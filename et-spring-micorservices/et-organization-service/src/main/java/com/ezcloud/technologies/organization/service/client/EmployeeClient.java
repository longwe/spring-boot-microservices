/**
 * 
 */
package com.ezcloud.technologies.organization.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezcloud.technologies.organization.service.model.Employee;

/**
 * @author sr4mxl
 *
 */
@FeignClient(name = "et-employee-service")
//@RibbonClient(name = "et-customer-service")
public interface EmployeeClient {
	@GetMapping("/organization/{organizationId}")
	public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationID);

}
