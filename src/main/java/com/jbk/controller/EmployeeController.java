package com.jbk.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jbk.entity.Employee;
import com.jbk.service.EmployeeService;

// ctrl+shift+o to remove unused imports

@Controller
@RequestMapping()
public class EmployeeController {
	
	//Employee Controller to check request and response
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);


	@Autowired EmployeeService service;

	@PostMapping(value = "/loginProcess")
	public ModelAndView loginProcess(@ModelAttribute Employee employee, HttpSession session)

	{   
		log.info("inside loginProcess method , processing user login for : "+employee.getUsername());
		Employee emp = service.login(employee);

		if(emp != null) 
		{
			session.setAttribute("username", emp.getUsername());
			session.setAttribute("eid", emp.getEmployeeId());
			session.setAttribute("role", emp.getRole());
			session.setAttribute("status", emp.getStatus());
			return new ModelAndView("home");
		}

		else
		{   
			log.warn("Attention ! employee is null");
			return new ModelAndView("login1", "msg", "invalid credential !!!");
		}


	}

	@PostMapping(value = "saveEmployee")                           
	public ModelAndView saveEmployee(@ModelAttribute Employee employee)
	{    log.trace("role", employee.getRole());
		Employee emp = service.saveEmployee(employee);
		if(emp != null)
		{
			return new ModelAndView("addEmployee", "msg", "saved successfully !!");
		}
		else 
		{
			return new ModelAndView("addEmployee", "msg", "Something went wrong !!");
		}

	}
	@GetMapping(value = "/getEmployee")
	public ModelAndView getEmployee(@RequestParam String eid,@RequestParam String msg,Model model){

		Employee employee = service.getEmployeeById(eid);
		if(!msg.equalsIgnoreCase("null"))
		{
			model.addAttribute("msg", msg);
		}
		model.addAttribute("employee", employee);

		return new ModelAndView("profile");

	}

	@GetMapping(value="/getAllEmployees")
	public ModelAndView getAllEmployees(HttpSession session, Model model) {

		String status = (String) session.getAttribute("status");
		if(status != null) {
			List<Employee> list = service.getAllEmployees();
			return new ModelAndView("employeeList", "employees", list);
		}
		else {
			model.addAttribute("msg", "Please login first !!!!");
			return new ModelAndView("login1");
		}

	}
	//using request parameter
	@GetMapping(value = "/deleteEmployee")
	public String deleteEmployee(@RequestParam String eid)
	{
		boolean b = service.deleteEmployee(eid);
		if(b) {
			return "redirect:/getAllEmployees";
		}else {
			return "redirect:/getAllEmployees";
		}

	}
	@PostMapping(value = "/updateEmployee")
	public ModelAndView updateEmployee(@ModelAttribute Employee employee) {
		Employee emp = service.updateEmployee(employee);
		if(emp!=null) {
			return  new ModelAndView("redirect:/getEmployee?eid="+employee.getEmployeeId()+"&msg=Updated");
		}
		else {
			return new ModelAndView("redirect:/getEmployee?eid="+employee.getEmployeeId()+"&msg=Not Updated");
		}

	}
	@RequestMapping(value = "/logOut")
	public String logout(HttpSession session , @RequestParam String eid) {

		service.logout(eid, session);
		return "login1";

	}
	@PostMapping(value ="/uploadSheet")
	public ModelAndView uploadSheet(@RequestParam CommonsMultipartFile file , HttpSession session) {
		System.out.println("in file");
		String msg = service.uploadSheet(file, session);
		return new ModelAndView("importEmployee", "msg", msg);	
	}
	
	@GetMapping
	public ResponseEntity<Resource> getFile(){
		String filename = "tutorials.xlsx";
		
	    return (ResponseEntity<Resource>) ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename).contentType(MediaType.parseMediaType("application/vnd.ms-excel"));

	}
	
	@PostMapping(value= "/updatePassword")
	public ModelAndView updatePassword(@ModelAttribute Employee employee , Model model) {
        System.out.println(employee);
		Employee emp=service.updatePassword(employee);
		System.out.println(emp);
		if(emp!=null) {
			return new ModelAndView("forgetpassword", "msg", "password changed !!!!");
		}
		else {
			return new ModelAndView("forgetpassword", "msg", "wrong input, check details !!!!");
		}
	}
	@PostMapping(value ="/getPassByEmail")
	public ModelAndView getEmail(@RequestParam String email,@RequestParam String employeeId, Model model) {
		
		boolean b = service.getEmail(email,employeeId);
		if(b) {
			return new ModelAndView("getPassByEmail", "msg", "email sent successfully....");
		}
		else {
			return new ModelAndView("getPassByEmail", "msg", "email not sent....");
		}
		
		
	}
}
