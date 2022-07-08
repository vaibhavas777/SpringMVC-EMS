package com.jbk.dao;

import java.util.List;

import com.jbk.entity.Employee;

public interface EmployeeDao {

	public Employee login(Employee employee);
	public Employee saveEmployee(Employee employee) ;
	public Employee getEmployeeById(String employeeId);
	public List<Employee> getAllEmployees();
	public boolean deleteEmployee(String employeeId);
	public Employee updateEmployee(Employee employee);
	public void logout(String employeeId);
	public String saveEmployees(List<Employee> list);
	public Employee updatePassword(Employee employee);
	public Employee getEmail(String email,String employeeId);
	
}
