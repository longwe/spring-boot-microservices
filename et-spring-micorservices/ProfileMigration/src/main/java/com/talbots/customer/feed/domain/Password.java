
package com.talbots.customer.feed.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "password", propOrder = { "value" })
public class Password {

	@XmlValue
	protected String value;
	@XmlAttribute(name = "encrypted", required = true)
	protected boolean encrypted;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value of the encrypted property.
	 * 
	 */
	public boolean isEncrypted() {
		return encrypted;
	}

	/**
	 * Sets the value of the encrypted property.
	 * 
	 */
	public void setEncrypted(boolean value) {
		this.encrypted = value;
	}

}
