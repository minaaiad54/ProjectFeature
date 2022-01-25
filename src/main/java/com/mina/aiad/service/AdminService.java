package com.mina.aiad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mina.aiad.model.Feature;
import com.mina.aiad.model.User;
import com.mina.aiad.model.UserFeatures;
import com.mina.aiad.repository.FeatureRepository;
import com.mina.aiad.repository.UserFeaturesRepository;
import com.mina.aiad.repository.UserRepository;

@Service
public class AdminService {

	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	UserFeaturesRepository userFeatureRepo;
	
	@Autowired
	FeatureRepository featureRepo;
	
	public void addFeature(String name)
	{
		Feature f=new Feature(name);
		featureRepo.save(f);
	}
	
	public List<Feature> getAllFeatures()
	{
		return featureRepo.findAll();
	}
	
	public boolean addFeatureToUser(String username,long featureId)
	{
		boolean result=true;
		User user=userRepo.findByUsername(username);
		if(user==null)
			return false;
		
		UserFeatures userFeatures=new UserFeatures(user.getId(), featureId);
		userFeatureRepo.save(userFeatures);
		return result;
	}
	
	
	public boolean disableFeatureToUser(String username,long featureId)
	{
		boolean result=true;
		User user=userRepo.findByUsername(username);
		if(user==null)
			return false;
		List<UserFeatures> userFeatures=userFeatureRepo.findByUserId(user.getId());
		if(userFeatures==null)
			return false;
		userFeatureRepo.deleteAll(userFeatures);
		
		return result;
	}
	
	public boolean enableGlobal(long featureId)
	{
		boolean result=true;
		
		Feature f=featureRepo.findById(featureId);
		if(f==null)
			return false;
		f.setIsGlobal(1);
		featureRepo.save(f);
		
		return result;
	}
}
