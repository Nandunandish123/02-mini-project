package com.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ashokit.service.DashboardService;

@Controller
public class DashboardController {

	@Autowired
	private DashboardService dbservice;
	
	@GetMapping("/dashboard")
	 public String buildDashboard(Model model) {
		  String textquote   =    dbservice.getQuote();
		model.addAttribute("quote", textquote);
		return "dashboard";
	 }
	
	public String getNewQuote( ) {
		 return "";
	 }
}
