/**
 * 
 */
package com.ezcloud.technologies.employee.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sr4mxl
 *
 */
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class Employee {
	private Long id;
	private Long organizationId;
	private Long departmentId;
	private String name;
	private int age;
	private String position;

}
