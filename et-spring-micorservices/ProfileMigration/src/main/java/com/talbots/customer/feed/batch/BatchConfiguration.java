/**
 * 
 */
package com.talbots.customer.feed.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.talbots.customer.feed.batch.service.CustomStaxEventItemWriter;
import com.talbots.customer.feed.batch.service.CustomerFeedProcessor;
import com.talbots.customer.feed.batch.service.ItemCountListener;
import com.talbots.customer.feed.batch.util.CustomerMapperHelper;
import com.talbots.customer.feed.batch.util.FeedBatchUtil;
import com.talbots.customer.feed.batch.util.SqlCallsUtil;
import com.talbots.customer.feed.domain.Addresses;
import com.talbots.customer.feed.domain.Customer;
import com.talbots.customer.feed.domain.config.MyPropertiesAggregator;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);
	@SuppressWarnings("unused")
	private static Map<String, Customer> custMap = new HashMap<>();

	@Autowired
	MyPropertiesAggregator dbConfigPropertiesAgregator;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	DataSource dataSource;

	@Autowired
	private EntityManagerFactory emFactory;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbConfigPropertiesAgregator.getPropDataSource().getDriverClassName());
		dataSource.setUrl(dbConfigPropertiesAgregator.getPropDataSource().getUrl());
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com.talbots.customer.feed");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect",
				dbConfigPropertiesAgregator.getHibernateJpaProperty().getDatabasePlatform());
		hibernateProperties.put("hibernate.show_sql",
				dbConfigPropertiesAgregator.getHibernateJpaProperty().getShowSql());
		hibernateProperties.put("hibernate.hbm2ddl.auto",
				dbConfigPropertiesAgregator.getHibernateJpaProperty().getHibernateDdlAuto());
		sessionFactoryBean.setHibernateProperties(hibernateProperties);

		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	@Bean(destroyMethod = "")
	public JdbcCursorItemReader<Customer> reader() {
		JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<Customer>();
		reader.setDataSource(dataSource);
		reader.setSql(SqlCallsUtil.getSql(SqlCallsUtil.PROD_BULK__CUSTOMER_MIGRATION__SQL));
		reader.setRowMapper(new CustomerRowMapper());

		return reader;
	}

	public class CustomerRowMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = null;
			String custId = rs.getString("id");

			if (custMap.containsKey(custId)) {
				customer = custMap.get(custId);
				customer.getAddressesObject().getAddresses().add(CustomerMapperHelper.getBillingAddress(rs));
			} else {

				customer = new Customer();
				customer.setCustomerNumber(rs.getString("id"));
				customer.setCredentialsObject(CustomerMapperHelper.createCustomerCredential(rs));
				customer.setProfileObject(CustomerMapperHelper.createProfileObject(rs));
				Addresses addresses = CustomerMapperHelper.getAddresses(rs);
				if (addresses != null) {
					customer.setAddressesObject(addresses);
				} else {
					customer.setAddressesObject(null);
				}

				custMap.put(custId, customer);

			}

			return customer;

		}

	}

	@Bean
	public CustomerFeedProcessor processor() {
		return new CustomerFeedProcessor();
	}

	@Bean
	public ItemCountListener listener() {
		return new ItemCountListener();
	}

	@Bean(destroyMethod = "")

	public ItemWriter<Customer> xmlWriter() {
		CustomStaxEventItemWriter<Customer> xmlFileWriter = new CustomStaxEventItemWriter<>();

		String exportFilePath = "./Customer_Talbots_"
				+ FeedBatchUtil.getCurrentTimeStamp().replace(":", "").replace(".", "_") + ".xml";
		xmlFileWriter.setResource(new FileSystemResource(exportFilePath));
		HashMap<String, String> rootElementAttribs = new HashMap<String, String>();
		rootElementAttribs.put("xmlns", "http://www.demandware.com/xml/impex/customer/2006-10-31");
		xmlFileWriter.setResource(new FileSystemResource(exportFilePath));
		xmlFileWriter.setRootTagName("customers");
		xmlFileWriter.setRootTagNamespace("\"xmlns\", \"http://www.demandware.com/xml/impex/customer/2006-10-31\"");
		xmlFileWriter.setRootElementAttributes(rootElementAttribs);
		Jaxb2Marshaller empMarshaller = new Jaxb2Marshaller();
		empMarshaller.setClassesToBeBound(Customer.class);
		xmlFileWriter.setShouldDeleteIfEmpty(true);
		xmlFileWriter.setMarshaller(empMarshaller);
		return xmlFileWriter;
	}

	@Bean("jdbcBatchItemWriter")
	public JdbcBatchItemWriter<Customer> writer() throws Exception {
		JdbcBatchItemWriter<Customer> jdbcBatchItemWriter = new JdbcBatchItemWriter<Customer>();
		jdbcBatchItemWriter.setAssertUpdates(true);
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setSql("update dps_user set processed=2 where id=:customerNumber");
		jdbcBatchItemWriter.setItemPreparedStatementSetter(setter());
		jdbcBatchItemWriter
				.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
		jdbcBatchItemWriter.afterPropertiesSet();

		return jdbcBatchItemWriter;
	}

	@Bean
	public ItemPreparedStatementSetter<Customer> setter() {
		return (customer, ps) -> {

			String customerId = customer.getCustomerNumber();

			if (LOGGER.isInfoEnabled()) {

				String message = MessageFormat.format("Updating record for customer number {0}", customerId);

				LOGGER.info(message);

			}
			ps.setString(1, customerId);

		};
	}

	@Bean(destroyMethod = "")
	public CompositeItemWriter<Customer> compositeItemWriter() throws Exception {
		CompositeItemWriter<Customer> writer = new CompositeItemWriter<Customer>();
		List<ItemWriter<? super Customer>> writerList = new ArrayList<ItemWriter<? super Customer>>();
		writerList.add(xmlWriter());
		writerList.add(writer());
		writer.setDelegates(writerList);
		return writer;

	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<Customer, Customer>chunk(100).reader(reader()).processor(processor())
				.writer(compositeItemWriter()).listener(listener()).build();
	}

	@Bean
	public Job exportUserJob() throws Exception {
		return jobBuilderFactory.get("exportCustomerFeedJob").incrementer(new RunIdIncrementer()).preventRestart()
				.flow(step1()).end().build();
	}

}
