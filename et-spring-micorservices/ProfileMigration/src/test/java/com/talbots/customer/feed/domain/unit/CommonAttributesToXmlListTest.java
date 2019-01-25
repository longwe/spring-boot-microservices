/**
 * 
 */
package com.talbots.customer.feed.domain.unit;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Test;

import com.talbots.customer.feed.domain.CommonAttribute;
import com.talbots.customer.feed.domain.CustomAttributes;


/**
 * @author sr4mxl
 *
 */
public class CommonAttributesToXmlListTest {
	
	private CommonAttribute commonAttributeOne;
	private CommonAttribute commonAttributeTwo;
	private CommonAttribute commonAttributeThree;
	private CommonAttribute commonAttributeFour;
	private CommonAttribute commonAttributeFive;
	private CommonAttribute commonAttributeSix;
	private CommonAttribute commonAttributeSeven;
	private CommonAttribute commonAttributEight;
	private CommonAttribute commonAttributeNine;
	private CommonAttribute commonAttributeElen;
	private CommonAttribute commonAttributeTwelve;
	private CommonAttribute commonAttributeThirteen;
	private CommonAttribute commonAttributeFourteen;
	private CommonAttribute commonAttributeFifteen;
	private CommonAttribute commonAttributeSixteen;
	private CommonAttribute commonAttributeSeventeen;
	private CommonAttribute commonAttributeEighteen;
	
	
	/*
	 * <custom-attribute attribute-id="legacyPassword">encryptedpasswordhere</custom-attribute>
      <!-- - Source table name: ? Table Field Name: ?   -->
      <custom-attribute attribute-id="legacyTransition">false</custom-attribute>
      <!-- - Source table name: ? Table Field Name: ?  -->
      <custom-attribute attribute-id="legacyAccount">true</custom-attribute>
      <!-- - Source table name: Table Field Name:   -->
      <custom-attribute attribute-id="KINumber">9999999999</custom-attribute><!-- ignore -->
      <!-- - Source table name: dbo.dps_user Table Field Name: date_of_birth  -->
      <custom-attribute attribute-id="birthMonth">10</custom-attribute>
      <!-- - Source table name: dbo.tlb_user Table Field Name:  us_citizen_status --> 
      <custom-attribute attribute-id="usCitizenStatus">1</custom-attribute>
      <!-- - Source table name: dbo.tlb_user Table Field Name:  classic_awards_member_number -->
      <custom-attribute attribute-id="classicAwardsMemberNumber">1111111111</custom-attribute>
      <!-- - Source table name: dbo.tlb_user Table Field Name:  classic_awards_member -->
      <custom-attribute attribute-id="classicAwardsMember">true</custom-attribute>
      <!-- - Source table name: dbo.tlb_user Table Field Name:  charge_card_holder -->
      <custom-attribute attribute-id="chargeCardHolder">true</custom-attribute><!-- ignore -->
      <!-- - Source table name: dbo.tlb_user Table Field Name:  receieve_promo_update --> 
      <custom-attribute attribute-id="promoSignup">1</custom-attribute>
      <!-- - Source table name: Table Field Name:   -->
      <custom-attribute attribute-id="talbotsChargeAccessEnabled">true</custom-attribute><!-- ignore -->
      <!-- - Source table name: Table Field Name:   -->
      <custom-attribute attribute-id="preferredStore">1</custom-attribute><!-- ignore -->
      <!-- - Source table name: Table Field Name:   -->
      <custom-attribute attribute-id="talbotsChargeInfo">895445XXXX</custom-attribute><!-- ignore -->
      <!-- - Source table name: Table Field Name:   -->
       <custom-attribute attribute-id="preferredShippingAddressID">990363</custom-attribute><!-- ignore -->
       <!-- - Source table name: Table Field Name:   -->
      <custom-attribute attribute-id="preferredBillingAddressID">990364</custom-attribute><!-- need more info-->
      <!-- - Source table name: Table Field Name:   -->
      <custom-attribute attribute-id="catalogSignup">true</custom-attribute>
      <!-- - Source table name: Table Field Name:   -->
      <custom-attribute attribute-id="exportCustomerLoyalityInfo">true</custom-attribute><!-- default value is true -->
      <!-- - Source table name: dbo.dps_contact_info Table Field Name:   -->
      <custom-attribute attribute-id="expressCheckoutEnabled">false</custom-attribute><!-- default value is false -->
	 */
	/*
	@Before
	public void setup() {
		commonAttributeOne = new CommonAttribute("legacyPassword", "encryptedpasswordhere");
		commonAttributeTwo = new CommonAttribute("legacyTransition", "false");
		commonAttributeThree = new CommonAttribute("legacyAccount", "true");
		commonAttributeFour = new CommonAttribute("KINumber", "9999999999");
		commonAttributeFive = new CommonAttribute("birthMonth", "10");
		 commonAttributeSix = new CommonAttribute("usCitizenStatus", "1");;
		 commonAttributeSeven= new CommonAttribute("classicAwardsMemberNumber", "111111111");;
		 commonAttributEight= new CommonAttribute("classicAwardsMember", "true");;
		commonAttributeNine= new CommonAttribute("chargeCardHolder", "true");;
		 commonAttributeTen= new CommonAttribute("promoSignup", "1");;
		 commonAttributeElen= new CommonAttribute("talbotsChargeAccessEnabled", "true");;
		 commonAttributeTwelve= new CommonAttribute("preferredStore", "1");;
		 commonAttributeThirteen= new CommonAttribute("talbotsChargeInfo", "133888585xxx");;
		 commonAttributeFourteen= new CommonAttribute("preferredShippingAddressID", "999999677");;
		 commonAttributeFifteen= new CommonAttribute("preferredBillingAddressID", "7896555");;
		 commonAttributeSixteen= new CommonAttribute("catalogSignup", "true");;
		 commonAttributeSeventeen= new CommonAttribute("exportCustomerLoyalityInfo", "true");;
		 commonAttributeEighteen= new CommonAttribute("expressCheckoutEnabled", "false");;
	
	}*/
	
	@After
	public void tearDown() {
		commonAttributeOne = null;
	}
	
	@Test
    public void testObjectToXml() throws JAXBException, FileNotFoundException {
		CustomAttributes customAttributes = new CustomAttributes();
		customAttributes.add(commonAttributeOne);
		customAttributes.add(commonAttributeTwo);
		customAttributes.add(commonAttributeThree);
		customAttributes.add(commonAttributeFour);
		customAttributes.add(commonAttributeFive);
		customAttributes.add(commonAttributeSix);
		customAttributes.add(commonAttributeSeven);
		customAttributes.add(commonAttributEight);
		customAttributes.add(commonAttributeNine);
		customAttributes.add(commonAttributeElen);
		customAttributes.add(commonAttributeTwelve);
		customAttributes.add(commonAttributeThirteen);
		customAttributes.add(commonAttributeFourteen);
		customAttributes.add(commonAttributeFifteen);
		customAttributes.add(commonAttributeSixteen);
		customAttributes.add(commonAttributeSeventeen);
		customAttributes.add(commonAttributeEighteen);
		
       
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomAttributes.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(customAttributes, new File("customAttributes.xml"));
        marshaller.marshal(customAttributes, System.out);
    }

}
