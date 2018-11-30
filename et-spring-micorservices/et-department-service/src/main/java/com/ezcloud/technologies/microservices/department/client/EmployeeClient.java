/**
 * 
 */
package com.ezcloud.technologies.microservices.department.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezcloud.technologies.microservices.department.model.Employee;

/**
 * @author sr4mxl
 *
 */
@FeignClient("et-employee-service")
public interface EmployeeClient {
	@GetMapping("/department/{departmentId}")
	public Employee findByDepartment(@PathVariable("departmentID") Long departmentId);

}
