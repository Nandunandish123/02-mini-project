package com.ashokit.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashokit.bindings.LoginForm;
import com.ashokit.bindings.RegistrationForm;
import com.ashokit.bindings.ResetPwdForm;
import com.ashokit.entity.User;
import com.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService uservice;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("login", new LoginForm());
		return "index";
	}
	@PostMapping("login")
	public String login(@ModelAttribute("login") LoginForm form, Model model) {
		  User user =  uservice.login(form);
		  System.out.println(user);
		  if(user == null) {
			  model.addAttribute("errmsg","invalid credential");
			  return "index";
		  }
		  
		  if(user.getPwdUpdated().equals("NO")) {
			 ResetPwdForm formObj = new ResetPwdForm();
			 formObj.setUserId(user.getUserId());
			      model.addAttribute("resetpwd", formObj);
			  return "resetPwd";
		  }
		return "redirect:dashboard";
	}
	@PostMapping("/updatePwd")
	public String updatePwd(@ModelAttribute("resetPwd") ResetPwdForm resetpwdform, Model model) {
		
		if(!resetpwdform.getNewPwd().equals(resetpwdform.getConfirmPwd())) {
			 model.addAttribute("errmsg", "both pwd should be same");
			   return "resetPwd";
		}
		
		   boolean status = uservice.resetPwd(resetpwdform);
		   
		   if(status) {
			   return "redirect:dashboard";
		   }
		   
		   model.addAttribute("errmsg", "password update failed");
		   return "resetPwd";
	}
	
	 @GetMapping("/register")
	public String loadRegistration(Model model) {
		 model.addAttribute("registerform", new RegistrationForm());
		 Map<Integer, String>  countries =  uservice.getCountries();
		     model.addAttribute("countries" ,countries);
		 
		 return "register";
	}
	 @GetMapping("/getStates")
	 @ResponseBody
	 public Map<Integer, String> getStates(@RequestParam("countryId") Integer countryId){
		 return uservice.getStates(countryId);
	 }
	 @GetMapping("/getCities")
	 @ResponseBody
	 public Map<Integer, String> getCities(@RequestParam("stateId") Integer stateId){
		 return uservice.getCities(stateId);
	 }
	 
	 @PostMapping("/register")
	 public String registerUser(@ModelAttribute RegistrationForm registerform, Model model) {
		 
		    boolean saveuser = uservice.saveUser(registerform);
		    
		    if(saveuser) {
		    	model.addAttribute("smsg", "user registered successfully");
		    }else {
		    	 model.addAttribute("errmsg", "user registration failed");
		    }
		    Map<Integer, String>  countries =  uservice.getCountries();
		     model.addAttribute("countries" ,countries);
		     
		     return "register";
	 }
}
