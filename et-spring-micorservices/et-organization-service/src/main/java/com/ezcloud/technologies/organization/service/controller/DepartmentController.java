/**
 * 
 */
package com.ezcloud.technologies.organization.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ezcloud.technologies.organization.service.client.DepartmentClient;
import com.ezcloud.technologies.organization.service.client.EmployeeClient;
import com.ezcloud.technologies.organization.service.model.Employee;
import com.ezcloud.technologies.organization.service.model.Organization;
import com.ezcloud.technologies.organization.service.repository.OrganizationRepository;

/**
 * @author sr4mxl
 *
 */
@RestController
public class DepartmentController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	OrganizationRepository repository;

	@Autowired
	DepartmentClient departmentClient;

	@Autowired
	EmployeeClient employeeClient;

	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		return repository.findById(id);
	}

	@SuppressWarnings("static-access")
	@GetMapping("/{id}/with-departments-and-employees")
	public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = findById(id);
		List<Employee> employees = departmentClient.findByOrganizationWithEmployees(organization.getId());
		organization.setEmployees(employees);
		;
		return organization;

	}

	@GetMapping("/{id}/with-employees")
	public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);
		organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
		return organization;
	}

	@GetMapping("/{id}/with-departments")
	public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);
		organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
		return organization;
	}

}
