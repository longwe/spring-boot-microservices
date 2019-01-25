/**
 * 
 */
package com.talbots.customer.feed.domain;

import java.lang.reflect.Field;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author sr4mxl
 *
 */
@XmlRootElement(name = "address")
@XmlType(propOrder = { "addessId", "firstName", "lastName", "addressLine1", "addressLine2", "city", "postalCode",
		"state", "country", "phoneNumber", "customAttributeList" })
public class Address {

	private String addessId;

	private String firstName;

	private String lastName;

	private String addressLine1;

	private String addressLine2;

	private String postalCode;

	private String city;

	private String state;

	private String country;

	private String phoneNumber;

	List<CommonAttribute> customAttributeList;

	@XmlAttribute(name = "address-id")
	public String getAddessId() {
		return addessId;
	}

	public void setAddessId(String addessId) {
		this.addessId = addessId;
	}

	@XmlElement(name = "first-name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement(name = "last-name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "address1")
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@XmlElement(name = "address2")
	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@XmlElement(name = "postal-code")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@XmlElement(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement(name = "state-code")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlElement(name = "country-code")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name = "phone")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@XmlElementWrapper(name = "custom-attributes")
	@XmlElement(name = "custom-attribute")
	public List<CommonAttribute> getCustomAttributeList() {
		return customAttributeList;
	}

	public void setCustomAttributeList(List<CommonAttribute> customAttributeList) {
		this.customAttributeList = customAttributeList;
	}

	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		String res = "";
		try {
			for (Field field : fields) {
				res += field.getName() + " :\n" + field.get(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

}
