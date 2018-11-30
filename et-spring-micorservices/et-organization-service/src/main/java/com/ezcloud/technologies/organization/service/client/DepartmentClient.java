/**
 * 
 */
package com.ezcloud.technologies.organization.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezcloud.technologies.organization.service.model.Department;
import com.ezcloud.technologies.organization.service.model.Employee;

/**
 * @author sr4mxl
 *
 */
@FeignClient(name = "et-department-service")
public interface DepartmentClient {
	@GetMapping("/organization/{organizationId}")
	List<Department> findByOrganization(@PathVariable("organizationId") Long departmentID);

	@GetMapping("/organization/{organizationId}/with-employees")
	List<Employee> findByOrganizationWithEmployees(@PathVariable("organizationId") Long departmentID);

}
