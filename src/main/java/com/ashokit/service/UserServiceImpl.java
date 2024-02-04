package com.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.bindings.LoginForm;
import com.ashokit.bindings.RegistrationForm;
import com.ashokit.bindings.ResetPwdForm;
import com.ashokit.entity.City;
import com.ashokit.entity.Country;
import com.ashokit.entity.State;
import com.ashokit.entity.User;
import com.ashokit.repo.CityRepo;
import com.ashokit.repo.CountryRepo;
import com.ashokit.repo.StateRepo;
import com.ashokit.repo.UserRepo;
import com.ashokit.utils.EmailUtils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepo crepo;
	
	@Autowired
	private StateRepo srepo;
	
	@Autowired 
	private CityRepo cityrepo;
	
	@Autowired
	private UserRepo urepo;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Override
	public Map<Integer, String> getCountries() {
   
		Map<Integer, String> countries = new HashMap<>();
		    List<Country>  findall =  crepo.findAll();
             findall.forEach(c ->{
            	countries.put(c.getCOUNTRY_ID(),c.getCOUNTRY_NAME());
             });
		return countries;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> states = new HashMap<>();
		          List<State>  stateslist = srepo.findByCountryId(countryId);
		      stateslist.forEach(s ->{
		    	  states.put(s.getSTATE_ID(), s.getSTATE_NAME());
		      });
		return states;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> cities = new HashMap<>();
		List<City> citieslist = cityrepo.findByStateId(stateId);
		
		citieslist.forEach(c ->{
			cities.put(c.getCityId(), c.getCityName());
		});
		return cities;
	}

	@Override
	public User getUser(String email) {
		return urepo.findByEmail(email);
	}

	@Override
	public boolean saveUser(RegistrationForm formObj) {
		
		formObj.setPwd(generateRandomPwd());
		formObj.setPwdUpdated("NO");
		 User userEntity = new User();
		 
		 BeanUtils.copyProperties(formObj, userEntity);
		 urepo.save(userEntity);
		 
		 String subject = "your account created - ashok it ";
		 String body = "your password"+formObj.getPwd();
		 
		return emailutils.sendEmail(subject, body, formObj.getEmail());
		
	}

	private String generateRandomPwd() {
		  String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuv";
		  
		    StringBuffer randomString = new StringBuffer(5);
		    Random random = new Random();
		 
		    for (int i = 0; i < 5; i++) {
		        int randomIndex = random.nextInt(alphanumericCharacters.length()-1);
		        char randomChar = alphanumericCharacters.charAt(randomIndex);
		        randomString.append(randomChar);
		    }
		 
		    return randomString.toString();
		
	}

	@Override
	public User login(LoginForm formObj) {
		return  urepo.findByEmailAndPwd(formObj.getEmail(), formObj.getPwd());
	}

	@Override
	
	public boolean resetPwd(ResetPwdForm formObj) {
	Optional<User> findById = urepo.findById(formObj.getUserId());
	
	   if(findById.isPresent()) {
		         User user = findById.get();
		         user.setPwd(formObj.getNewPwd());
		         user.setPwdUpdated("yes");
		         urepo.save(user);
		         return true ;
	   }
		return false;
	}

}
