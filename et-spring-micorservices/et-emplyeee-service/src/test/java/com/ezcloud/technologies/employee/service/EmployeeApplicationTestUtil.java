/**
 * 
 */
package com.ezcloud.technologies.employee.service;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sr4mxl
 *
 */
/*
 * Abstract class file used to create web application context by using MockMvc
 * and define the mapToJson() and mapFromJson() methods to convert the Java
 * object into JSON string and convert the JSON string into Java object.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmployeeApplication.class)
@WebAppConfiguration
public abstract class EmployeeApplicationTestUtil {

	protected MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}
