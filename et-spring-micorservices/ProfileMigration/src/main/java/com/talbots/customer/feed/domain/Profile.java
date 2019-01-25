/**
 * 
 */
package com.talbots.customer.feed.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author sr4mxl
 *
 */
@XmlRootElement(name = "profile")
@XmlType(propOrder = { "firstName", "lastName", "email", "gender", "customAttributeList" })
public class Profile {

	private String firstName;

	private String lastName;

	private String email;

	private String gender;

	List<CommonAttribute> customAttributeList;

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

	@XmlElement(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@XmlElementWrapper(name = "custom-attributes")
	@XmlElement(name = "custom-attribute")
	public List<CommonAttribute> getCustomAttributeList() {
		return customAttributeList;
	}

	public void setCustomAttributeList(List<CommonAttribute> customAttributeList) {
		this.customAttributeList = customAttributeList;
	}

}