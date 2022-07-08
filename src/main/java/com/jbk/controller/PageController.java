package com.jbk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
// This is just to direct navigate
	@RequestMapping(value = "/")
	public String loginPage()
	{
		return "login1";	
	}

	@RequestMapping(value = "/addEmployeePage")
	public String addEmployeePage(HttpSession session , Model model)
	{
		String status = (String) session.getAttribute("status");
		if(status != null) {
			return "addEmployee";
		}
		else {
			model.addAttribute("msg", "Please login first !!!!");
			return "login1";
		}

	}

	@RequestMapping(value="/homePage")
	public String getHomePage(HttpSession session , Model model) {
		String status = (String) session.getAttribute("status");
		if(status != null) {
			return "home";
		}
		else {
			model.addAttribute("msg", "Please login first !!!!");
			return "login1";
		}
	}
	@RequestMapping(value ="/importEmployee")
	public String importEmployee(HttpSession session , Model model) {
		
		String status = (String) session.getAttribute("status");
		if(status != null) {
			return "importEmployee";
		}
		else {
			model.addAttribute("msg", "Please login first !!!!");
			return "login1";
		}
		
	}
	@GetMapping(value ="/forgetPasswordPage")
	public String forgetPasswordPage() {
		return "forgetpassword";
	}
	
	@GetMapping(value = "/getPassByEmailPage")
	public String getPassByEmailPage() {
		return "getPassByEmail";
		
	}
}
