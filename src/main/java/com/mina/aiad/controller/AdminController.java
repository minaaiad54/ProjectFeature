package com.mina.aiad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mina.aiad.model.Feature;
import com.mina.aiad.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Feature>> getAllFeatures() {
		try {
			List<Feature> features = adminService.getAllFeatures();
			if (features.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(features, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addfeature")
	public ResponseEntity<Feature> createFeature(@RequestBody String name) {
		try {
			adminService.addFeature(name);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/switchon/{id}")
	public ResponseEntity<Feature> updateFeature(@PathVariable("id") long id) {
		
		boolean result=adminService.enableGlobal(id);
		if (result) {
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/switchon/{id}/user/{username}")
	public ResponseEntity<Feature> switchOnUserFeature(@PathVariable("id") long id,@PathVariable("username") String username) {
		
		boolean result=adminService.addFeatureToUser(username, id);
		if (result) {
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/switchoff/{id}/user/{username}")
	public ResponseEntity<Feature> switchOffUserFeature(@PathVariable("id") long id,@PathVariable("username") String username) {
		
		boolean result=adminService.disableFeatureToUser(username, id);
		if (result) {
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
