package com.talbots.customer.feed.batch.intergation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.talbots.customer.feed.domain.config.HibernateJpaProperty;
import com.talbots.customer.feed.domain.config.MyPropertiesAggregator;
import com.talbots.customer.feed.domain.config.PropDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbConfigPropertyIntegrationTestIT {

	@Autowired
	PropDataSource propDataSource;

	@Autowired
	HibernateJpaProperty hibernateJpaProperty;

	private MyPropertiesAggregator myPropertiesAggregator;

	@org.junit.Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		myPropertiesAggregator = new MyPropertiesAggregator();
		myPropertiesAggregator.setPropDataSource(propDataSource);
		myPropertiesAggregator.setJpaLocalProperty(hibernateJpaProperty);
	}

	@Test
	public void shouldRetrieveDriverClassNameValueFromYamlConfigFile() throws Exception {
		PropDataSource testPropDataSource = new PropDataSource();
		testPropDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// Given
		String testSTring = testPropDataSource.getDriverClassName();

		// When
		String driverClassName = myPropertiesAggregator.getPropDataSource().getDriverClassName();

		// Then
		Assert.assertEquals(testSTring, driverClassName);
	}

	@Test
	public void shouldRetrieveUrlValueFromYamlConfigFile() throws Exception {
		PropDataSource testPropDataSource = new PropDataSource();

		// Given
		testPropDataSource.setUrl(
				"jdbc:sqlserver://VTP3102\\\\MSSQLSERVER2012:65239;DatabaseName=atgcore;integratedSecurity=true;sendStringParametersAsUnicode=false;responseBuffering=adaptive");

		String url = testPropDataSource.getUrl();

		// When
		String testUrl = myPropertiesAggregator.getPropDataSource().getUrl();

		// Then

		Assert.assertEquals(url, testUrl);
	}

	@Test
	public void shouldRetrieveDatabasePlatformFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		// Given
		testHibernateJpaProperty.setDatabasePlatform("org.hibernate.dialect.SQLServer2008Dialect");
		String databasePlatform = testHibernateJpaProperty.getDatabasePlatform();

		// When
		String testDatabasePlatform = myPropertiesAggregator.getJpaLocalProperty().getDatabasePlatform();

		// Then
		Assert.assertEquals(databasePlatform, testDatabasePlatform);
	}

	@Test
	public void shouldRetrieveHibernateDdlAutoValueFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setHibernateDdlAuto("update");

		// Given
		String hibernateDdlAuto = testHibernateJpaProperty.getHibernateDdlAuto();
		// When
		String testHibernateDdlAuto = myPropertiesAggregator.getJpaLocalProperty().getHibernateDdlAuto();

		// Then
		Assert.assertEquals(hibernateDdlAuto, testHibernateDdlAuto);
	}

	@Test
	public void shouldRetrieveShowSqlValueFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setShowSql("true");
		// Given
		String showSql = testHibernateJpaProperty.getShowSql();

		// When
		String testShowSql = myPropertiesAggregator.getJpaLocalProperty().getShowSql();

		// Then
		Assert.assertEquals(showSql, testShowSql);
	}

	@Test
	public void shouldRetrieveUseSqlCommentsValueFromYamlConfigFile() throws Exception {

		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setUseSqlComments("true");

		// Given
		String useSqlComments = testHibernateJpaProperty.getUseSqlComments();
		// When
		String testUseSqlComments = myPropertiesAggregator.getJpaLocalProperty().getUseSqlComments();

		// Then
		Assert.assertEquals(useSqlComments, testUseSqlComments);
	}

	@Test
	public void shouldRetrieveFormatSqlValueFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new

		HibernateJpaProperty();
		testHibernateJpaProperty.setFormatSql("true");

		// Given
		String formatSql = testHibernateJpaProperty.getFormatSql();

		// When
		String testFormatSql = myPropertiesAggregator.getJpaLocalProperty().getFormatSql();

		// Then
		Assert.assertEquals(formatSql, testFormatSql);
	}
}
