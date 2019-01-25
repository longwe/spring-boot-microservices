/**
 * 
 */
package com.talbots.customer.feed.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * @author sr4mxl
 *
 */
@Builder
@Getter
public class CustomerDTO {
	private String customerNumber;
	private String loginId;
	private int processesStatus;

}
