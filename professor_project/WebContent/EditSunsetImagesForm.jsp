<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Perfect Sunset :: Post</title>
<style><%@include file="/jsp/stylesheet.css"%></style>

</head>
<body>
		<img src="https://images.immediate.co.uk/production/volatile/
		sites/4/2013/04/GettyImages-640318118-c83a508.jpg?quality=90&resize=940%2C400" height="200">
<nav class="nav">
	<ul>
		<div class=search">
		<form action="searchUsersPosting">
		<li><input type="text" placeholder="Search Username"> </li>
		<li><button type="submit">Submit</button></li>
		</form>
		</div>
		<li><a href="home.jsp">Home</a></li>
			<li><a href="listUsers">Sunset Community</a></li>
		<li><a href="imageFeed">Sunset Feed</a></li>
			
	</ul>
</nav>


    <div align="center">
 
        
        
        <form  action="updateImage" method="post">
     <br>
     <input type="hidden" name="imageid" value="<c:out value="${param['imageid']}" />" />
     <label><b>Image URL:</b></label>
     <input type="text"   name="imageURL" placeholder="Enter Sunset URL" size="45" value="${image.url}" required>
  	 <br><br>
       <label><b>Description:</b></label>
     <input type="text"  name="description" placeholder="Give a simple description" size="45" value="${image.description}" required>
  		 <br><br>
     
	
	
     <button class="button" type="submit" id="postSunset" value="postSunset">Post Your Sunset!</button>
        </form>
        
    </div>   
</body>
</html>