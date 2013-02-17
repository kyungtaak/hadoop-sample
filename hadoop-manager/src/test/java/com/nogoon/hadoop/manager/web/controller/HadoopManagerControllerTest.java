package com.nogoon.hadoop.manager.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nogoon.hadoop.manager.config.MainConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("webapp")
@ContextConfiguration(classes = MainConfig.class)
public class HadoopManagerControllerTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	private static MockMvc mvc;

	@Before
	public void beforeClass() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public final void whenSpringContextIsInstantiated_thenNoExceptions() {
	}

	@Test
	public void viewManagerHome() throws Exception {

		MvcResult result = mvc.perform(get("/home")).andExpect(status().isOk())
				.andReturn();

		System.out.println(ToStringBuilder.reflectionToString(
				result.getResponse(), ToStringStyle.MULTI_LINE_STYLE));
	}

}
