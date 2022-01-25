package com.mina.aiad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	private int isGlobal;

	public Feature(String name)
	{
		this.name=name;
		isGlobal=0;
		
	}
	
	
	public void setIsGlobal(int isGlobal) {
		this.isGlobal = isGlobal;
	}


	protected Feature() {
	}

	public String getName() {
		return name;
	}

	public int getIsGlobal() {
		return isGlobal;
	}

}
