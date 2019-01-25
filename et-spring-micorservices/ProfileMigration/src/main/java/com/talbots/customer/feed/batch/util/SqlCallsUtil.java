/**
 * 
 */
package com.talbots.customer.feed.batch.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

/**
 * @author sr4mxl
 *
 */
@Component
public class SqlCallsUtil {
	public static final String PROD_RETRIEVE_TBL_CUSTOMERS_TEST_SQL = "db/sql/prod_talbots_user_accounts_migration.sql";
	public static final String PROD_RETRIEVE_TBL_CUSTOMERS_TEST_SQL_2 = "db/sql/prod_talbots_user_accounts_migration_v2.sql";
	public static final String QA_RETRIEVE_CUSTOMERS_ALL_SQL = "db/sql/qa_retrieve_customers_all_uprocessed.sql";
	public static final String RETRIEVE_CUSTOMERS_RUN_PROCESSED_SQL = "db/sql/retrieve_job_processed_customers.sql";
	public static final String UPDATE_CUSTOMER_SQL = "db/sql/update_customer_processed_record.sql";
	public static final String QA_10_CUSTOMER_SQL = "db/sql/qa-10-customer-records-for-qa-testing.sql";
	public static final String PROD__CUSTOMER_LOGIN_TEST_SQL = "db/sql/steve-login-issue-prod-migration.sql";
	public static final String PROD_BULK__CUSTOMER_MIGRATION__SQL = "db/sql/prod_bulk_talbots_migration_v1.sql";

	private SqlCallsUtil() {
	}

	/**
	 * Method takes a file path String and returns the String representation of that
	 * sql file
	 *
	 * @param filePath of location of the sql file
	 * @return a String of the contents of the SQL file
	 */
	public static final String getSql(String filePath) {
		String sql = "";

		try {
			// get the file stream object
			InputStream is = SqlCallsUtil.class.getClassLoader().getResourceAsStream(filePath);

			// convert to String and close stream
			sql = CharStreams.toString(new InputStreamReader(is));
			if (null == sql || sql.isEmpty()) {
				Closeables.closeQuietly(is);
				throw new IOException("File path to SQL file could not be read!");
			}

			Closeables.closeQuietly(is);

			return sql;
		} catch (IOException ex) {
			// log your error
			return sql;
		}
	}

	/*
	 * @Override public void update(final List<Group> groups){ String updateSQL =
	 * SqlCalls.getSql(SqlCalls.UPDATE_GROUP_SQL); int[] batchUpdate =
	 * getJdbcTemplate().batchUpdate(updateSQL, new BatchPreparedStatementSetter(){
	 * 
	 * @Override public void setValues(PreparedStatement ps, int i) throws
	 * SQLException {
	 * 
	 * Group grp = groups.get(i); ps.setString(1, grp.getName()); ps.setString(2,
	 * grp.getLocation()); }
	 * 
	 * @Override public int getBatchSize() { return groups.size(); } } }
	 */

}
