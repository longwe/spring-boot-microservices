/**
 * 
 */
package com.ezcloud.technologies.employee.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezcloud.technologies.employee.service.model.Employee;
import com.ezcloud.technologies.employee.service.repository.EmployeeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

/**
 * @author sr4mxl
 *
 */
@RestController
@Api(value = "/", authorizations = { @Authorization(value = "employee_auth", scopes = {
		@AuthorizationScope(scope = "write:employees", description = "modify employees in your departments"),
		@AuthorizationScope(scope = "read:employees", description = "read your employees") }) }, tags = "employees")

public class EmployeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository repository;

	@PostMapping("/")
	public Employee add(@RequestBody Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return repository.add(employee);
	}

	@GetMapping("/{id}")
	@ApiOperation("Find employee by id")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid id supplied "),
			@ApiResponse(code = 404, message = "Employee not found") })
	public Employee findById(
			@ApiParam(value = "Employee id to retrieve", required = true) @PathVariable("id") Long id) {
		LOGGER.info("Employee find: id={}", id);
		return repository.findById(id);
	}

	@GetMapping("/")
	@ApiOperation("Find Employees")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid url supplied"),
			@ApiResponse(code = 404, message = "Employees not found") })
	public List<Employee> findAll() {
		LOGGER.info("Employee find");
		return repository.findAll();
	}

	@GetMapping("/department/{departmentId}")
	@ApiOperation("Find department by departmentId")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid departmentId supplied "),
			@ApiResponse(code = 404, message = "Department not found") })
	public List<Employee> findByDepartment(
			@ApiParam(value = "Department id to retrieve", required = true) @PathVariable("departmentId") Long departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);
		return repository.findByDepartment(departmentId);
	}

	@GetMapping("/organization/{organizationId}")
	@ApiOperation("Find organization by organizationId")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid organizationId supplied "),
			@ApiResponse(code = 404, message = "Organization not found") })
	public List<Employee> findByOrganization(
			@ApiParam(value = "Organization id to retrieve", required = true) @PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}

}
