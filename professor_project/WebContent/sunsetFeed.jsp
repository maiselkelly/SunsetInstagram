<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Perfect Sunset :: Feed</title>
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
		<li><a href="sessionImage">Post Sunset</a></li>			
	</ul>
</nav>
	
	<h1>Sunset Community Feed</h1>
	
	
	
	<c:forEach var="image" items="${imageFeed}" >
	<div class="imageFeed"><img src="${image.getUrl()}" /></div>
	<div class="description"><c:out value="${image.getDescription()}" /></div>
	
	<c:forEach var="tag" items="${image.getTags()}" > 
		<div class="tags"><c:out value="${tag.getTag()}" /></div>	
	  </c:forEach>
	
	<div class="dateanduser"><c:out value="${image.getPostDate()}" /> &nbsp; &nbsp; <c:out value="${image.getPostuser()}" /></div>
	<div class="likes"><b>Likes: </b><c:out value="${image.getNumLikes()}" /> 
	
	<a  href="like?imageid=<c:out value='${image.getImageId()}' />">Like</a>
	<a  href="unlike?imageid=<c:out value='${image.getImageId()}' />">Unlike</a>
	</div>
	
	<h3>Comments</h3>
	<c:forEach var="comment" items="${image.getComments()}" > 
		<div class="comments"><b><c:out value="${comment.getEmail()}" /></b>&nbsp; -  &nbsp;<c:out value="${comment.getComment()}" /></div>	
	  </c:forEach>
	  <br>
	  <form action="insertComment">
			<label>Post a Comment</label><br>
			<input type="hidden" name="imageid" value="${image.getImageId()}" />
			<textarea id="comment" name="comment" placeholder="Write a  comment..." rows="4" cols="50">
		 </textarea>
		 <br>
  <input class="button1" type="submit" value="Submit">
</form>
	  <hr>
   </c:forEach>

	

</body>
</html>