package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import entityTypes.Follow;
import entityTypes.User;

@WebServlet("/FollowsDAO")
public class FollowsDAO extends AbstractDAO {


	public FollowsDAO() {

	}


	@SuppressWarnings("finally")
	public boolean dropTable() {
		boolean result = false;
		try {
			connect_func();
			result = (statement.executeUpdate("DROP TABLE IF EXISTS Follows") > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			return result;
		}
	}

	public boolean initFollowsTable() throws SQLException {
		connect_func();
		String createFollows = "CREATE TABLE IF NOT EXISTS Follows ( "
				+ " followingemail VARCHAR(100) NOT NULL, "
				+ "followeremail VARCHAR(100) NOT NULL, "
				+ " PRIMARY KEY (followingemail, followeremail), " 
				+ " FOREIGN KEY(followingemail) REFERENCES User(email) ON DELETE CASCADE , "
				+ "FOREIGN KEY (followeremail) REFERENCES User(email) ON DELETE CASCADE )";
		
		boolean tableCreated = statement.executeUpdate(createFollows) > 0;

		String insertFollows = "INSERT INTO Follows(followingemail, followeremail)"
				+ "VALUES('lloyd@wayne.edu', 'jackwhite@wayne.edu'), "
				+ " ('marley@yahoo.com', 'ringwald1@gmail.com'),"
				+ "('hendrix1@gmail.com', 'lloyd@wayne.edu'), " 
				+ "('smith@mail.com', 'joe@wayne.edu'), "
				+ " ('marley@yahoo.com', 'ride@gmail.com')," 
				+ "('marley@yahoo.com', 'hendrix1@gmail.com'),"
				+ "('jackwhite@wayne.edu', 'ringwald1@gmail.com')," 
				+ "('lloyd@wayne.edu', 'marley@yahoo.com'), "
				+ "('mpoppins@yahoo.com', 'marley@yahoo.com'), " 
				+ "('jackwhite@wayne.edu', 'mpoppins@yahoo.com')";

		boolean followsInserted = statement.executeUpdate(insertFollows) > 0;
		close();

		System.out.println("Follows initialized in db");
		return (tableCreated && followsInserted);
	}
	
	
	
	//Insert a follower connection
	public boolean insertFollower(Follow follow) throws SQLException {
		connect_func();

		System.out.println("adding follow");
		
		//adding IGNORE to insert prevents exception if the entry already exists. Adds entry if doesn't exist, otherwise aborts execution
		String sql = "insert IGNORE into Follows(followeremail, followingemail) values (?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, follow.getFollower());
		preparedStatement.setString(2, follow.getFollowed());
		boolean rowInserted = preparedStatement.executeUpdate() > 0;
		close();
		return rowInserted;
	}

	//Delete a follower connection
	public boolean deleteFollower(String followeremail, String followingemail) throws SQLException {
		String sql = "DELETE FROM Follows WHERE followeremail = ? AND followingemail = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, followeremail);
		preparedStatement.setString(2, followingemail);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		close();
		return rowDeleted;
	}

}
