package com.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="COUNTRY_MASTER")
public class Country {

	@Id
	private Integer countryId;	
	private String COUNTRY_NAME;
	
	public Country() {
		
	}
	
	public Country(Integer countryId, String cOUNTRY_NAME) {
		super();
		this.countryId = countryId;
		COUNTRY_NAME = cOUNTRY_NAME;
	}
	public Integer getCOUNTRY_ID() {
		return countryId;
	}
	public void setCOUNTRY_ID(Integer cOUNTRY_ID) {
		countryId = cOUNTRY_ID;
	}
	public String getCOUNTRY_NAME() {
		return COUNTRY_NAME;
	}
	public void setCOUNTRY_NAME(String cOUNTRY_NAME) {
		COUNTRY_NAME = cOUNTRY_NAME;
	}	
	
	
}
