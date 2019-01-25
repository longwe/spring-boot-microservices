/**
 * 
 */
package com.talbots.customer.feed.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author sr4mxl
 *
 */
@XmlRootElement(name = "customer")
@XmlType(propOrder = { "credentialsObject", "profileObject", "addressesObject" })
public class Customer {

	private String customerNumber;

	private String xmlns;

	private Profile profileObject;
	private Credential credentialsObject;
	private Addresses addressesObject;

	public Customer() {
		super();

	}

	@XmlAttribute(name = "customer-no")
	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	@XmlAttribute(name = "xmlns")
	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	@XmlElement(name = "profile")
	public Profile getProfileObject() {
		return profileObject;
	}

	public void setProfileObject(Profile profileObject) {
		this.profileObject = profileObject;
	}

	@XmlElement(name = "credentials")
	public Credential getCredentialsObject() {
		return credentialsObject;
	}

	public void setCredentialsObject(Credential credentialsObject) {
		this.credentialsObject = credentialsObject;
	}

	@XmlElement(name = "addresses")
	public Addresses getAddressesObject() {
		return addressesObject;
	}

	public void setAddressesObject(Addresses addressesObject) {
		this.addressesObject = addressesObject;
	}

}