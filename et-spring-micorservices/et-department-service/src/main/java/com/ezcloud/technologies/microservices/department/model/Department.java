/**
 * 
 */
package com.ezcloud.technologies.microservices.department.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 * @author sr4mxl
 *
 */
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class Department {
	private Long id;
	private Long organizationId;
	private String name;
	@Singular
	private final List<Employee> employees;

}
