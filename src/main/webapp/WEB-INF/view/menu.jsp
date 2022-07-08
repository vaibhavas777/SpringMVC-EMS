<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
<a class="navbar-brand" style="color:white;">VAS</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item ">
        <a class="nav-link" href="homePage">Home</a>
      </li>
      
      <% String role = (String)session.getAttribute("role");
      if(role.equalsIgnoreCase("Admin")){
    	  %>
    	  <li class="nav-item">
    	   <a class="nav-link" href="addEmployeePage">Add Employee</a>
    	     </li>
    	  <% 
      }
      %>
      
      <%
String msg=(String)request.getAttribute("msg");
if(msg!=null){
	%>
	<script type="text/javascript">

	alert("${msg}")
	</script>
<%	
}
%>
      
      
    	      <li class="nav-item">
              <a class="nav-link " href="getAllEmployees">Employee List</a>
             </li>
    
      <li class="nav-item">
        <a class="nav-link" href="getEmployee?eid=<%=session.getAttribute("eid")%>&msg=null">Profile-<%=session.getAttribute("username")%></a>
      </li>
      
          <li class="nav-item">
          <a class="nav-link " href="importEmployee">Import</a>
          </li>
      
      <li class="nav-item">
        <a class="nav-link " href="logOut?eid=<%= session.getAttribute("eid") %>">Logout</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link " href="#" disabled >Status-<%= session.getAttribute("status") %></a>
      </li>
    </ul>
  </div>
</nav>
</body>
</html>