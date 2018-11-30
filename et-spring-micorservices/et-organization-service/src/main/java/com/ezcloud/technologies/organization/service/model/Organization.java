/**
 * 
 */
package com.ezcloud.technologies.organization.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * @author sr4mxl
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {
	private Long id;
	private String name;
	private String address;
	@Singular
	private List<Department> departments;
	@Singular
	private List<Employee> employees;

}
