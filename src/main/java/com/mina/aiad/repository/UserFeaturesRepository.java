package com.mina.aiad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.mina.aiad.model.UserFeatures;

@Repository
public interface UserFeaturesRepository extends JpaRepository<UserFeatures, Long>  {
	
	List<UserFeatures> findByUserId(long userId);
	

}
