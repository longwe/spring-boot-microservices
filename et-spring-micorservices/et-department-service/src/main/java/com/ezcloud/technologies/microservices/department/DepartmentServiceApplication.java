package com.ezcloud.technologies.microservices.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.ezcloud.technologies.microservices.department.model.Department;
import com.ezcloud.technologies.microservices.department.reposiitory.DepartmentRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableSwagger2
public class DepartmentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

	/*
	 * @Bean public Docket swaggerPersonApi10() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage(
	 * "pl.piomin.services.department.controller"))
	 * .paths(PathSelectors.any()).build().apiInfo(new
	 * ApiInfoBuilder().version("1.0").title("Department API")
	 * .description("Documentation Department API v1.0").build()); }
	 */

	@Bean
	DepartmentRepository repository() {
		DepartmentRepository repository = new DepartmentRepository();
		repository.add(Department.builder().organizationId(1L).name("Development").build());
		repository.add(Department.builder().organizationId(1L).name("Operations").build());
		repository.add(Department.builder().organizationId(2L).name("Development").build());
		repository.add(Department.builder().organizationId(2L).name("Operations").build());

		return repository;
	}
}