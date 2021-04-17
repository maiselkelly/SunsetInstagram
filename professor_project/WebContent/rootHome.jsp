<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Sunsets Homepage</title>
<style><%@include file="/jsp/stylesheet.css"%></style>
</head>
<body>
<img src="https://images.immediate.co.uk/production/volatile/
		sites/4/2013/04/GettyImages-640318118-c83a508.jpg?quality=90&resize=940%2C400" height="200">



		<h1>Welcome to Sunsets!</h1>
		<h3> Welcome root user! </h3>


		<%
			String user = (String) session.getAttribute("currentUser");
			out.println(user);
			if (user.equals("email@mail.com")) {
				out.println("<h2>Click here to initialize the database</h2>");
				out.println("<a href='InitializeDatabase'>Initialize Database</a>");
			} else {
				out.println("Sorry, you must be admin to use this page");
			}
		%>
		
		<h1>Cool Images</h1>
			<div align="center">
        <table id="users" border="0" cellpadding="5">
            <tr>
                <th>Image</th>
            </tr>
            <c:forEach var="url" items="${displayCoolImages}">  
                <tr>
                
                    <td><div class="imageFeed"><img src="${url}" /></div></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>
		
		
		
		<h1>New Images</h1>
			<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>Image</th>
            </tr>
            <c:forEach var="url" items="${displayNewImages}">  
                <tr>
                    <td><div class="imageFeed"><img src="${url}" /></div></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>
		
		
		
		<h1>Viral Images</h1>
			<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>Image</th>
            </tr>
            <c:forEach var="url" items="${displayViralImages}">  
                <tr>
                    <td><div class="imageFeed"><img src="${url}" /></div></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>
		
		
		<h1>Top Users</h1>
			<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>User</th>
            </tr>
            <c:forEach var="user" items="${displayTopUsers}">  
                <tr>
                    <td><c:out value="${user}" /></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>

		
		<h1>Popular Users</h1>
				<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>User</th>
            </tr>
            <c:forEach var="user" items="${displayPopularUsers}">  
                <tr>
                    <td><c:out value="${user}" /></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>
    	
    	
		<h1>Common Users</h1>
    	<form action="Data" method="post">
    		Select two users to see common follows:&nbsp;
    		<div align="center">
    			<select name="name1">
    				<c:forEach items="${allUsers}" var="user">
       			 	<option value="${user.email}">${user.email}</option>
   					</c:forEach>
				</select> 
				 
				<select name="name2">
    				<c:forEach items="${allUsers}" var="user">
       			 	<option value="${user.email}">${user.email}</option>
   					</c:forEach>
				</select>  		
				<br>
				<input type="submit" value="submit" />
			</div>
		</form>
			<br>
		<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>Common Users Followed</th>
            </tr>
            <c:forEach var="user" items="${commonUsers}">  
                <tr>
                    <td><c:out value="${user}" /></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>  
    

		<h1>Top Tags</h1>
				<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>User</th>
            </tr>
            <c:forEach var="tag" items="${displayTopTags}">  
                <tr>
                    <td><c:out value="${tag}" /></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>    	
    	
    
    	<h1>Positive Users</h1>
		<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>User</th>
            </tr>
            <c:forEach var="user" items="${positiveUsers}">  
                <tr>
                    <td><c:out value="${user}" /></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>    		
    	
    	
		<h1>Poor Images</h1>
			<div align="center">
        <table id="users" border="0" cellpadding="5">
            <tr>
                <th>Image</th>
            </tr>
            <c:forEach var="url" items="${poorImages}">  
                <tr>
                
                    <td><div class="imageFeed"><img src="${url}" /></div></td>
                </tr>
               
            </c:forEach> 
        </table>
    	</div>
    	
    	
    	<h1>Inactive Users</h1>
		<div align="center">
        <table id="users"  border="0" cellpadding="5">
            <tr>
                <th>User</th>
            </tr>
            <c:forEach var="user" items="${inactiveUsers}">  
                <tr>
                    <td><c:out value="${user}" /></td>
                </tr>
            </c:forEach> 
        </table>
    	</div>    
    	
    	
		<br> <br>

	</div> 
</body>
</html>