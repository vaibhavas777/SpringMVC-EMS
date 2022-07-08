<html>

<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>get-password</title>

</head>

<body>
<h1 class="p-3 mb-2 bg-info text-white">Get password by email<span class="badge badge-secondary"> VAS </span></h1>

<hr>

<form action="getPassByEmail" method="post" class="text-primary" class="p-3 mb-2 bg-dark text-white">


  <div  class="container" >
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" ><br>
     <input type="text" class="form-control" placeholder="Enter EmployeeId *" value="" name="employeeId" required/><br>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email or id with anyone else.</small> 
     <small id="emailHelp" class="form-text text-muted">Ensure that you have entered correct email & employee id.</small> <br>
     <button type="submit" class="btn btn-primary">Submit</button> <br><br>
     <p style="color: red;">${msg}</p>
     <small id="emailHelp" class="form-text text-muted">Go to login page if you get your password, link given below</small><br>
      <a href="/" class="btn btn-success">Login</a>
  </div>
    
</form>

</body>
</html>