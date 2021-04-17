package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RootDAO extends AbstractDAO {

	
	//Create a View
	public boolean topPostingTable() throws SQLException{
		connect_func();
	
		String insertView = "CREATE OR REPLACE VIEW topPosting(postuser, num) AS"
				+ " SELECT postuser, COUNT(*)"
				+ " FROM Image"
				+ " GROUP BY postuser";
		
		boolean viewCreated = statement.executeUpdate(insertView) > 0;
		
		close();
		return viewCreated;
	}
	
	
	//Cool Images 
	public List<String> coolImages() throws SQLException{
		
		List<String> images = new ArrayList<String>();
		connect_func();
		
		String sql = "SELECT i.url"
				+ " FROM Image i"
				+ " WHERE i.imageid IN"
				+ " (Select L.imageid"
				+ " FROM Likes L, Image i"
				+ " WHERE i.imageid = L.imageid"
				+ " GROUP BY L.imageid"
				+ " HAVING Count(*) >=5)";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);		
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String url = resultSet.getString("i.url");
			System.out.println("cool: " + url);
			images.add(url);			
		}
		
		close();
		return images;
	}
	
	
	
	//New Images
	public List<String> newImages(String postdate) throws SQLException{
		
		List<String> images = new ArrayList<String>();
		connect_func();
		
		String sql = "SELECT i.url"
				+ " FROM Image i"
				+ " WHERE postdate = '" + postdate + "'";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String url = resultSet.getString("i.url");			
			images.add(url);
			System.out.println("got new: " + url);
		}
		
		close();
		return images;
	}
	
	
	//Viral Images
	public List<String> viralImages() throws SQLException{
	
		
		System.out.println("Viral IMAGES");
		
		List<String> images = new ArrayList<String>();
		connect_func();
		
		String sql = "SELECT i.url AS count FROM Image i, Likes l"
				+ " WHERE i.imageid = l.imageid"
				+ " GROUP BY i.url"
				+ " ORDER BY count DESC LIMIT 3";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);	
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String url = resultSet.getString("count");			
			images.add(url);			
		}
		
		close();
		return images;
	}
	
	
	//Top Users
	public List<String> topUsers() throws SQLException{

		//Call to Top Posting Function
		topPostingTable();
		
		List<String> users = new ArrayList<String>();
		connect_func();
		
		String sql ="SELECT postuser"
				+ " FROM topPosting"
				+ " WHERE num = (SELECT MAX(num) FROM topPosting)";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);	
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String user = resultSet.getString("postuser");			
			users.add(user);			
		}
		
		close();
		return users;
	}
	
	
	//Popular Users
	public List<String> popularUsers() throws SQLException{
		
		List<String> users = new ArrayList<String>();
		connect_func();
		
		String sql ="SELECT U.email"
				+ " FROM User U, Follows F"
				+ " WHERE U.email = F.Followingemail"
				+ " GROUP BY U.email"
				+ " HAVING COUNT(*) >= 5";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String user = resultSet.getString("U.email");			
			users.add(user);		
		}
		
		close();
		return users;
	}
	
	
	//Positive Users
	public List<String> commonUsers(String userX, String userY) throws SQLException{
		
		List<String> user = new ArrayList<String>();
		connect_func();
		
		String sql ="SELECT email from User" + 
				"	WHERE User.email IN (SELECT followingemail FROM Follows WHERE followeremail = '" + userX + "')" + 
				"    AND  User.email IN (SELECT followingemail FROM Follows WHERE followeremail = '" + userY + "')";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String email = resultSet.getString("email");			
			user.add(email);			
		}
		
		close();
		return user;
	}
	
	//Top Tags
	public List<String> topTags() throws SQLException{
		
		List<String> tags = new ArrayList<String>();
		connect_func();
		
		String sql ="SELECT tag from "
				+ "(SELECT tag, postuser FROM Image INNER JOIN Tags "
				+ "ON Image.imageid = Tags.imageid ORDER BY tag) as temp "
				+ "GROUP BY tag HAVING COUNT(*) >= 3";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);		
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String tag = resultSet.getString("tag");			
			tags.add(tag);			
		}
		
		close();
		return tags;
	}
	
	//Positive Users
	public List<String> positiveUsers() throws SQLException{
		
		List<String> user = new ArrayList<String>();
		connect_func();

		//this produces list of users that have liked at least as many images as are in their feed.
		//because users can only like each image once, if numLikes == numImages in their feed we have a positive user
		//(went with >= in case some likes were dummy data ALSO produced some likes on images not in the user's feed)	
		String sql = "SELECT email FROM User where "
				+ "(SELECT COUNT(*) from Likes WHERE Likes.email = User.email) >= "
				+ "(SELECT COUNT(*) FROM Image WHERE postuser in "
				+ "(select Follows.followingemail from Follows where followeremail = User.email))";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);		
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String tag = resultSet.getString("email");	
			if(!tag.equals("email@mail.com")) {
				user.add(tag);
			}	
		}
		
		close();
		return user;
	}
	
	//Poor Images
	public List<String> poorImages() throws SQLException{
		
		List<String> images = new ArrayList<String>();
		connect_func();
		
		String sql ="SELECT url From Image WHERE "
				+ "Image.imageid NOT IN (SELECT DISTINCT imageid from Likes) "
				+ "AND Image.imageid NOT IN (SELECT DISTINCT imageid from Comments)";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String url = resultSet.getString("url");			
			images.add(url);			
		}
		
		close();
		return images;
	}
	
	
	//Inactive Users
	public List<String> inactiveUsers() throws SQLException{
		
		List<String> user = new ArrayList<String>();
		connect_func();
		
		String sql ="SELECT email FROM User \n" + 
				"	WHERE User.email NOT IN (SELECT DISTINCT postuser FROM Image) \n" + 
				"    AND User.email NOT IN (SELECT DISTINCT followeremail FROM Follows)\n" + 
				"    AND User.email NOT IN (SELECT DISTINCT email FROM Likes)\n" + 
				"    AND User.email NOT IN (SELECT DISTINCT email FROM Comments)";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);		
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {			
			String tag = resultSet.getString("email");			
			user.add(tag);			
		}
		
		close();
		return user;
	}
	
	
}
