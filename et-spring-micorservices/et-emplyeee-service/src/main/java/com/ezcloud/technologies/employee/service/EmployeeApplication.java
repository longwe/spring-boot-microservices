/**
 * 
 */
package com.ezcloud.technologies.employee.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.ezcloud.technologies.employee.service.model.Employee;
import com.ezcloud.technologies.employee.service.repository.EmployeeRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sr4mxl
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class EmployeeApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean
	EmployeeRepository repository() {
		EmployeeRepository repository = new EmployeeRepository();
		repository
				.add(Employee.builder().id(1L).departmentId(1L).name("John Smith").age(34).position("Analyst").build());
		repository.add(
				Employee.builder().id(1L).departmentId(1L).name("Darren Hamilto").age(55).position("Manager").build());
		repository.add(
				Employee.builder().id(1L).departmentId(1L).name("Tom Scott").age(31).position("Developer").build());
		repository.add(
				Employee.builder().id(1L).departmentId(1L).name("Anna London").age(34).position("Analyst").build());
		repository.add(
				Employee.builder().id(1L).departmentId(1L).name("Patrick Dempsey").age(34).position("Analyst").build());
		repository
				.add(Employee.builder().id(1L).departmentId(2L).name("Jane Smith").age(34).position("Manager").build());
		repository.add(
				Employee.builder().id(1L).departmentId(3L).name("Kevin Price").age(34).position("Developer").build());
		repository
				.add(Employee.builder().id(2L).departmentId(2L).name("Ian Scott").age(38).position("Analyst").build());
		repository.add(
				Employee.builder().id(2L).departmentId(3L).name("Steve Franklin").age(30).position("Analyst").build());
		repository.add(Employee.builder().id(2L).departmentId(4L).name("Elisabeth Smith").age(44).position("Developer")
				.build());

		return repository;
	}

}
