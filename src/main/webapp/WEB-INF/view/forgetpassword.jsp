
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<html>
<head>
<title>Forget-Password</title>
<style type="text/css">
.note
{
    text-align: center;
    height: 80px;
    background: -webkit-linear-gradient(left, #0072ff, #8811c5);
    color: #fff;
    font-weight: bold;
    line-height: 80px;
}
.form-content
{
    padding: 5%;
    border: 1px solid #ced4da;
    margin-bottom: 2%;
}
.form-control{
    border-radius:1.5rem;
}
.btnSubmit
{
    border:none;
    border-radius:1.5rem;
    padding: 1%;
    width: 20%;
    cursor: pointer;
    background: #0062cc;
    color: #fff;
}
</style>

</head>

<body>
<script>

       var password = document.getElementById("password")
       var confirm_password = document.getElementById("confirm_password");

            function validatePassword(){
            if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
            } else {
            confirm_password.setCustomValidity('');
          }
         }

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

</script>
<form action="updatePassword" method="post">
<div class="container register-form">
            <div class="form">
                <div class="note">
                    <p> Enter your answer to the question you have entered and new password </p>
                </div>
                     <p style="color: red;">${msg}</p>
                <div class="form-content">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Enter Username *" value="" name="username" required/>
                            </div><br>
                             <div class="form-group">
                                <input type="text" class="form-control" placeholder="Enter EmployeeId *" value="" name="employeeId" required/>
                            </div><br>
                           
                         <div class="form-group">
										<select class="form-control" name="question" required>
											<option value="">Select question*</option>

											<option class="hidden" disabled>Please select your
												Security Question</option>
											<option>What is your Birthdate?</option>
											<option>What is Your old Phone Number</option>
											<option>What is your Pet Name?</option>
										</select>
									</div><br>
                      
                        <div class="form-group">
										<input type="text" class="form-control"
											placeholder="Enter Your Answer *" value="" name="answer" required/>
									</div><br>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="Enter New Password *"  id="password" required name="password"/>
                            </div><br>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="Confirm Password *" id="confirm_password" required/>
                            </div><br>
                            <div class="form-group">
                                 <a href="getPassByEmailPage" class="btn btn-success">Get password by email</a>
                            </div><br>
                              </div>
                        </div>
                    </div>
                    <button type="submit" class="btnSubmit" value="Update">Update</button>
                     
                </div>
            </div>
        </div>
</form>

</body>
</html>
