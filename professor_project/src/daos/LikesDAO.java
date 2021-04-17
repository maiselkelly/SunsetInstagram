package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import entityTypes.Like;

@WebServlet("/LikesDAO")
public class LikesDAO extends AbstractDAO{
	

	public LikesDAO() {

	}
	
  
	@SuppressWarnings("finally")
	public boolean dropTable() {
		boolean result = false;
		try {
			connect_func();
			result = (statement.executeUpdate("DROP TABLE IF EXISTS Likes") > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			return result;
		}
	}
	
	public boolean initLikesTable() throws SQLException{
		connect_func();
		String createLike = "CREATE TABLE IF NOT EXISTS Likes " 
				+ "(email VARCHAR(100) NOT NULL,"
				+ "imageid INTEGER NOT NULL, "
				+ "likedate DATE, "
				+ " PRIMARY KEY (email, imageid)," 
				+ " FOREIGN KEY(email) REFERENCES User(email), "
				+ "FOREIGN KEY (imageid) REFERENCES Image(imageid) ON DELETE CASCADE )";
		
		boolean tableCreated = statement.executeUpdate(createLike) >0;
		
		String insertLikes = "INSERT INTO Likes(email, imageid, likedate)" 
				+ "VALUES('lloyd@wayne.edu', '1', '2020-02-06'),"
				+"('joe@wayne.edu', '4', '2020-02-07'),"
				+"('ride@gmail.com', '6', '2020-02-08'), " 
				+"('lloyd@wayne.edu', '7', '2020-02-10'), "
				+"('jackwhite@wayne.edu', '8', '2020-03-10'),"
				+"('hendrix1@gmail.com', '9', '2020-03-13'),"
				+"('ride@gmail.com', '10', '2020-03-20')," 
				+"('joe@wayne.edu', '6', '2020-04-21'), "
				+"('jackwhite@wayne.edu', '6', '2020-04-01'), "
				+"('mpoppins@yahoo.com', '3', '2020-03-25')";
		
		
		boolean likesInserted = statement.executeUpdate(insertLikes) > 0;
		close();
		
		System.out.println("Likes initialized in db");
		return (tableCreated && likesInserted);
	}
	
	public int getNumLikes(int imageid) throws SQLException {
		connect_func();
		String sql = "SELECT COUNT(*) FROM Likes WHERE imageid = '"+String.valueOf(imageid)+"'";
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		return resultSet.getInt(1);
	}
	

	public void insert(Like newLike) throws SQLException {
		connect_func();
		String sql = "insert IGNORE into Likes(imageid, email, likedate) values (?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, String.valueOf(newLike.getImageId()));
		preparedStatement.setString(2, newLike.getLikerId());
		preparedStatement.setString(3, newLike.getLikeDate());
		preparedStatement.executeUpdate();
		close();
	}
	
	public void remove(Like newLike) throws SQLException {
		connect_func();
		String sql = "DELETE FROM Likes WHERE imageid = ? AND email = ?";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, String.valueOf(newLike.getImageId()));
		preparedStatement.setString(2, newLike.getLikerId());
		preparedStatement.executeUpdate();
		close();
	}
	

}
