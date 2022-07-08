<jsp:include page="menu.jsp"/>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body, html {
  height: 100%;
  margin: 0;
}

.bg {
  /* The image used */
  background-image: url("resources/images/home.png");

  /* Full height */
  height: 100%; 

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
p
{
left-margin : 50px;
text-decoration: overline;
color : yellow;
}

</style>

</head>
<body>

<div class="bg">
<p>Welcome to the home page of our employee management service</p>
<h4 style="color: red">${msg}</h4>
</div>
</body>
</html>
