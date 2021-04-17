<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>The Perfect Sunset :: User Page</title>
<style><%@include file="/jsp/stylesheet.css"%></style>
</head>
<body>

<img src="https://images.immediate.co.uk/production/volatile/
		sites/4/2013/04/GettyImages-640318118-c83a508.jpg?quality=90&resize=940%2C400" height="200px">

<nav class="nav">
	<ul>
		<div class=search">
		<form action="search">
		<li><input type="text" placeholder="Search Username"> </li>
		<li><button type="submit">Submit</button></li>
		</form>
		</div>
		<li><a href="Home">Home</a></li>
		<li><a href="listUsers">Sunset Community</a></li>
		<li><a href="sunsetFeed">Sunset Feed</a></li>
		
		
	</ul>
</nav>

<br><br>
	<h1>User Profile Information:</h1>
    	
    	<p>First Name: </p> 
    	<p>Last Name: </p>
    	<p>Username: </p>
    	<p>Birthday:</p>
    	<p>Gender: </p>
  		
  		
  		<h2>Would you like to follow or unfollow?</h2>
  		
  		<button class="button" type="submit" id="follow" value="follow">Follow!</button> 
  		<button class="button" type="submit" id="unfollow" value="unfollow">Unfollow!</button>


</body>
</html>