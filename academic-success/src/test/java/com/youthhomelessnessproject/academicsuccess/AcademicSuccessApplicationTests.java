package com.youthhomelessnessproject.academicsuccess;

import org.testng.annotations.Test;

import com.youthhomelessnessproject.academicsuccess.controllers.HomeController;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

@SpringBootTest()
public class AcademicSuccessApplicationTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	@BeforeClass
	public void setup() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	
	@Test
	public void homeController()throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(200,status);
		
	}
	
	@Test
	public void loginController()throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/login/student")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(200,status);
		
	}
	
	

}
