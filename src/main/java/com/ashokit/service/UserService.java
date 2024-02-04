package com.ashokit.service;

import java.util.Map;

import com.ashokit.bindings.LoginForm;
import com.ashokit.bindings.RegistrationForm;
import com.ashokit.bindings.ResetPwdForm;
import com.ashokit.entity.User;


public interface UserService {

	 public Map<Integer, String> getCountries( );
	 
	 public Map<Integer, String> getStates(Integer countryId);
	 public Map<Integer, String> getCities(Integer stateId);
	 public User getUser(String email);
	public boolean saveUser(RegistrationForm formObj);
	public User login(LoginForm formObj);
	public boolean resetPwd(ResetPwdForm formObj);
	
}
