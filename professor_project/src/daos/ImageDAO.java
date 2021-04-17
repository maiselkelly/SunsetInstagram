package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import entityTypes.Image;
import entityTypes.User;

@WebServlet("/ImageDAO")
public class ImageDAO extends AbstractDAO{
	

	public ImageDAO() {

	}
	
	
	@SuppressWarnings("finally")
	public boolean dropTable() {
		boolean result = false;
		try {
			connect_func();
			result = (statement.executeUpdate("DROP TABLE IF EXISTS Image") > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			return result;
		}
	}
	
	
	
	public boolean initImageTable() throws SQLException{
		connect_func();
		String createImage = "CREATE TABLE IF NOT EXISTS Image (" 
				+ " imageid INTEGER NOT NULL AUTO_INCREMENT, " 
				+ " postdate DATE, "
				+ " posttime DATETIME DEFAULT CURRENT_TIMESTAMP,"
				+ " url VARCHAR(150) NOT NULL, " 
				+ " description VARCHAR(100), " 
				+ " postuser VARCHAR(100) NOT NULL, "
				+ " PRIMARY KEY (imageid ),"
				+ " FOREIGN KEY (postuser) REFERENCES User(email) ON DELETE CASCADE );";		
		
		boolean tableCreated = statement.executeUpdate(createImage) >0;
		
		String insertImage = "INSERT INTO Image(postdate, posttime, url, description, postuser)" 
				+ "VALUES('2020-01-05',  '2020-01-05 10:50:00', 'https://images.all-free-download.com/images/graphiclarge/dramatic_sunset_sky_192371.jpg', 'picture', 'lloyd@wayne.edu'),"
				+"('2020-01-05', '2020-01-05 10:50:00', 'https://images.all-free-download.com/images/graphiclarge/palm_tree_at_sunset_187105.jpg', 'Maui sunset tonight', 'joe@wayne.edu'),"
				+"('2020-01-01', '2020-01-01 01:20:00', 'https://images.all-free-download.com/images/graphiclarge/sunset_in_prague_201673.jpg', 'Sunset in Thailand!', 'smith@mail.com'), " 
				+"('2020-01-03', '2020-01-03 11:40:00', 'https://images.all-free-download.com/images/graphiclarge/sunset_barbados_514542.jpg', 'Smokey sunset in the city', 'ride@gmail.com'), "
				+"('2020-01-07', '2020-01-07 12:26:00', 'https://images.all-free-download.com/images/graphiclarge/sunset_de_rincn_515593.jpg', 'The sunset had some crazy colors tonight!', 'marley@yahoo.com'),"
				+"('2020-02-06', '2020-02-06 01:20:00', 'https://images.all-free-download.com/images/graphiclarge/sunset_and_evening_11_of_161_515488.jpg', 'Beautiful sunset over Malibu', 'ringwald1@gmail.com'),"
				+"('2020-01-01', '2020-01-02 01:26:00', 'https://images.all-free-download.com/images/graphiclarge/the_mt_carry_on_the_baltic_sea_at_sunset_516958.jpg', 'Great sunset here in Miami', 'ride@gmail.com')," 
				+"('2020-01-09', '2020-01-09 01:30:00', 'https://images.all-free-download.com/images/graphiclarge/natural_beauty_of_hd_picture_3_166090.jpg', 'These sunsets in Hawaii never get old', 'jackwhite@wayne.edu'), "
				+"('2020-01-15', '2020-01-15 07:55:00', 'https://images.all-free-download.com/images/graphiclarge/burn_baby_burn_515725.jpg', 'What a perfect end to the day', 'marley@yahoo.com'), "
				+"('2020-01-20', '2020-01-20 13:27:00', 'https://images.fineartamerica.com/images/artworkimages/mediumlarge/1/mackinac-bridge-winter-sunset-michelle-thompson.jpg', 'A great sunset to end my vacation!', 'ringwald1@gmail.com')";
		
		
		boolean imagesInserted = statement.executeUpdate(insertImage) > 0;
		
		close();
		
		System.out.println("Images initialized in db");
		return (tableCreated && imagesInserted);
	}
	
	
	public List<Image> imageFeed() throws SQLException{
		List<Image> imageFeed = new ArrayList<Image>();
		String sql = "SELECT * FROM Image";
		connect_func();
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println("##############START NEW ROW READ#################");
		while(resultSet.next()) {
			
			Date postDate = resultSet.getDate("postdate");
			String postdate = String.valueOf(postDate);
			String url = resultSet.getString("url");
			String description = resultSet.getString("description");
			String postuser = resultSet.getString("postuser");
			int imageid = resultSet.getInt("imageid");
			imageFeed.add(new Image(imageid, postdate, url, description, postuser));
		}
		close();
		////
		////Does it make more sense to add a numLikes column to the image table and keep that updated?
		////Something else more efficient..I feel like this is going to to too much too often
		LikesDAO likesdao = new LikesDAO();
		for(int i=0; i<imageFeed.size();i++) {
			imageFeed.get(i).setNumLikes(likesdao.getNumLikes(imageFeed.get(i).getImageId()));
		}
		return imageFeed;
	}
	
	public List<Image> imageFeed(String userName) throws SQLException{
		List<Image> imageFeed = new ArrayList<Image>();

		String sql = "SELECT * FROM Image WHERE  postuser in \n" + 
				"	(select Follows.followingemail from Follows where followeremail = '" + userName + "') order by posttime desc";
		
		connect_func();
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println("##############START NEW ROW READ#################");
		while(resultSet.next()) {
			
			Date postDate = resultSet.getDate("postdate");
			String postdate = String.valueOf(postDate);
			String url = resultSet.getString("url");
			String description = resultSet.getString("description");
			String postuser = resultSet.getString("postuser");
			int imageid = resultSet.getInt("imageid");
			imageFeed.add(new Image(imageid, postdate, url, description, postuser));
		}
		close();
		
		////
		////Does it make more sense to add a numLikes column to the image table and keep that updated?
		////Something else more efficient..I feel like this is going to to too much too often
		LikesDAO likesdao = new LikesDAO();
		for(int i=0; i<imageFeed.size();i++) {
			imageFeed.get(i).setNumLikes(likesdao.getNumLikes(imageFeed.get(i).getImageId()));
		}
		return imageFeed;
	}
	
	//Search User's Personal Images to post for delete/edit
	public List<Image> searchUser(String email) throws SQLException {
		List<Image> searchUser = new ArrayList<Image>();
		connect_func();
		
		String sql = "SELECT * FROM Image WHERE postuser = '" + email + "'";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);		
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			Date postDate = resultSet.getDate("postdate");
			String postdate = String.valueOf(postDate);
			String url = resultSet.getString("url");
			String description = resultSet.getString("description");
			String postuser = resultSet.getString("postuser");
			int imageid = resultSet.getInt("imageid");

			searchUser.add(new Image(imageid, postdate, url, description, postuser));
		}
		
		close();
		return searchUser;		
	}	
	
	public int insertImage(Image image) throws SQLException {

		connect_func();
		String sql = "insert into  Image(postdate, url, description, postuser) values (?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
		preparedStatement.setString(1, image.getPostDate());
		preparedStatement.setString(2, image.getUrl());
		preparedStatement.setString(3, image.getDescription());
		preparedStatement.setString(4, image.getPostuser());

		//Statement.RETURN_GENERATED_KEYS
		boolean rowInserted = preparedStatement.executeUpdate() > 0;
		ResultSet keys = preparedStatement.getGeneratedKeys();
		keys.next();
		int newImageId = keys.getInt(1);
		close();
		return newImageId;
	}

	
	
	public boolean deleteImage(int image) throws SQLException {
		String sql = "DELETE FROM Image WHERE imageid = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, image);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		close();
		return rowDeleted;
	}

	
	public boolean updateImage(Image image) throws SQLException {
		String sql = "update Image set postdate=?, posttime=?, url=?, description=?, postuser=?  where imageid = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, image.getPostDate());
		preparedStatement.setString(2, image.getPostTime());
		preparedStatement.setString(3, image.getUrl());
		preparedStatement.setString(4, image.getDescription());
		preparedStatement.setString(5, image.getPostuser());
		preparedStatement.setInt(6,image.getImageId());

		//HERE - need post user attribute
		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		close();
		return rowUpdated;
	}

	
	public Image getImage(int imageid) throws SQLException {
		Image image = null;
		String sql = "SELECT * FROM Image WHERE imageid = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, imageid);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String postdate = resultSet.getString("postdate");
			String url = resultSet.getString("url");
			String description = resultSet.getString("description");
			String postuser = resultSet.getString("postuser");	
			
			image = new Image(imageid, postdate, url, description, postuser);
		}

		close();
		return image;

	}
	
	
	
}
