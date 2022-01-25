package com.mina.aiad.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mina.aiad.model.Feature;
import com.mina.aiad.model.User;
import com.mina.aiad.repository.FeatureRepository;
import com.mina.aiad.repository.UserFeaturesRepository;
import com.mina.aiad.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	UserFeaturesRepository userFeatureRepo;
	
	@Autowired
	FeatureRepository featureRepo;
	
	@Autowired
	UserService userService;
	
	
	@Test
	@DisplayName("Test if new feature is added to the user")
	public void getAllFeaturesTest()
	{
		Feature f=new Feature("test feature4");
		f.setIsGlobal(1);
		featureRepo.save(f);
		User user=new User("mina", "111", "admin");
		userRepo.save(user);
		//should be >1 as global feature added to all users
		Assertions.assertFalse(userService.getAllFeatures(user.getId()).size()==0);
	}
	
	
}
