package com.jbk.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.jbk.dao.EmployeeDao;
import com.jbk.dao.EmployeeDaoImpl;
import com.jbk.entity.Employee;

@Service
public class EmployeeService_Impl implements EmployeeService {

	@Autowired 
	private EmployeeDao dao;

	@Override
	public Employee saveEmployee(Employee Employee) {
		String EmployeeId = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
		Employee.setEmployeeId(EmployeeId);
		String cratedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Date date = null;
		try 
		{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cratedDate);
		} 
		catch (ParseException e) 
		{

			e.printStackTrace();
		}
		Employee.setCreatedDate(date);
		Employee emp =dao.saveEmployee(Employee);
		return emp;
	}

	@Override
	public Employee getEmployeeById(String EmployeeId) {
		Employee std =dao.getEmployeeById(EmployeeId);
		return std;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = dao.getAllEmployees();
		return list;
	}

	@Override
	public boolean deleteEmployee(String EmployeeId) {
		boolean b = dao.deleteEmployee(EmployeeId);
		return b;
	}

	@Override
	public Employee updateEmployee(Employee Employee) {
		String cratedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Date date = null;
		try 
		{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cratedDate);
		} 
		catch (ParseException e) 
		{

			e.printStackTrace();
		}
		Employee.setCreatedDate(date);
		Employee std = dao.updateEmployee(Employee);
		return std;
	}

	@Override
	public Employee login(Employee employee) {
		Employee emp = dao.login(employee);
		return emp;
	}

	@Override
	public void logout(String employeeId, HttpSession session) {
		dao.logout(employeeId);
		session.invalidate(); //session ( log ) out 
	}

	public List<Employee> readExcel(String filePath){
		List<Employee> list = new ArrayList<>();

		try 
		{
			FileInputStream fis = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			Employee employee = null;

			while(rows.hasNext()) {
				employee = new Employee();
				// generating the employeeId
				String employeeId = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
				employee.setEmployeeId(employeeId);
				
				// generating the createdDate
				String cratedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				Date date = null;
				try 
				{
					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cratedDate);
				} 
				catch (ParseException e) 
				{

					e.printStackTrace();
				}
				employee.setCreatedDate(date);
				
				Row row=rows.next();
				Iterator<Cell>cells = row.cellIterator();

				while(cells.hasNext()) {
					Cell cell = cells.next();
					int colIndex= cell.getColumnIndex();
					if(cell.getCellType() == CellType.NUMERIC) {
						cell.setCellType(CellType.STRING);

					}

					switch (colIndex) {
					case 0:
						employee.setUsername(cell.getStringCellValue());
						break;
					case 1:
						employee.setPassword(cell.getStringCellValue());
						break;
					case 2:
						employee.setDepartment(cell.getStringCellValue());
						
						break;
					case 3:
						employee.setGender(cell.getStringCellValue());
						
						break;
					case 4:
						employee.setRole(cell.getStringCellValue());
						break;
					case 5:
						employee.setEmail(cell.getStringCellValue());
						
						break;
					case 6:
						Long salary = Long.parseLong(cell.getStringCellValue());
						employee.setSalary(salary);
						break;
					case 7:
						employee.setPhone(cell.getStringCellValue());
						break;
					case 8:
						employee.setQuestion(cell.getStringCellValue());
						break;
					case 9:
						employee.setAnswer(cell.getStringCellValue());
						break;

					default:
						break;
					}
					
				}
				list.add(employee);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
 
	// UPLOAD SHEET
	@Override
	public String uploadSheet(CommonsMultipartFile file, HttpSession session) {
		
		String msg = null;
		String path = session.getServletContext().getRealPath("WEB-INF/uploaded");
		System.out.println("path : "+path);

		String fileName = file.getOriginalFilename();
		System.out.println("fileName : "+fileName);
        
		//Return the contents of the file as an array of bytes
		byte[] data = file.getBytes();
		
		try {
			//Creates a file output stream to write to the file with the specified name
			
			FileOutputStream fos = new FileOutputStream(new File(path+ File.separator+fileName));
			fos.write(data); //uploaded

			List<Employee> list = readExcel(path+ File.separator+fileName);
			
			msg=dao.saveEmployees(list);
			

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "done";
	}
	

	
	
	@Override
	public Employee updatePassword(Employee employee) {
		Employee emp=dao.updatePassword(employee);
		return emp;
	}

	@Override
	public boolean getEmail(String email,String employeeId) {
	Employee emp =	dao.getEmail(email,employeeId);
	boolean b = false;
	if(emp != null)
	{
		String to = email;
		String from = "vaibhav.mitujjain07@gmail.com";
		String subject = "Hello "+emp.getUsername();
		String message = "Dear "+ emp.getUsername()+" Your orignal password is "+ "("+ emp.getPassword()+
				")"+" ,Do not share this message with anyone else.";
		//Variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties prop = System.getProperties();
		System.out.println("PROPERTIES "+prop);
		
		//setting important information to properties object
		
	    //host set
		
		prop.put("mail.smtp.user", from);
		prop.put("mail.smtp.host",host);
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.ssl.enable","true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		
		Session session=Session.getInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("vaibhav.mitujjain07@gmail.com", "bnyrysflvpledayx");
			}
			//ssatishujn.07@gmail.com
			//20220618173834295
				
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage msg = new MimeMessage(session);
		
		try
		{
			// from email
			msg.setFrom(new InternetAddress(from));
			
			// adding recipient
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// adding subject to message
			msg.setSubject(subject);
			
			//adding text to message
			msg.setText(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Send the message
		
		try {
			Transport.send(msg);
			b=true;
			System.out.println("message sent successfully.....");
			return b;
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return b;
	}
	else
	{
		return b;
	}
			
	}

	@Override
	public CommonsMultipartFile getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public CommonsMultipartFile getFile(HttpSession session) throws
	 * FileNotFoundException { List<Employee> list = dao.getListForFile(); String
	 * fileName = "employeeData.xlsx"; String path =
	 * session.getServletContext().getRealPath("WEB-INF/uploaded");
	 * CommonsMultipartFile file = null; byte[] data = file.getBytes();
	 * 
	 * //Creates a file output stream to write to the file with the specified name
	 * 
	 * FileOutputStream fos = new FileOutputStream(new File(path+
	 * File.separator+fileName)); try { fos.write(data); } catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } return null; }
	 * 
	 * 
	 */
	


}


