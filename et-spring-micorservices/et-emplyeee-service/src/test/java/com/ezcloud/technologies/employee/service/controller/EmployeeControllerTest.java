/**
 * 
 */
package com.ezcloud.technologies.employee.service.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ezcloud.technologies.employee.service.EmployeeApplicationTestUtil;
import com.ezcloud.technologies.employee.service.model.Employee;

/**
 * @author sr4mxl
 *
 */
public class EmployeeControllerTest extends EmployeeApplicationTestUtil {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void givenValidIdShouldRetrieveEmployeeDetails() throws Exception {
		String uri = "/v1/api/employees";
		System.out.println("URI " + mockMvc.perform(MockMvcRequestBuilders.get(uri)).toString());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Employee[] empployeeList = super.mapFromJson(content, Employee[].class);
		assertTrue(empployeeList.length > 0);
	}

}
