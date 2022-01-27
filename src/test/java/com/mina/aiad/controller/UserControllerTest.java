package com.mina.aiad.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mina.aiad.model.User;
import com.mina.aiad.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	UserService userService;

	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	@DisplayName("Test create user")
	public void createUserTest() throws Exception {

		mockMvc.perform(post("/users").content(asJsonString(new User("testname", "password", "admin")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	@DisplayName("Test getting all users")
	public void getAllusersTest() throws Exception {

		mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}

	@Test
	@DisplayName("Test getting all features for a user")
	public void getAllFeaturesTest() throws Exception {

		mockMvc.perform(get("/users/1/features").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
