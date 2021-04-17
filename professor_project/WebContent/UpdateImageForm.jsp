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
			<li><a href="Community.jsp">Sunset Community</a></li>
		<li><a href="sunsetFeed.jsp">Sunset Feed</a></li>
		<li><a href="Postsunset.jsp">Post a Sunset</a></li>
		
	</ul>
</nav>

   
   <div align="center">
            <form action="updateImage" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Edit an Existing Image</h2>
            </caption>
                    <input type="hidden" name="id"  value='${people.id}' />
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value='${people.name}' />
                        
                </td>
            </tr>
            <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="description" size="5"
                            value='${image.description}' />
                    
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
   
   
</body>
</html>