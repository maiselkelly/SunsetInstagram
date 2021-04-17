<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Perfect Sunset :: Register Today!</title>
<style><%@include file="/jsp/stylesheet.css"%></style>
</head>
<body>
<header>
  	<img src="https://images.immediate.co.uk/production/volatile/
		sites/4/2013/04/GettyImages-640318118-c83a508.jpg?quality=90&resize=940%2C400" height="200">
</header>
<nav class="nav">
	<ul>
		<li><a href="Login.jsp">Login Here</a></li>
		<li><a href="about">About Sunsets</a></li>
		<li><a href="mailus">Message Us</a></li>	
	</ul>
</nav>


<h1>Welcome to The Perfect Sunset</h1>

<h2>Please fill in your information to join today!</h2>
            

    <form  action="register" method="post">
     <h1 class="error">${RegistrationError}</h1>
     <br>
     <label><b>First Name:</b></label>
     <input type="text"   name="firstname" placeholder="Enter first name" size="45" value="${user.firstname}" required>
  	 <br><br>
       <label><b>Last Name:</b></label>
     <input type="text"  name="lastname" placeholder="Enter last name" size="45" value="${user.lastname}" required>
  	<br>
	<br>
       <label><b>Email Address:</b></label>
     <input type="email"  name="email" placeholder="Enter Email Address"size="45" value="${user.email}" required>
 	<br>
	<br>
       <label><b>Password:</b></label>
     <input type="password" name="password" placeholder="Enter Password" size="45" value="${user.password}" required>
  	<br><br>
  	  <label for="gender">Gender:</label>
 	 <select name="gender" id="gender">
   		 <option name="m" value="${user.gender}">Male</option>
   		 <option name="f" value="${user.gender}">Female</option>
   		 <option name="o" value="${user.gender}">Other</option>
  </select>
  
   <br><br>
   
       <label><b>Birth Date:</b></label>
     <input type="date" name="birthday" placeholder="Enter Birthday" size="45" value="${user.birthday}" required>
	<br><br>
     <button class="button" type="submit" id="register" value="register">Register!</button>
        </form>
<br><br><br>


</body>
</html>


 