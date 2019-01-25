/**
 * 
 */
package com.talbots.customer.feed.batch.intergation;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.talbots.customer.feed.batch.util.SqlCallsUtil;

/**
 * @author sr4mxl
 *
 */
@SuppressWarnings("static-access")
@RunWith(SpringJUnit4ClassRunner.class)
public class SqlCallsUtilTestIT {

	SqlCallsUtil sqlCallsUtil;
	static String updateSql;
	static String selectSql;

	@Before
	public void setUp() {

		updateSql = sqlCallsUtil.UPDATE_CUSTOMER_SQL;

		selectSql = SqlCallsUtil.PROD_RETRIEVE_TBL_CUSTOMERS_TEST_SQL;

	}

	@Test
	public void test_retrieve_select_customer_location() throws Exception {
		System.out.println(selectSql);

		assertNotNull(updateSql);

	}

	@Test
	public void test_retrieve_select_update_location() throws Exception {
		System.out.println(updateSql);

		assertNotNull(updateSql);

	}

	@Test
	public void test_retrieve_select_customer_sql() throws Exception {
		System.out.println(sqlCallsUtil.getSql(updateSql));

		assertNotNull(sqlCallsUtil.getSql(updateSql));

	}

	@Test
	public void test_retrieve_select_update_sql() throws Exception {
		System.out.println(sqlCallsUtil.getSql(selectSql));

		assertNotNull(sqlCallsUtil.getSql(selectSql));

	}

}
