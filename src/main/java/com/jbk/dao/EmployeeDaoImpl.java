package com.jbk.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);


	@Autowired
	SessionFactory factory;
	// Save Employee API
	@Override
	public Employee saveEmployee(Employee Employee) {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try 
		{
			session.save(Employee);
			tr.commit();
		} 
		catch (Exception e) 
		{
			log.error("System exception occurred during employe register process",e);
			return null;
		}
		finally
		{
			session.close();
		}
		return Employee;
	}
	//Get Employee by ID
	@Override
	public Employee getEmployeeById(String EmployeeId) {
		Session session = factory.openSession();
		Employee Employee = null;
		try 
		{
			Employee = session.get(Employee.class, EmployeeId);

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return Employee;
	}
	// Get all Employee (Employee list)
	@Override
	public List<Employee> getAllEmployees() {
		Session session =factory.openSession();
		List<Employee> listEmployee = null;

		//try catch is to close session
		try 
		{
			Criteria ctr = session.createCriteria(Employee.class);
			listEmployee=ctr.list();	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			session.close();	
		}
		return listEmployee;
	}
	// Api to delete Employee by ID
	@Override
	public boolean deleteEmployee(String EmployeeId) {

		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		boolean b = false;

		try {

			Employee Employee = session.load(Employee.class, EmployeeId);
			session.delete(Employee);
			tr.commit();
			b= true;

		} 
		catch (Exception e) {
			e.printStackTrace();
			b= false;
		}
		finally {
			session.close();
		}

		return b;
	}
	@Override
	public Employee updateEmployee(Employee Employee) {
		Session session = factory.openSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.update(Employee);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

		return Employee;
	}


	
	@Override
	public Employee login(Employee employee) {
		log.info("employee obj coming form client side", employee);
		System.err.println("dao"+employee);
		Session session = factory.openSession();
		Employee emp = null;
        Transaction tr = session.beginTransaction();
		try 
		{
			System.err.println("dao"+employee);
			Criteria ctr = session.createCriteria(Employee.class);
			Criterion username = Restrictions.eq("username", employee.getUsername());
			Criterion password = Restrictions.eq("password", employee.getPassword());
			// eq = where
		    ctr.add(Restrictions.and(username, password));// and means both should be checked

			//ctr.list(); or
			emp = (Employee) ctr.uniqueResult();
			emp.setStatus("active");
			session.update(emp);
			tr.commit();
			log.info("updated employee object coming from DB", emp);
		} 
		catch (Exception e) 
		{
			log.error("System exception occurred during employee login process",e);
		}
		finally 
		{
			session.close();
		}

		return emp;
	}
	@Override
	public void logout(String employeeId) {
		
		Employee employee = getEmployeeById(employeeId);
		employee.setStatus("inactive");
		updateEmployee(employee);
		
	}
	@Override
	public String saveEmployees(List<Employee> list) {
		
		int count = 0;
		for (Employee employee : list) {
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			try 
			{
				session.save(employee);
				tr.commit();
				count = count+1;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return "Something wrong while uploading sheet !!!";
			}
			finally
			{
				session.close();
			}
			
		}
		return count+" Record inserted !!!";
			
	}
	@Override
	public Employee updatePassword(Employee employee) {
	    System.out.println("inside update password  method of DAO==============");
		
		Employee emp = null;
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try
		{
			Criteria ctr = session.createCriteria(Employee.class);
			Criterion employeeId = Restrictions.eq("employeeId", employee.getEmployeeId());
			Criterion username = Restrictions.eq("username", employee.getUsername());
			Criterion question = Restrictions.eq("question", employee.getQuestion());
			Criterion answer = Restrictions.eq("answer", employee.getAnswer());
			
			ctr.add(Restrictions.and(employeeId,username,question,answer));
			emp = (Employee) ctr.uniqueResult();
			emp.setPassword(employee.getPassword());
			session.update(emp);
			tr.commit();
			System.out.println(emp);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		finally 
		{
			session.close();
		}
		
		return emp;
	}
	@Override
	public Employee getEmail(String email,String employeeId) {
		Session session = factory.openSession();
		Employee emp = null;
		emp = session.get(Employee.class, employeeId);
		log.info(employeeId);
		
		if(email.equals(emp.getEmail()) && employeeId.equals(emp.getEmployeeId())) {
			return emp;
		}
		else 
		{   
			return null;
		}
		
	}
	
	public List<Employee> getListForFile(){
		
		Session session = factory.openSession();
		Criteria ctr = session.createCriteria(Employee.class);
		List<Employee> list = ctr.list();
		
		return list;
		
	}
}


