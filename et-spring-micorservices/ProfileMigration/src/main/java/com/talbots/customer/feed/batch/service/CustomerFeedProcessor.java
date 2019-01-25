/**
 * 
 */
package com.talbots.customer.feed.batch.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.talbots.customer.feed.domain.Customer;

/**
 * @author sr4mxl
 *
 */

public class CustomerFeedProcessor implements ItemProcessor<Customer, Customer> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFeedProcessor.class);

	private static List<String> customerList = new ArrayList<>();

	@Override
	public Customer process(Customer customer) throws Exception {

		if (LOGGER.isInfoEnabled()) {

			String message = MessageFormat.format("Processing customer number {0}", customer.getCustomerNumber());

			LOGGER.info(message);

		}
		if (customerList.contains(customer.getCustomerNumber())) {
			return null;
		} else {

			customerList.add(customer.getCustomerNumber());
		}

		return customer;
	}

}
