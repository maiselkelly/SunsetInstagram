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
		<li><input type="text" name="searchFor" placeholder="Search Username"> </li>
		<li><button type="submit">Submit</button></li>
		</form>
		</div>
		<li><a href="home.jsp">Home</a></li>
			<li><a href="listUsers">Sunset Community</a></li>
		<li><a href="imageFeed">Sunset Feed</a></li>
			
	</ul>
</nav>

<h1>Post a Sunset Here!</h1>

 <form  action="insertSunset" method="post">
     <br>
     <label><b>Image URL:</b></label>
     <input type="text"   name="imageURL" placeholder="Enter Sunset URL" size="45" value="${image.url}" required>
  	 <br><br>
       <label><b>Description:</b></label>
     <input type="text"  name="description" placeholder="Give a simple description" size="45" value="${image.description}" required>
  		 <br><br>
       <label><b>Tags:</b></label>
     <input type="text"  name="tags" placeholder="Tag this image" size="45" value="${tag.tag}" required>
  	<br><br>
	
	
     <button class="button" type="submit" id="postSunset" value="postSunset">Post Your Sunset!</button>
        </form>
        
        <br><br>
        
         
    <div align="center">
        <table id="users" border="1" cellpadding="5">
            <caption><h1>Modify Your Sunset Images Here!</h1></caption>
            <tr>       
                <th>Image(Url)</th>
                <th>Description</th>
                <th>Date Posted</th>
                <th>Edit or Delete Image</th>
            </tr>
            <c:forEach var="image" items="${sessionImage}">
                <tr>
                    <td><img src="${image.getUrl()}"/></td>
                    <td><c:out value="${image.getDescription()}" /></td>
                    <td><c:out value="${image.getPostDate()}" /></td>
                  
                    <td>
                        <a href="edit?imageid=<c:out value='${image.getImageId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteImage?imageid=<c:out value='${image.getImageId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>         
<br><br><br><br>

</body>
</html>