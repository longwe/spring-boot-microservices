/**
 * 
 */
package com.talbots.customer.feed.batch.intergation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.talbots.customer.feed.batch.BatchConfiguration;
import com.talbots.customer.feed.batch.util.FeedBatchUtil;
import com.talbots.customer.feed.domain.Address;
import com.talbots.customer.feed.domain.Addresses;
import com.talbots.customer.feed.domain.CommonAttribute;
import com.talbots.customer.feed.domain.Credential;
import com.talbots.customer.feed.domain.Customer;
import com.talbots.customer.feed.domain.Customers;
import com.talbots.customer.feed.domain.Password;
import com.talbots.customer.feed.domain.Profile;

/**
 * @author sr4mxl
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
		// TestDataSourceConfig.class,
		// MyBatisConfig.class,
		// SingleJobLauncherTestUtils.class,
		// MultiJobLauncherTestUtils.class,
		BatchConfiguration.class })
public class CustomerFeedBatchIntegrationTestIT {
	private static Map<String, Customer> custMap = new HashMap<>();
	private final static String SCHEMA_URL = "http://www.demandware.com/xml/impex/customer/2006-10-31";

	protected static final String CUSTOMER_FEED_QUERY = "select distinct top 100 \r\n" + "\r\n" + "    u.id as id,\r\n"
			+ "	u.login as login,\r\n" + "	u.password as password,\r\n" + "	\r\n" + "    u.first_name,\r\n"
			+ "	u.last_name,\r\n" + "	u.email,\r\n" + "	u.gender,\r\n" + "	\r\n" + "	tu.birth_month,\r\n"
			+ "	tu.us_citizen_status,\r\n" + "	tu.classic_awards_member_number,\r\n"
			+ "	tu.classic_awards_member,\r\n" + "	tu.receieve_promo_updates,\r\n" + "	csu.promo_signup,\r\n"
			+ "	\r\n" + "	ci.id as addressId,\r\n" + "	ci.first_name,\r\n" + "	ci.last_name,\r\n"
			+ "	ci.address1,\r\n" + "	ci.address2,\r\n" + "	ci.city,\r\n" + "	ci.postal_code,\r\n"
			+ "	ci.country,\r\n" + "	ci.phone_number,\r\n" + "\r\n" + "	\r\n" + "	 csi.address4,\r\n"
			+ "	 csi.day_phone,\r\n" + "	 csi.evening_phone,\r\n" + "	 csi.location\r\n" + "	 \r\n" + "	\r\n"
			+ "from atgcore.dbo.dps_user u\r\n" + "join atgcore.dbo.tlb_user tu on u.id = tu.id\r\n"
			+ "join atgcore.dbo.csys_user csu on u.id = csu.id\r\n"
			+ "join atgcore.dbo.dps_contact_info ci on u.id = ci.user_id\r\n"
			+ "join atgcore.dbo.csys_contact_info csi on ci.id = csi.id  order by u.id ";

	/*
	 * private JdbcTemplate jdbcTemplate;
	 * 
	 * @Autowired public void setDatasource(DataSource datasource) { jdbcTemplate =
	 * new JdbcTemplate(datasource); }
	 * 
	 * 
	 * @Autowired
	 * 
	 * @Qualifier(value = "singleJobLauncherTestUtils") private JobLauncherTestUtils
	 * singleJobLauncherTestUtils;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier(value = "multiJobLauncherTestUtils") private JobLauncherTestUtils
	 * multiJobLauncherTestUtils;
	 * 
	 */

	private JdbcTemplate simpleJdbcTemplate;

	private Customers customers = new Customers();

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Before
	public void setUp() {
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(CUSTOMER_FEED_QUERY);
		List<Customer> customerList = createCustomers(list);
		customers.setCustomers(customerList);
	}

	@Test
	public void testJob() throws Exception {

		StaxEventItemWriter<Customer> xmlFileWriter = new StaxEventItemWriter<>();

		String exportFilePath = "./src/main/resources/customers"
				+ FeedBatchUtil.getCurrentTimeStamp().replace(":", "_").replace(".", "_") + ".xml";
		xmlFileWriter.setResource(new FileSystemResource(exportFilePath));
		xmlFileWriter.setRootTagName("customers");

		Jaxb2Marshaller empMarshaller = new Jaxb2Marshaller();
		empMarshaller.setClassesToBeBound(Customer.class);
		xmlFileWriter.setMarshaller(empMarshaller);

		JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(customers, new File(exportFilePath));
		marshaller.marshal(customers, System.out);

		// JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		// Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
	}

	private List<Customer> createCustomers(List<Map<String, Object>> list) {

		Map<String, Integer> dups = getDuplicateCustomerIds(list);
		printMap(dups);

		List<Customer> customerList = new ArrayList<>();
		Customer customer = null;// new Customer();

		for (Map<String, Object> row : list) {
			String custId = (String) row.get("id");
			if (custMap.containsKey(custId)) {
				customer = custMap.get(custId);
				customer.getAddressesObject().getAddresses().add(getBillingAddress(row));
			} else {

				customer = new Customer();
				customer.setCustomerNumber((String) (row.get("id")));
				customer.setXmlns(SCHEMA_URL);
				customer.setCredentialsObject(createCustomerCredential(row));
				customer.setProfileObject(createProfileObject(row));
				customer.setAddressesObject(getAddresses(row));
				custMap.put(custId, customer);
				customerList.add(customer);
			}

		}

		return customerList;

	}

	private void printMap(Map<String, Integer> map) {

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}

	}

	private Map<String, Integer> getDuplicateCustomerIds(List<Map<String, Object>> list) {

		HashMap<String, Integer> frequencymap = new HashMap<String, Integer>();

		for (Map<String, Object> row : list) {
			if (frequencymap.containsKey(row.get("id"))) {
				frequencymap.put((String) row.get("id"), frequencymap.get(row.get("id")) + 1);
			} else {
				frequencymap.put((String) (row.get("id")), 1);
			}
		}

		return frequencymap;
	}

	private Credential createCustomerCredential(Map<String, Object> row) {

		String login = (String) row.get("login");

		return new Credential(login, getPassword(), "true");

	}

	public static Password getPassword() {
		Password password = new Password();
		password.setValue("Test1234");
		password.setEncrypted(true);

		return password;

	}

	private Profile createProfileObject(Map<String, Object> row) {

		Profile profileObject = new Profile();
		profileObject.setFirstName(((String) row.get("first_name")));
		profileObject.setLastName(((String) row.get("last_name")));
		profileObject.setEmail(((String) row.get("email")));
		profileObject.setGender(((String) String.valueOf(row.get("gender"))));
		profileObject.setCustomAttributeList(createCustomerProfileCustomAttributes(row));
		return profileObject;
	}

	private List<CommonAttribute> createCustomerProfileCustomAttributes(Map<String, Object> row) {
		List<CommonAttribute> profileList = new ArrayList<>();

		// tu.birth_month,
		// tu.us_citizen_status,
		// tu.classic_awards_member_number,
		// tu.classic_awards_member,
		// tu.receieve_promo_updates,
		// csu.promo_signup,

		CommonAttribute commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setAttributeId("legacyPassword");
		commonAttributeOne.setDescription((String) row.get("passowrd"));
		profileList.add(commonAttributeOne);

		CommonAttribute commonAttributeTwo = new CommonAttribute();
		commonAttributeTwo.setAttributeId("legacyTransition");
		commonAttributeTwo.setDescription("false");
		profileList.add(commonAttributeTwo);

		CommonAttribute commonAttributeThree = new CommonAttribute();
		commonAttributeThree.setAttributeId("legacyAccount");
		commonAttributeThree.setDescription("true");
		profileList.add(commonAttributeThree);

		// CommonAttribute commonAttributeFour = new CommonAttribute();
		// commonAttributeFour.setAttributeId("KINumber");
		// commonAttributeFour.setDescription("XXXXXXX");
		// profileList.add(commonAttributeFour);

		CommonAttribute commonAttributeFive = new CommonAttribute();
		if (!StringUtils.isBlank((String) row.get("birth_month"))) {
			commonAttributeFive.setAttributeId("birthMonth");
			commonAttributeFive.setDescription((String) row.get("birth_month"));
			profileList.add(commonAttributeFive);
		}

		if (StringUtils.isBlank((String) row.get((String) String.valueOf(row.get("us_citizen_status"))))) {
			CommonAttribute commonAttributeSix = new CommonAttribute();
			commonAttributeSix.setAttributeId("usCitizenStatus");
			commonAttributeSix.setDescription((String) String.valueOf(row.get("us_citizen_status")));
			profileList.add(commonAttributeSix);
		}

		CommonAttribute commonAttributeSeven = new CommonAttribute();

		if (!StringUtils.isBlank((String) row.get((String) row.get("classic_awards_member_number")))) {
			commonAttributeSeven.setAttributeId("classicAwardsMemberNumber");
			commonAttributeSeven.setDescription((String) row.get("classic_awards_member_number"));
			profileList.add(commonAttributeSeven);
		}

		CommonAttribute commonAttributEight = new CommonAttribute();
		if (!StringUtils.isBlank((String) row.get((String) String.valueOf(row.get("classic_awards_member"))))) {
			commonAttributEight.setAttributeId("classicAwardsMember");
			commonAttributEight.setDescription((String) String.valueOf(row.get("classic_awards_member")));
			profileList.add(commonAttributEight);
		}

		CommonAttribute commonAttributeNine = new CommonAttribute();
		commonAttributeNine.setAttributeId("chargeCardHolder");
		commonAttributeNine.setDescription("true");
		// profileList.add(commonAttributeNine);

		if (!StringUtils.isBlank((String) row.get((String) String.valueOf(row.get("receieve_promo_updates"))))) {
			CommonAttribute commonAttributeTen = new CommonAttribute();
			commonAttributeTen.setAttributeId("promoSignup");
			commonAttributeTen.setDescription((String) String.valueOf(row.get("receieve_promo_updates")));
			profileList.add(commonAttributeTen);
		}

		CommonAttribute commonAttributeElen = new CommonAttribute();
		commonAttributeElen.setAttributeId("talbotsChargeAccessEnabled");
		commonAttributeElen.setDescription("true");
		// profileList.add(commonAttributeElen);

		CommonAttribute commonAttributeTwelve = new CommonAttribute();
		commonAttributeTwelve.setAttributeId("preferredStore");
		commonAttributeTwelve.setDescription("1");
		// profileList.add(commonAttributeTwelve);

		CommonAttribute commonAttributeThirteen = new CommonAttribute();
		commonAttributeThirteen.setAttributeId("talbotsChargeInfo");
		commonAttributeThirteen.setDescription("133888585xxx");
		// profileList.add(commonAttributeThirteen);

		CommonAttribute commonAttributeFourteen = new CommonAttribute();
		commonAttributeFourteen.setAttributeId("preferredShippingAddressID");
		commonAttributeFourteen.setDescription("999999677");
		// profileList.add(commonAttributeFourteen);

		CommonAttribute commonAttributeFifteen = new CommonAttribute();
		commonAttributeFifteen.setAttributeId("preferredBillingAddressID");
		commonAttributeFifteen.setDescription("7896555");
		// profileList.add(commonAttributeFifteen);

		CommonAttribute commonAttributeSixteen = new CommonAttribute();
		commonAttributeSixteen.setAttributeId("catalogSignup");
		commonAttributeSixteen.setDescription((String) String.valueOf(row.get("promo_signup")));
		profileList.add(commonAttributeSixteen);

		CommonAttribute commonAttributeSeventeen = new CommonAttribute();
		commonAttributeSeventeen.setAttributeId("exportCustomerLoyalityInfo");
		commonAttributeSeventeen.setDescription("true");
		profileList.add(commonAttributeSeventeen);

		CommonAttribute commonAttributeEighteen = new CommonAttribute();
		commonAttributeEighteen.setAttributeId("expressCheckoutEnabled");
		commonAttributeEighteen.setDescription("false");
		profileList.add(commonAttributeEighteen);
		return profileList;
	}

	private Addresses getAddresses(Map<String, Object> row) {

		Address billingAddress = getBillingAddress(row);

		// Address shippingAddress = getShippingAddress();

		List<Address> addressList = new ArrayList<>();
		addressList.add(billingAddress);
		// addressList.add(shippingAddress);

		Addresses addresses = new Addresses();
		addresses.setAddresses(addressList);

		return addresses;

	}

	private Address getBillingAddress(Map<String, Object> row) {

		Address address = new Address();

		if (!StringUtils.isBlank((String) (row.get("addressId"))))
			address.setAddessId((String) row.get("addressId"));
		else {
			address.setAddessId(null);
		}

		address.setFirstName((String) row.get("first_name"));

		address.setLastName((String) row.get("last_name"));

		if (!StringUtils.isBlank((String) (row.get("address1"))))
			address.setAddressLine1((String) row.get("address1"));
		else {
			address.setAddressLine1(null);
		}

		if (!StringUtils.isBlank((String) (row.get("address2"))))
			address.setAddressLine2((String) row.get("address2"));
		else {
			address.setAddressLine2(null);
		}

		if (!StringUtils.isBlank((String) (row.get("city"))))
			address.setCity((String) row.get("city"));
		else {
			address.setCity((null));
		}

		if (!StringUtils.isBlank((String) (row.get("state"))))
			address.setState((String) row.get("state"));
		else {
			address.setState((null));
		}

		if (!StringUtils.isBlank((String) (row.get("postal_code"))))
			address.setPostalCode((String) row.get("postal_code"));
		else {
			address.setPostalCode((null));
		}

		if (!StringUtils.isBlank((String) (row.get("country"))))
			address.setCountry((String) row.get("country"));
		else {
			address.setCountry((null));
		}

		if (!StringUtils.isBlank((String) (row.get("phone_number"))))
			address.setPhoneNumber((String) row.get("phone_number"));
		else {
			address.setPhoneNumber(null);
		}

		address.setCustomAttributeList(getAddressCommonAttribute(row));
		return address;

	}

	private List<CommonAttribute> getAddressCommonAttribute(Map<String, Object> row) {
		List<CommonAttribute> billingAddress = new ArrayList<>();

		CommonAttribute commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setAttributeId("isLegacy");
		commonAttributeOne.setDescription("true");

		billingAddress.add(commonAttributeOne);

		CommonAttribute commonAttributeTwo = new CommonAttribute();
		commonAttributeTwo.setAttributeId("nickName");
		commonAttributeTwo.setDescription((String) row.get("nickname"));
		;

		billingAddress.add(commonAttributeTwo);

		CommonAttribute commonAttributeThree = new CommonAttribute();
		commonAttributeThree.setAttributeId("location");
		commonAttributeThree.setDescription((String) row.get("location"));
		billingAddress.add(commonAttributeThree);

		CommonAttribute commonAttributeFour = new CommonAttribute();
		commonAttributeFour.setAttributeId("dayPhone");
		commonAttributeFour.setDescription((String) row.get("day_phone"));
		billingAddress.add(commonAttributeFour);

		CommonAttribute commonAttributeFive = new CommonAttribute();
		commonAttributeFive.setAttributeId("eveningPhone");
		commonAttributeFive.setDescription((String) row.get("evening_phone"));
		billingAddress.add(commonAttributeFive);

		CommonAttribute commonAttributeSix = new CommonAttribute();
		commonAttributeSix.setAttributeId("address3");
		commonAttributeSix.setDescription((String) row.get("address3"));
		billingAddress.add(commonAttributeSix);

		CommonAttribute commonAttributeSeven = new CommonAttribute();
		commonAttributeSeven.setAttributeId("address4");
		commonAttributeSeven.setDescription("address4");
		billingAddress.add(commonAttributeSeven);

		return billingAddress;

	}

}
