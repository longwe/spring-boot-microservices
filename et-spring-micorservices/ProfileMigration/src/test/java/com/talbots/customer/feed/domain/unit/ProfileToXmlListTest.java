/**
 * 
 */
package com.talbots.customer.feed.domain.unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.talbots.customer.feed.domain.CommonAttribute;
import com.talbots.customer.feed.domain.Profile;



/**
 * @author sr4mxl
 *
 */
public class ProfileToXmlListTest {
	/*
	 * <!-- - Source table name: dbo.dps_user Table Field Name:first_name   -->
    <first-name>Sam</first-name>
    <!-- - Source table name:dbo.dps_user Table Field Name:  last_name -->
    <last-name>Rivera</last-name>
    <!-- - Source table name:dbo.dps_user Table Field Name: email  -->
    <email>srivera@lyonscg.com</email>
    <!-- - Source table name: dbo.dps_user Table Field Name: gender  -->
    <gender>1</gender>
	 */
	
	private Profile profile = new Profile();
	
	
	@Before
	public void setup() {
		profile.setFirstName("Samuel");
		profile.setLastName("Rivera");
		profile.setEmail("srivera@lyonscg.com");
		profile.setGender("1");
		profile.setCustomAttributeList(createCustomerProfileCustomAttributes());
				
		
	}
	
	@After
	public void tearDown() {
		profile = null;
	}
	
	@Test
    public void testObjectToXml() throws JAXBException, FileNotFoundException {
		
        JAXBContext jaxbContext = JAXBContext.newInstance(Profile.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(profile, new File("Profile.xml"));
        marshaller.marshal(profile, System.out);
    }
	
	
	private   List<CommonAttribute> createCustomerProfileCustomAttributes() {
		List<CommonAttribute> profileList = new ArrayList<>();
		
		CommonAttribute	commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setAttributeId("legacyPassword");
		commonAttributeOne.setDescription( "encryptedpasswordhere");
		profileList.add(commonAttributeOne);
		
		CommonAttribute	commonAttributeTwo = new CommonAttribute( );
		commonAttributeTwo.setAttributeId("legacyTransition");
		commonAttributeTwo.setDescription("false");
		profileList.add(commonAttributeTwo);
		
		CommonAttribute	commonAttributeThree = new CommonAttribute( );
		commonAttributeThree.setAttributeId("legacyAccount");
		commonAttributeThree.setDescription("true");
		profileList.add(commonAttributeThree);
		
		CommonAttribute	commonAttributeFour = new CommonAttribute();
		commonAttributeFour.setAttributeId("KINumber");
		commonAttributeFour.setDescription("9999999999");
		profileList.add(commonAttributeFour);
		
		CommonAttribute	commonAttributeFive = new CommonAttribute( );
		commonAttributeFive.setAttributeId("birthMonth");
		commonAttributeFive.setDescription("10");
		profileList.add(commonAttributeFive);
		
		CommonAttribute	 commonAttributeSix = new CommonAttribute();
		commonAttributeSix.setAttributeId("usCitizenStatus");
		commonAttributeSix.setDescription( "1");
		profileList.add(commonAttributeSix);
		
		 CommonAttribute	 commonAttributeSeven= new CommonAttribute();;
		 commonAttributeSeven.setAttributeId("classicAwardsMemberNumber");
		 commonAttributeSeven.setDescription("111111111");
		 profileList.add(commonAttributeSeven);
		 
		 CommonAttribute	 commonAttributEight= new CommonAttribute();
		 commonAttributEight.setAttributeId("classicAwardsMember");
		 commonAttributEight.setDescription("true");
		 profileList.add(commonAttributEight);
		 
		 CommonAttribute	commonAttributeNine= new CommonAttribute( );
		 commonAttributeNine.setAttributeId("chargeCardHolder");
		 commonAttributeNine.setDescription("true");
		 //profileList.add(commonAttributeNine);
		
		 CommonAttribute	 commonAttributeTen= new CommonAttribute();
		 commonAttributeTen.setAttributeId("promoSignup");
		 commonAttributeTen.setDescription("1");
		profileList.add(commonAttributeTen);
		
		CommonAttribute	 commonAttributeElen= new CommonAttribute( );
		commonAttributeElen.setAttributeId("talbotsChargeAccessEnabled");
		commonAttributeElen.setDescription("true");
		// profileList.add(commonAttributeElen);
		 
		 CommonAttribute	 commonAttributeTwelve= new CommonAttribute();
		 commonAttributeTwelve.setAttributeId("preferredStore");
		 commonAttributeTwelve.setDescription("1");
		// profileList.add(commonAttributeTwelve);
		 
		 CommonAttribute	 commonAttributeThirteen= new CommonAttribute();
		 commonAttributeThirteen.setAttributeId("talbotsChargeInfo");
		 commonAttributeThirteen.setDescription("133888585xxx");
		 //profileList.add(commonAttributeThirteen);
		 
		 CommonAttribute	 commonAttributeFourteen= new CommonAttribute( );
		 commonAttributeFourteen.setAttributeId("preferredShippingAddressID");
		 commonAttributeFourteen.setDescription("999999677");
		 //profileList.add(commonAttributeFourteen);
		 
		 CommonAttribute	 commonAttributeFifteen= new CommonAttribute( );
		 commonAttributeFifteen.setAttributeId("preferredBillingAddressID");
		 commonAttributeFifteen.setDescription("7896555");
		 //profileList.add(commonAttributeFifteen);
		 
		 CommonAttribute commonAttributeSixteen= new CommonAttribute( );
		 commonAttributeSixteen.setAttributeId("catalogSignup");
		 commonAttributeSixteen.setDescription("true");
		 profileList.add(commonAttributeSixteen);
		 
		 CommonAttribute	 commonAttributeSeventeen= new CommonAttribute( );
		 commonAttributeSeventeen.setAttributeId("exportCustomerLoyalityInfo");
		 commonAttributeSeventeen.setDescription("true");
		 profileList.add(commonAttributeSeventeen);
		
		 CommonAttribute commonAttributeEighteen= new CommonAttribute( );
		 commonAttributeEighteen.setAttributeId("expressCheckoutEnabled");
		 commonAttributeEighteen.setDescription("false");
		 profileList.add(commonAttributeEighteen);
		return profileList;
		
		
	}
	
	


}
