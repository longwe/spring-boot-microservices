/**
 * 
 */
package com.talbots.customer.feed.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sr4mxl
 *
 */
@XmlRootElement(name = "customers")
public class Customers {
	private List<Customer> customers;

	/**
	 * @param customers
	 */
	public Customers() {

	}

	@XmlElement(name="customers")
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
