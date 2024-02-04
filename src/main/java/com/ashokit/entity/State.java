package com.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="STATE_MASTER")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer STATE_ID;      
    private String STATE_NAME;    
    private Integer countryId; 
    
    public State() {
    	
    }

    public State(Integer sTATE_ID, String sTATE_NAME, Integer countryId) {
		super();
		STATE_ID = sTATE_ID;
		STATE_NAME = sTATE_NAME;
		this.countryId = countryId;
	}

	public Integer getSTATE_ID() {
        return STATE_ID;
    }

    public void setSTATE_ID(Integer sTATE_ID) {
        STATE_ID = sTATE_ID;
    }

    public String getSTATE_NAME() {
        return STATE_NAME;
    }

    public void setSTATE_NAME(String sTATE_NAME) {
        STATE_NAME = sTATE_NAME;
    }

    public Integer getCOUNTRY_ID() {
        return countryId;  // Corrected getter method
    }

    public void setCOUNTRY_ID(Integer cOUNTRY_ID) {
        countryId = cOUNTRY_ID;  // Corrected setter method
    }
}
