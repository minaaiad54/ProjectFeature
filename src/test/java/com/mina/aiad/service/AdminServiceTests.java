package com.mina.aiad.service;

import java.util.List;

import org.aspectj.lang.annotation.Before;
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
public class AdminServiceTests {

	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	UserFeaturesRepository userFeatureRepo;
	
	@Autowired
	FeatureRepository featureRepo;
	
	@Autowired
	AdminService adminService;
	
	
	
	
	@Test
	@DisplayName("Test if new feature is added to the feature repo")
	public void addFeatureTest()
	{
		String featureName="test feature";
		adminService.addFeature(featureName);
		Feature featureNameInRepo=featureRepo.findByName(featureName);
		Assertions.assertEquals(featureName, featureNameInRepo.getName());
	}
	
	
	@Test
	@DisplayName("Test getting all features")
	public void getAllFeaturesTest()
	{
		Feature f=new Feature("test feature");
		featureRepo.save(f);
		List<Feature> featurelist=adminService.getAllFeatures();
		Assertions.assertNotNull(featurelist);
	}
	
	@Test
	@DisplayName("Test enable feature to user")
	public void addFeatureToUserTest()
	{
	
		Feature f=new Feature("test feature2");
		featureRepo.save(f);
		
		User user=new User("testUser", "test", "temp");
		userRepo.save(user);
		
		adminService.addFeatureToUser(user.getUsername(), f.getId());
		
		Assertions.assertNotNull(userFeatureRepo.findByUserId(user.getId()));
	}
	
	
	@Test
	@DisplayName("Test disable feature to user")
	public void disableFeatureToUserTest()
	{
	
		User user=userRepo.findByUsername("testUser");
		adminService.disableFeatureToUser(user.getUsername(), featureRepo.findByName("test feature2").getId());
		Assertions.assertTrue(userFeatureRepo.findByUserId(user.getId()).size()==0);
	}
	
	
	@Test
	@DisplayName("Test enable feature to all users")
	public void enableGlobalTest()
	{
		Feature f=new Feature("test feature3");
		featureRepo.save(f);
		adminService.enableGlobal(featureRepo.findByName("test feature3").getId());
		Assertions.assertTrue(featureRepo.findByName("test feature3").getIsGlobal()==1);
	}
}
