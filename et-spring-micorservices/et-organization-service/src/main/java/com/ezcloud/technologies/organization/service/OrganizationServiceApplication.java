package com.ezcloud.technologies.organization.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.ezcloud.technologies.organization.service.model.Organization;
import com.ezcloud.technologies.organization.service.repository.OrganizationRepository;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class OrganizationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

	@Bean
	OrganizationRepository repository() {
		OrganizationRepository repository = new OrganizationRepository();
		repository.add(Organization.builder().name("Microsoft").address("Redmond, Washington, USA").build());
		repository.add(Organization.builder().name("Oracle").address("Redwood City, California, USA").build());
		return repository;
	}

}