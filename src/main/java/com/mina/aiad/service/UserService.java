package com.mina.aiad.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mina.aiad.model.Feature;
import com.mina.aiad.model.User;
import com.mina.aiad.model.UserFeatures;
import com.mina.aiad.repository.FeatureRepository;
import com.mina.aiad.repository.UserFeaturesRepository;
import com.mina.aiad.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	UserFeaturesRepository userFeatureRepo;
	
	@Autowired
	FeatureRepository featureRepo;
	
	
	public  List<Feature> getAllFeatures(long userId)
	{
		ArrayList<Feature> result=new ArrayList<Feature>();
		
		
		List<UserFeatures> userFeatures=userFeatureRepo.findByUserId(userId);
		List<Feature> globalFeatures=featureRepo.findByIsGlobal(1);
		result.addAll(globalFeatures);
		if(userFeatures!=null && userFeatures.size()>0)
		{
			for(int i=0;i<userFeatures.size();i++)
			{
				Feature f=featureRepo.findById(userFeatures.get(i).getFeatureId());
				if(f!=null)
					result.add(f);
			}
		}
		
		return result;
		
	}
	
	public User createUser(User user)
	{
		return userRepo.saveAndFlush(user);
	}
	
	
	public List<User> getAll()
	{
		return userRepo.findAll();
	}
}
