package com.mina.aiad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mina.aiad.model.Feature;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

	Feature findById(long featureId);
	List<Feature> findByIsGlobal(int isGlobal);
	Feature findByName(String name);
}
