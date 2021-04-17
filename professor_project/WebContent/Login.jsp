<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>The Perfect Sunset :: Login</title>
<style><%@include file="/jsp/stylesheet.css"%></style>

</head>
<body>
		<img src="https://images.immediate.co.uk/production/volatile/
		sites/4/2013/04/GettyImages-640318118-c83a508.jpg?quality=90&resize=940%2C400" height="200">
<nav class="nav">
	<ul>
		<li><a href="Registration.jsp">Register Today</a></li>
		<li><a href="about">About Sunsets</a></li>
		<li><a href="mailus">Message Us</a></li>	
	</ul>
</nav>




<h1>Log In to The Perfect Sunset</h1>
<h2>Sunsets are only one click away!</h2>
<form  action="login" method="post">

	<label>Email Address</label>
	<input type="text"  placeholder="Enter Email Address" name="email" value="${user.email}" required>
	<br><br>

	<label>Password</label>
	<input type="password" placeholder="Enter Password" name="password" value="${user.password}" required>
	<br>

	<h1 class="error">${loginError}</h1>
	<br>
	<button class="button" type="submit" id="login" value="login">Log in!</button>

</form>
<br><br><br>

</body>
</html>
