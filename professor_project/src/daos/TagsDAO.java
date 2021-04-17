package daos;

import java.sql.Connection;
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
import entityTypes.Tag;

@WebServlet("/TagsDAO")
public class TagsDAO extends AbstractDAO{
	

	public TagsDAO() {

	}
	
	
	@SuppressWarnings("finally")
	public boolean dropTable() {
		boolean result = false;
		try {
			connect_func();
			result = (statement.executeUpdate("DROP TABLE IF EXISTS Tags") > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			return result;
		}
	}
	
	public boolean initTagsTable() throws SQLException{
		connect_func();
		String createTag = "CREATE TABLE IF NOT EXISTS Tags (" 
				+ "imageid INTEGER NOT NULL AUTO_INCREMENT,"
				+ "tag VARCHAR(20) NOT NULL,"
				+ "PRIMARY KEY (imageid, tag), "
				+ "FOREIGN KEY (imageid) REFERENCES Image (imageid) ON DELETE CASCADE ) ";

		boolean tableCreated = statement.executeUpdate(createTag) >0;
		
		String insertTags = "INSERT INTO Tags(imageid, tag)" 
				+ "VALUES('2' , '#wow'),"
				+"('4' , '#vibrant'),"
				+"('2' , '#stunning'), " 
				+"('5' , '#everyday'), "
				+"('7' , '#living'),"
				+"('1' , '#vibrant'),"
				+"('8' , '#wow')," 
				+"('9' , '#ocean'), "
				+"('1' , '#redsky'), "
				+"('6' , '#mountains')";
		
		
		boolean tagsInserted = statement.executeUpdate(insertTags) > 0;
		close();
		
		System.out.println("Tags initialized in db");
		return (tableCreated && tagsInserted);
	}
	
	public void insertTags(int imageid, String tagString) throws SQLException {
		String[] tags = tagString.split("#");
		connect_func();
		for(int i = 1; i<tags.length; i++) {
			String insertTag = "INSERT INTO Tags(imageid, tag) VALUES (" 
					+ "'" +String.valueOf(imageid)+"' , '#"+ tags[i].trim() + "')";
			statement.executeUpdate(insertTag);
		}		
		close();
	}
	
	
	public List<Tag> tagFeed(int imageid) throws SQLException{
		
		List<Tag> tagFeed = new ArrayList<Tag>();
		
		String sql = "Select * from Tags "
				+ "WHERE imageid = '"+ imageid + "'";		
	
		connect_func();
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println("##############START NEW ROW READ#################\n" + sql);
		while(resultSet.next()) {
			String tag = resultSet.getString("tag");
			tagFeed.add(new Tag(imageid, tag));			
		}
		close();
		return tagFeed;
	}

	
}