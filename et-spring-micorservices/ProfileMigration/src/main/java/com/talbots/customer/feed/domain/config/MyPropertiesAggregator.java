/**
 * 
 */
package com.talbots.customer.feed.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

/**
 * @author sr4mxl
 *
 */
@Component
@Data
@ToString
public class MyPropertiesAggregator {
	@Autowired
	HibernateJpaProperty jpaLocalProperty;
	@Autowired
	HibernateJpaProperty hibernateJpaProperty;
	@Autowired
	PropDataSource propDataSource;

}
