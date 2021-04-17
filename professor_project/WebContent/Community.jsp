<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>The Perfect Sunset :: Community</title>
<style><%@include file="/jsp/stylesheet.css"%></style>
</head>
<body>

<img src="https://images.immediate.co.uk/production/volatile/
		sites/4/2013/04/GettyImages-640318118-c83a508.jpg?quality=90&resize=940%2C400" height="200px">

<nav class="nav">
	<ul>
		<div class=search">
		<form action="searchUsersPosting">
		<li><input type="text" name="searchFor" placeholder="Search Username"> </li>
		<li><button type="submit">Submit</button></li>
		</form>
		</div>
			<li><a href="home.jsp">Home</a></li>
		<li><a href="imageFeed">Sunset Feed</a></li>
		<li><a href="sessionImage">Post a Sunset</a></li>
		
	</ul>
</nav>

		<h1>All Users Registered to The Perfect Sunset</h1>
		
		 <div align="center">
        <table id="users" border="1" cellpadding="5">
            
            <tr>       
                <th>Username</th>
                <th>Follow User</th>          
                <th>Unfollow User</th>
            </tr>
            
            <c:forEach var="user" items="${listUsers}">
                <tr>
                    <td ><c:out value="${user.getEmail()}" /></td>
                    <td class=Like" ><a  href="insertFollower?followingemail=<c:out value='${user.getEmail()}' />">Follow</a></td>
                    <td><a  href="deleteFollower?followingemail=<c:out value='${user.getEmail()}' />">Unfollow</a></td>
               </tr>
            </c:forEach>
            
       
            
            
        </table>
    </div>         




</body>
</html>