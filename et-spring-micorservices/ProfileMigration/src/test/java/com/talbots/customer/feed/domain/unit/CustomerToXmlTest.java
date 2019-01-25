package com.talbots.customer.feed.domain.unit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.talbots.customer.feed.batch.util.CustomerMapperHelper;
import com.talbots.customer.feed.batch.util.FeedBatchUtil;
import com.talbots.customer.feed.domain.Address;
import com.talbots.customer.feed.domain.Addresses;
import com.talbots.customer.feed.domain.CommonAttribute;
import com.talbots.customer.feed.domain.Credential;
import com.talbots.customer.feed.domain.Customer;
import com.talbots.customer.feed.domain.Customers;
import com.talbots.customer.feed.domain.Profile;

public class CustomerToXmlTest {
	private Customer customer = new Customer();
	private Credential credentials = new Credential();
	private Addresses addresses = new Addresses();
	private Profile profile = new Profile();

	private List<Customer> customerList = new ArrayList<>();

	@Before
	public void setUp() {

		for (int i = 0; i < 10; i++) {

			profile = createProfileObject();
			credentials = createCredentials();
			addresses = getAddresses();
			customer.setCustomerNumber("0000001");
			customer.setXmlns("http://www.demandware.com/xml/impex/customer/2006-10-31");
			customer.setProfileObject(profile);
			customer.setCredentialsObject(credentials);
			customer.setAddressesObject(addresses);
			customerList.add(customer);
		}

		Customers customers = new Customers();

		customers.setCustomers(customerList);

	}

	@After
	public void tearDown() {
		customer = null;
	}

	@Test
	public void StaxEventItemWriterTest() throws JAXBException, XmlMappingException, IOException {

		System.out.println(testObjectToXml().toString());
	}

	public StaxEventItemWriter testObjectToXml() throws JAXBException, FileNotFoundException {

		StaxEventItemWriter xmlFileWriter = new StaxEventItemWriter<>();

		String exportFilePath = "./Customer_Talbots_"
				+ FeedBatchUtil.getCurrentTimeStamp().replace(":", "-").replace(".", "-") + ".xml";
		xmlFileWriter.setResource(new FileSystemResource(exportFilePath));
		xmlFileWriter.setRootTagName("customers");
		Jaxb2Marshaller empMarshaller = new Jaxb2Marshaller();
		empMarshaller.setClassesToBeBound(Customers.class);
		xmlFileWriter.setShouldDeleteIfEmpty(true);
		xmlFileWriter.setMarshaller(empMarshaller);
		return xmlFileWriter;
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Customers.class);
		return marshaller;
	}
	/*
	 * JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class); Marshaller
	 * marshaller = jaxbContext.createMarshaller();
	 * marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 * marshaller.marshal(customer, new File("Customer.xml"));
	 * marshaller.marshal(customer, System.out);
	 */

	private Credential createCredentials() {
		return new Credential("srivera@lyonscg.com", CustomerMapperHelper.getPassword(), "true");

	}

	private Addresses getAddresses() {

		Address billingAddress = getBillingAddress();

		Address shippingAddress = getShippingAddress();

		List<Address> addressList = new ArrayList<>();
		addressList.add(billingAddress);
		addressList.add(shippingAddress);

		Addresses addresses = new Addresses();
		addresses.setAddresses(addressList);

		return addresses;

	}

	private Address getBillingAddress() {

		Address address = new Address();
		address.setAddessId("999992");
		address.setFirstName("Samuel");
		address.setLastName("Rivera");
		address.setAddressLine1("123 Main Street");
		address.setAddressLine2("Apt3");
		address.setCity("Higham");
		address.setState("MA");
		address.setPostalCode("023468");
		address.setCountry("USA");
		address.setPhoneNumber("999-999-9999");
		address.setCustomAttributeList(getShippinggAddressCommonAttribute());
		return address;

	}

	private List<CommonAttribute> getShippinggAddressCommonAttribute() {

		List<CommonAttribute> billingAddress = new ArrayList<>();

		CommonAttribute commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setAttributeId("isLegacy");
		commonAttributeOne.setDescription("true");

		billingAddress.add(commonAttributeOne);

		CommonAttribute commonAttributeTwo = new CommonAttribute();
		commonAttributeTwo.setAttributeId("nickName");
		commonAttributeTwo.setDescription("billimg");

		billingAddress.add(commonAttributeTwo);

		CommonAttribute commonAttributeThree = new CommonAttribute();
		commonAttributeThree.setAttributeId("location");
		commonAttributeThree.setDescription("domestic");
		billingAddress.add(commonAttributeThree);

		CommonAttribute commonAttributeFour = new CommonAttribute();
		commonAttributeFour.setAttributeId("dayPhone");
		commonAttributeFour.setDescription("9999-888-7777");
		billingAddress.add(commonAttributeFour);

		CommonAttribute commonAttributeFive = new CommonAttribute();
		commonAttributeFive.setAttributeId("eveningPhone");
		commonAttributeFive.setDescription("9998-123-1234");
		billingAddress.add(commonAttributeFive);

		return billingAddress;

	}

	private Address getShippingAddress() {
		Address address = new Address();

		address.setAddessId("989899");
		address.setFirstName("Samuel");
		address.setLastName("Rivera");
		address.setAddressLine1("12 Cedar Street");
		address.setAddressLine2("Apt3");
		address.setCity("Stoughton");
		address.setState("MA");
		address.setPostalCode("02072");
		address.setCountry("USA");
		address.setPhoneNumber("339-999-1212");

		address.setCustomAttributeList(getBillingAddressCommonAttribute());
		return address;

	}

	private List<CommonAttribute> getBillingAddressCommonAttribute() {
		List<CommonAttribute> billingAddress = new ArrayList<>();

		CommonAttribute commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setAttributeId("isLegacy");
		commonAttributeOne.setDescription("true");

		billingAddress.add(commonAttributeOne);

		CommonAttribute commonAttributeTwo = new CommonAttribute();
		commonAttributeTwo.setAttributeId("nickName");
		commonAttributeTwo.setDescription("billimg");

		billingAddress.add(commonAttributeTwo);

		CommonAttribute commonAttributeThree = new CommonAttribute();
		commonAttributeThree.setAttributeId("location");
		commonAttributeThree.setDescription("canada");
		billingAddress.add(commonAttributeThree);

		CommonAttribute commonAttributeFour = new CommonAttribute();
		commonAttributeFour.setAttributeId("dayPhone");
		commonAttributeFour.setDescription("9999-888-7777");
		billingAddress.add(commonAttributeFour);

		CommonAttribute commonAttributeFive = new CommonAttribute();
		commonAttributeFive.setAttributeId("eveningPhone");
		commonAttributeFive.setDescription("9998-123-1234");
		billingAddress.add(commonAttributeFive);

		CommonAttribute commonAttributeSix = new CommonAttribute();
		commonAttributeSix.setAttributeId("address3");
		commonAttributeSix.setDescription("Apt 10");
		billingAddress.add(commonAttributeSix);

		CommonAttribute commonAttributeSeven = new CommonAttribute();
		commonAttributeSeven.setAttributeId("address4");
		commonAttributeSeven.setDescription("Suite 10");
		billingAddress.add(commonAttributeSeven);

		return billingAddress;

	}

	private Profile createProfileObject() {
		Profile profileObject = new Profile();
		profileObject.setFirstName("Sam");
		profileObject.setLastName("Rivera");
		profileObject.setEmail("srivera@lyonscg.com");
		profileObject.setGender("1");
		profileObject.setCustomAttributeList(createCustomerProfileCustomAttributes());
		return profileObject;
	}

	private List<CommonAttribute> createCustomerProfileCustomAttributes() {
		List<CommonAttribute> profileList = new ArrayList<>();

		CommonAttribute commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setAttributeId("legacyPassword");
		commonAttributeOne.setDescription("encryptedpasswordhere");
		profileList.add(commonAttributeOne);

		CommonAttribute commonAttributeTwo = new CommonAttribute();
		commonAttributeTwo.setAttributeId("legacyTransition");
		commonAttributeTwo.setDescription("false");
		profileList.add(commonAttributeTwo);

		CommonAttribute commonAttributeThree = new CommonAttribute();
		commonAttributeThree.setAttributeId("legacyAccount");
		commonAttributeThree.setDescription("true");
		profileList.add(commonAttributeThree);

		CommonAttribute commonAttributeFour = new CommonAttribute();
		commonAttributeFour.setAttributeId("KINumber");
		commonAttributeFour.setDescription("9999999999");
		profileList.add(commonAttributeFour);

		CommonAttribute commonAttributeFive = new CommonAttribute();
		commonAttributeFive.setAttributeId("birthMonth");
		commonAttributeFive.setDescription("10");
		profileList.add(commonAttributeFive);

		CommonAttribute commonAttributeSix = new CommonAttribute();
		commonAttributeSix.setAttributeId("usCitizenStatus");
		commonAttributeSix.setDescription("1");
		profileList.add(commonAttributeSix);

		CommonAttribute commonAttributeSeven = new CommonAttribute();
		;
		commonAttributeSeven.setAttributeId("classicAwardsMemberNumber");
		commonAttributeSeven.setDescription("111111111");
		profileList.add(commonAttributeSeven);

		CommonAttribute commonAttributEight = new CommonAttribute();
		commonAttributEight.setAttributeId("classicAwardsMember");
		commonAttributEight.setDescription("true");
		profileList.add(commonAttributEight);

		CommonAttribute commonAttributeNine = new CommonAttribute();
		commonAttributeNine.setAttributeId("chargeCardHolder");
		commonAttributeNine.setDescription("true");
		// profileList.add(commonAttributeNine);

		CommonAttribute commonAttributeTen = new CommonAttribute();
		commonAttributeTen.setAttributeId("promoSignup");
		commonAttributeTen.setDescription("1");
		profileList.add(commonAttributeTen);

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
		commonAttributeSixteen.setDescription("true");
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

}
