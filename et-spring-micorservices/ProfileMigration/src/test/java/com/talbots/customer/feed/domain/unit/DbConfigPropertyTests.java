package com.talbots.customer.feed.domain.unit;

import static org.mockito.BDDMockito.given;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.talbots.customer.feed.domain.config.HibernateJpaProperty;
import com.talbots.customer.feed.domain.config.MyPropertiesAggregator;
import com.talbots.customer.feed.domain.config.PropDataSource;

/**
 * @author sr4mxl
 *
 */

public class DbConfigPropertyTests {
	@Mock
	PropDataSource propDataSource;
	@Mock
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
		given(myPropertiesAggregator.getPropDataSource().getDriverClassName())
				.willReturn("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// When
		String driverClassName = testPropDataSource.getDriverClassName();

		// Then
		Assert.assertEquals("com.microsoft.sqlserver.jdbc.SQLServerDriver", driverClassName);
	}

	@Test
	public void shouldRetrieveUrlValueFromYamlConfigFile() throws Exception {
		PropDataSource testPropDataSource = new PropDataSource();
		String url = "jdbc:sqlserver://VTP3102\\\\MSSQLSERVER2012:65239;DatabaseName=atgcore;integratedSecurity=true;sendStringParametersAsUnicode=false;responseBuffering=adaptive";
		testPropDataSource.setUrl(url);
		// Given
		given(myPropertiesAggregator.getPropDataSource().getUrl()).willReturn(
				"jdbc:sqlserver://VTP3102\\\\MSSQLSERVER2012:65239;DatabaseName=atgcore;integratedSecurity=true;sendStringParametersAsUnicode=false;responseBuffering=adaptive");
		// When
		String testUrl = testPropDataSource.getUrl();

		// Then

		Assert.assertEquals(
				"jdbc:sqlserver://VTP3102\\\\MSSQLSERVER2012:65239;DatabaseName=atgcore;integratedSecurity=true;sendStringParametersAsUnicode=false;responseBuffering=adaptive",
				testUrl);
	}

	@Test
	public void shouldRetrieveDatabasePlatformFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setDatabasePlatform("org.hibernate.dialect.SQLServer2008Dialect");

		// Given
		given(myPropertiesAggregator.getJpaLocalProperty().getDatabasePlatform())
				.willReturn("org.hibernate.dialect.SQLServer2008Dialect");
		// When
		String databasePlatform = testHibernateJpaProperty.getDatabasePlatform();

		// Then
		Assert.assertEquals("org.hibernate.dialect.SQLServer2008Dialect", databasePlatform);
	}

	@Test
	public void shouldRetrieveHibernateDdlAutoValueFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setHibernateDdlAuto("update");

		// Given
		given(myPropertiesAggregator.getJpaLocalProperty().getHibernateDdlAuto()).willReturn("update");
		// When
		String hibernateDdlAuto = testHibernateJpaProperty.getHibernateDdlAuto();

		// Then
		Assert.assertEquals("update", hibernateDdlAuto);
	}

	@Test
	public void shouldRetrieveShowSqlValueFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setShowSql("update");

		// Given
		given(myPropertiesAggregator.getJpaLocalProperty().getShowSql()).willReturn("update");
		// When
		String showSql = testHibernateJpaProperty.getShowSql();

		// Then
		Assert.assertEquals("update", showSql);
	}

	@Test
	public void shouldRetrieveUseSqlCommentsValueFromYamlConfigFile() throws Exception {

		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setUseSqlComments("update");

		// Given
		given(myPropertiesAggregator.getJpaLocalProperty().getUseSqlComments()).willReturn("update");
		// When
		String useSqlComments = testHibernateJpaProperty.getUseSqlComments();

		// Then
		Assert.assertEquals("update", useSqlComments);
	}

	@Test
	public void shouldRetrieveFormatSqlValueFromYamlConfigFile() throws Exception {
		HibernateJpaProperty testHibernateJpaProperty = new HibernateJpaProperty();
		testHibernateJpaProperty.setFormatSql("update");

		// Given
		given(myPropertiesAggregator.getJpaLocalProperty().getFormatSql()).willReturn("update");

		// When
		String formatSql = testHibernateJpaProperty.getFormatSql();

		// Then
		Assert.assertEquals("update", formatSql);
	}

}
