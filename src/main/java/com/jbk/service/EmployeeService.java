package com.jbk.service;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.entity.Employee;


public interface EmployeeService {

	public Employee login(Employee employee);
	public Employee saveEmployee(Employee Employee);
	public Employee getEmployeeById(String EmployeeId);
	public List<Employee> getAllEmployees();
	public boolean deleteEmployee(String EmployeeId);
	public Employee updateEmployee(Employee Employee);	
	public void logout(String employeeId, HttpSession session);
	public String uploadSheet(CommonsMultipartFile file, HttpSession session);
	public Employee updatePassword(Employee employee);
	public boolean getEmail(String email, String employeeId);
	public CommonsMultipartFile getFile();
	
}
