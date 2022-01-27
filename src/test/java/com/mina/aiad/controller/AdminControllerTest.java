package com.mina.aiad.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mina.aiad.model.User;
import com.mina.aiad.service.AdminService;
import com.mina.aiad.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdminControllerTest {

	private MockMvc mockMvc;

	@Mock
	AdminService adminService;

	@InjectMocks
	private AdminController adminController;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}
	
	
	@Test
	@DisplayName("Test getting all features")
	public void getAllFeaturesTest() throws Exception {

		mockMvc.perform(get("/admin/features").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}
	
	@Test
	@DisplayName("Test adding feature")
	public void createUserTest() throws Exception {

		mockMvc.perform(post("/admin/features").content("newfeature")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}
	
	
	@Test
	@DisplayName("Test updating feature")
	public void updateFeatureTest() throws Exception {

		mockMvc.perform(put("/admin/features/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}
	
	
	@Test
	@DisplayName("Test enable feature to user")
	public void switchOnUserFeatureTest() throws Exception {

		mockMvc.perform(put("/admin/features/1/user/testuser/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}
}
