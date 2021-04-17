<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>The Perfect Sunset :: Home</title>
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
			<li><a href="listUsers">Sunset Community</a></li>
		<li><a href="imageFeed">Sunset Feed</a></li>
		<li><a href="sessionImage">Post a Sunset</a></li>
		
		
	</ul>
</nav>


    <div style="text-align: center">

        <h1>Welcome ${currentUser} to The Perfect Sunset!</h1>
     
        
         <h2>The sunset possibilities are endless!</h2>
   <br><br>       
    </div>
    
  
</body>
</html>