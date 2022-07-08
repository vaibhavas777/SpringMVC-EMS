<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<jsp:include page="menu.jsp"/>
<html>
<h1>Employee List</h1><head>
<style>
h1 {
  color: #666;
  margin: 20px 10px 0px;
  margin-top: 25px;
  padding: 0px 30px 0px 30px;
  text-align: center;
}
</style>
<body style="background-color:rgb(242, 220, 22);">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
 rel="stylesheet" id="bootstrap-css">
<title>List</title>
</head>
<body>
<% String role = (String) session.getAttribute("role"); %>
<table class="table table-dark">
  <thead>
    <tr>
    
      <th scope="col">ID</th>
      <th scope="col">Username</th>
     <% if(role.equalsIgnoreCase("admin")){
    	 %>
    	 <th scope="col">Password</th>
    	<% 
    }
    %>
      <th scope="col">Gender</th>
      <th scope="col">Email</th>
        
         <% if(role.equalsIgnoreCase("admin")){
    	      %>
    	         <th scope="col">Phone</th>
    	     <% 
           }
         %>
      
      <th scope="col">Department</th>
      <th scope="col">Role</th>
      
       <% if(role.equalsIgnoreCase("admin")){
    	      %>
    	         <th scope="col">Salary</th>
    	     <% 
           }
         %>
      
      <th scope="col">Created Date</th> 
      
      <% if(role.equalsIgnoreCase("admin")){
    	      %>
    	          <th scope="col">Action</th> 
    	     <% 
           }
         %>
     
    </tr>
 
  </thead>
  <tbody>
    <c:forEach items="${employees}" var="emp"> 
     <tr>
      <td><c:out value="${emp.employeeId}"></c:out></td>
      <td><c:out value="${emp.username}"></c:out></td>
      <% if(role.equalsIgnoreCase("admin")){
    	      %>
    	          <td><c:out value = "${emp.password }"></c:out></td>
    	     <% 
           }
         %>
      
       <td><c:out value = "${emp.gender }"></c:out></td>
      <td><c:out value = "${emp.email }"></c:out></td>
      <% if(role.equalsIgnoreCase("admin")){
    	      %>
    	          <td><c:out value = "${emp.phone }"></c:out></td>
    	     <% 
           }
         %>
      
       <td><c:out value = "${emp.department }"></c:out></td>
      <td><c:out value = "${emp.role }"></c:out></td>
      <% if(role.equalsIgnoreCase("admin")){
    	      %>
    	            <td><c:out value = "${emp.salary }"></c:out></td>
    	     <% 
           }
         %>
    
      <td><c:out value = "${emp.createdDate }"></c:out></td>
      
          <% if(role.equalsIgnoreCase("admin")){
    	      %>
    	          
               <td><a href="getEmployee?eid=${emp.employeeId}&msg=null"
                class="btn btn-warning">Edit</a></td> 
                
               <td><a class="btn btn-danger" 
               href="deleteEmployee?eid=${emp.employeeId}" 
               onclick="return confirm('Are you sure ?')">Delete</a></td>
    	     <% 
           }
         %>
   </tr>
    </c:forEach>
    
    
  </tbody>
</table>

</body>
</html>