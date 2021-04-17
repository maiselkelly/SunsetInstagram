package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import entityTypes.Comment;
import entityTypes.Image;
import entityTypes.User;

@WebServlet("/CommentDAO")
public class CommentDAO extends AbstractDAO{
	

	public CommentDAO() {

	}

	@SuppressWarnings("finally")
	public boolean dropTable() {
		boolean result = false;
		try {
			connect_func();
			result = (statement.executeUpdate("DROP TABLE IF EXISTS Comments") > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			return result;
		}
	}
	
	public boolean initCommentTable() throws SQLException{
		connect_func();
		String createComment = "CREATE TABLE IF NOT EXISTS Comments (" 
				+ "email VARCHAR(100) NOT NULL, " 
				+ "imageid INTEGER NOT NULL,"
				+ "comment VARCHAR(500), " 
				+ "PRIMARY KEY (email, imageid), "
				+ "FOREIGN KEY (email) REFERENCES User(email), "
				+ "FOREIGN KEY (imageid) REFERENCES Image(imageid)ON DELETE CASCADE )";
		
		boolean tableCreated = statement.executeUpdate(createComment) >0;
		
		String insertComments = "INSERT INTO Comments(email, imageid, comment)" 
				+ "VALUES('lloyd@wayne.edu', '1', 'Love that picture!'),"
				+"('joe@wayne.edu', '5', 'Sunset looks great.'),"
				+"('smith@mail.com', '9', 'Oh, what city was this taken?'), " 
				+"('mpoppins@yahoo.com', '1', 'Such a beautful sunset you captured!'), "
				+"('marley@yahoo.com', '6', 'Cant wait to visit to see this.'),"
				+"('ride@gmail.com', '5',  'Looks like you are having a great time.'),"
				+"('mpoppins@yahoo.com', '2',  'Absolutely stunning photo.')," 
				+"('hendrix1@gmail.com', '3', 'Miss seeing this every night.'), "
				+"('smith@mail.com', '4', 'You must be having a good time!'), "
				+"('ride@gmail.com', '6',  'So professional!!')";
		
		
		boolean commentsInserted = statement.executeUpdate(insertComments) > 0;
		close();
		
		System.out.println("Comments initialized in db");
		return (tableCreated && commentsInserted);
	}
	
	
	public List<Comment> commentFeed(int imageid) throws SQLException{
		
		List<Comment> commentFeed = new ArrayList<Comment>();

		String sql = "Select * from Comments "
				+ "WHERE imageid = '" + imageid + "'";
		
		connect_func();
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println("##############START NEW ROW READ#################");
		while(resultSet.next()) {
			
			String email = resultSet.getString("email");
			String comment = resultSet.getString("comment");
			commentFeed.add(new Comment(imageid, email, comment));
		}
		close();
		return commentFeed;
	}
	
 
	
	public boolean insertComment(Comment comment) throws SQLException {

		connect_func();
		String sql = "insert IGNORE into  Comments(email, imageid, comment) values (?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, comment.getEmail());
		preparedStatement.setInt(2, comment.getImage_id());
		preparedStatement.setString(3, comment.getComment());
		
		System.out.println(preparedStatement);

		boolean rowInserted = preparedStatement.executeUpdate() > 0;
		close();
		return rowInserted;
		}
	}


