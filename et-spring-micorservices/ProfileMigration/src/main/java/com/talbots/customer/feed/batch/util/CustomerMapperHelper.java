/**
 * 
 */
package com.talbots.customer.feed.batch.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.talbots.customer.feed.domain.Address;
import com.talbots.customer.feed.domain.Addresses;
import com.talbots.customer.feed.domain.CommonAttribute;
import com.talbots.customer.feed.domain.Credential;
import com.talbots.customer.feed.domain.Password;
import com.talbots.customer.feed.domain.Profile;

/**
 * @author sr4mxl
 *
 */
public class CustomerMapperHelper {

	private CustomerMapperHelper() {

	}

	public static Profile createProfileObject(ResultSet rs) throws SQLException {

		Profile profileObject = new Profile();
		if (!StringUtils.isAllBlank(rs.getString("first_name"))) {
			profileObject.setFirstName(rs.getString("first_name"));
		} else {
			profileObject.setFirstName(null);
		}

		if (!StringUtils.isAllBlank(rs.getString("last_name"))) {
			profileObject.setLastName(rs.getString("last_name"));
		} else {
			profileObject.setLastName(null);
		}

		if (!StringUtils.isAllBlank(rs.getString("email"))) {
			profileObject.setEmail(rs.getString("email"));
		} else {
			profileObject.setEmail(null);
		}

		if (!StringUtils.isAllBlank(rs.getString("gender"))) {
			profileObject.setGender(rs.getString("gender"));
		} else {
			profileObject.setGender(null);
		}

		profileObject.setCustomAttributeList(createCustomerProfileCustomAttributes(rs));
		return profileObject;

	}

	private static List<CommonAttribute> createCustomerProfileCustomAttributes(ResultSet rs) throws SQLException {
		List<CommonAttribute> profileList = new ArrayList<>();

		CommonAttribute commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setDescription(rs.getString("password"));
		commonAttributeOne.setAttributeId("legacyPassword");
		profileList.add(commonAttributeOne);

		CommonAttribute commonAttributeTwo = new CommonAttribute();
		commonAttributeTwo.setAttributeId("legacyTransition");
		commonAttributeTwo.setDescription("false");
		profileList.add(commonAttributeTwo);

		CommonAttribute commonAttributeThree = new CommonAttribute();
		commonAttributeThree.setAttributeId("legacyAccount");
		commonAttributeThree.setDescription("true");
		profileList.add(commonAttributeThree);

		if (!StringUtils.isBlank(rs.getString("birth_month"))) {
			CommonAttribute commonAttributeFive = new CommonAttribute();
			commonAttributeFive.setAttributeId("birthMonth");
			commonAttributeFive.setDescription(rs.getString("birth_month"));
			profileList.add(commonAttributeFive);
		}

		if (!StringUtils.isBlank(String.valueOf(rs.getInt("us_citizen_status")))) {
			CommonAttribute commonAttributeSix = new CommonAttribute();
			commonAttributeSix.setAttributeId("usCitizenStatus");
			commonAttributeSix.setDescription(String.valueOf(rs.getInt("us_citizen_status")));
			profileList.add(commonAttributeSix);
		}

		if (!StringUtils.isBlank(rs.getString("classic_awards_member_number"))) {
			CommonAttribute commonAttributeSeven = new CommonAttribute();
			commonAttributeSeven.setAttributeId("classicAwardsMemberNumber");
			commonAttributeSeven.setDescription(rs.getString("classic_awards_member_number"));
			profileList.add(commonAttributeSeven);
		}

		String classicAwardsMember = String.valueOf(rs.getString("classic_awards_member"));

		if (!StringUtils.isBlank(classicAwardsMember)) {
			CommonAttribute commonAttributEight = new CommonAttribute();
			commonAttributEight.setAttributeId("classicAwardsMember");

			if ("0".equalsIgnoreCase(classicAwardsMember)) {
				commonAttributEight.setDescription("false");
			} else {
				commonAttributEight.setDescription("true");
			}

			profileList.add(commonAttributEight);
		}

		if (!StringUtils.isBlank(String.valueOf(rs.getInt("receieve_promo_updates")))) {
			CommonAttribute commonAttributeTen = new CommonAttribute();
			commonAttributeTen.setAttributeId("promoSignup");
			commonAttributeTen.setDescription(String.valueOf(rs.getInt("receieve_promo_updates")));
			profileList.add(commonAttributeTen);
		}

		if (!StringUtils.isBlank(String.valueOf(rs.getInt("promo_signup")))) {
			CommonAttribute commonAttributeSixteen = new CommonAttribute();
			commonAttributeSixteen.setAttributeId("catalogSignup");
			commonAttributeSixteen.setDescription(String.valueOf(rs.getBoolean("promo_signup")));
			profileList.add(commonAttributeSixteen);
		}
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

	public static Addresses getAddresses(ResultSet rs) throws SQLException {
		Address billingAddress = getBillingAddress(rs);
		Addresses addresses = new Addresses();

		if (billingAddress != null) {
			List<Address> addressList = new ArrayList<>();
			addressList.add(billingAddress);

			addresses.setAddresses(addressList);

		} else {
			addresses.setAddresses(null);
		}
		return addresses;

	}

	public static Credential createCustomerCredential(ResultSet rs) throws SQLException {
		String login = rs.getString("login");
		Password password = getPassword();
		return new Credential(login, password, "true");
	}

	public static Password getPassword() {
		Password password = new Password();
		password.setValue("Test1234");
		password.setEncrypted(false);

		return password;

	}

	public static Address getBillingAddress(ResultSet rs) throws SQLException {
		Address address = new Address();

		if (!StringUtils.isBlank(rs.getString("addressId"))) {
			address.setAddessId(rs.getString("addressId"));
		} else {
			address.setAddessId(null);
			address = null;
			return address;
		}

		if (!StringUtils.isBlank(rs.getString("ad_first_name"))) {
			address.setFirstName(rs.getString("ad_first_name"));
		} else {
			address.setFirstName(null);
		}

		if (!StringUtils.isBlank(rs.getString("ad_last_name"))) {
			address.setLastName(rs.getString("ad_last_name"));
		} else {
			address.setLastName(null);
		}

		if (!StringUtils.isBlank(rs.getString("address1"))) {
			address.setAddressLine1(rs.getString("address1"));
		} else {
			address.setAddressLine1(null);
		}

		if (!StringUtils.isBlank(rs.getString("address2"))) {
			address.setAddressLine2(rs.getString("address2"));
		} else {
			address.setAddressLine2(null);
		}

		if (!StringUtils.isBlank(rs.getString("city"))) {
			address.setCity(rs.getString("city"));
		} else {
			address.setCity(null);
		}

		if (!StringUtils.isBlank(rs.getString("state"))) {
			address.setState(rs.getString("state"));
		} else {
			address.setState(null);
		}

		String zipCode = processZipCode(rs);

		if (!StringUtils.isBlank(zipCode)) {
			address.setPostalCode(zipCode);
		} else {
			address.setPostalCode(null);
		}

		String country = getCountry(rs);

		if (!StringUtils.isBlank(country)) {
			address.setCountry(country);
		} else {
			address.setCountry(null);
		}

		if (!StringUtils.isBlank(rs.getString("phone_number"))) {
			address.setPhoneNumber(rs.getString("phone_number"));
		} else {
			address.setPhoneNumber(null);
		}

		address.setCustomAttributeList(getAddressCommonAttribute(rs));
		return address;
	}

	private static List<CommonAttribute> getAddressCommonAttribute(ResultSet rs) throws SQLException {
		List<CommonAttribute> billingAddress = new ArrayList<>();

		CommonAttribute commonAttributeOne = new CommonAttribute();
		commonAttributeOne.setAttributeId("isLegacy");
		commonAttributeOne.setDescription("true");

		billingAddress.add(commonAttributeOne);

		CommonAttribute commonAttributeTwo = new CommonAttribute();
		if (!StringUtils.isBlank(rs.getString("nickname"))) {
			commonAttributeTwo.setAttributeId("nickName");
			commonAttributeTwo.setDescription(rs.getString("nickname"));
			billingAddress.add(commonAttributeTwo);
			{
			}

			if (!StringUtils.isBlank(rs.getString("location"))) {
				CommonAttribute commonAttributeThree = new CommonAttribute();
				commonAttributeThree.setAttributeId("location");
				commonAttributeThree.setDescription(rs.getString("location"));
				billingAddress.add(commonAttributeThree);
			}
			if (!StringUtils.isBlank(rs.getString("day_phone"))) {
				CommonAttribute commonAttributeFour = new CommonAttribute();
				commonAttributeFour.setAttributeId("dayPhone");
				commonAttributeFour.setDescription(rs.getString("day_phone"));
				billingAddress.add(commonAttributeFour);
			}

			if (!StringUtils.isBlank(rs.getString("evening_phone"))) {
				CommonAttribute commonAttributeFive = new CommonAttribute();
				commonAttributeFive.setAttributeId("eveningPhone");
				commonAttributeFive.setDescription(rs.getString("evening_phone"));
				billingAddress.add(commonAttributeFive);
			}

			if (!StringUtils.isBlank(rs.getString("address3"))) {
				CommonAttribute commonAttributeSix = new CommonAttribute();
				commonAttributeSix.setAttributeId("address3");
				commonAttributeSix.setDescription(rs.getString("address3"));
				billingAddress.add(commonAttributeSix);
			}

			if (!StringUtils.isBlank(rs.getString("address4"))) {
				CommonAttribute commonAttributeSeven = new CommonAttribute();
				commonAttributeSeven.setAttributeId("address4");
				commonAttributeSeven.setDescription(rs.getString("address4"));
				billingAddress.add(commonAttributeSeven);
			}

		}

		return billingAddress;
	}

	private static String processZipCode(ResultSet rs) throws SQLException {
		String zipCode = getZipCode(rs);
		String countryCode = getCountry(rs);
		if ("US".equalsIgnoreCase(countryCode) && zipCode.length() > 5) {
			zipCode = zipCode.substring(0, 5);
		}

		return zipCode;

	}

	private static String getCountry(ResultSet rs) throws SQLException {
		String country = "";
		if (!StringUtils.isBlank(rs.getString("country"))) {
			country = (rs.getString("country"));
		}
		return country;

	}

	private static String getZipCode(ResultSet rs) throws SQLException {
		String zipCode = "";
		if (!StringUtils.isBlank(rs.getString("postal_code"))) {
			zipCode = rs.getString("postal_code");
		}
		return zipCode;

	}
}