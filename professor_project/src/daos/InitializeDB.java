//package daos;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import SqlTables.CommentSQL;
//import SqlTables.FollowSQL;
//import SqlTables.ImageSQL;
//import SqlTables.LikeSQL;
//import SqlTables.TagsSQL;
//import SqlTables.UserSQL;
//import entityTypes.Like;
//
//public class InitializeDB {
//	
//	public InitializeDB(){
//		System.out.println("initialize constructor");
//		initialize();
//	}
//
//	private static Connection connect = null;
//	private static Statement statement = null;
//	private static PreparedStatement preparedStatement = null;
//	private static ResultSet resultSet = null;
//	private List<String> tableList = new ArrayList<String>(Arrays.asList("Follows", "Tags", "Likes", "Comments", "Image", "User"));
//
//
//
//	// main initialize method that sequences dropping and creating all tables
//	public void initialize() {
//		try {
//			connect = Connect.getConnection();
//			statement = connect.createStatement();
//
//			dropExisting();
//			createTables();
//			populate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//	}
//	
//	
//	// method to iterate through list of table names and drop them if they exist
//	private void dropExisting() throws SQLException {
//		for (int i = 0; i < tableList.size(); i++) {
//			System.out.println("tableList size and i: " + tableList.size()+", "+i);
//
//			String drop = "DROP TABLE IF EXISTS " + tableList.get(i);
//			System.out.println("cmd = "+drop);
//
//			statement.executeUpdate(drop);
//			System.out.println("Table " + tableList.get(i) + " dropped");
//		}
//	}
//	
//	
//	private void createTables() throws SQLException {
//		
//		
//		try {
//			statement.executeUpdate(UserSQL.getCreateTableString());
//			System.out.println(UserSQL.getCreateTableString());
//			statement.executeUpdate(ImageSQL.getCreateTableString());
//			System.out.println(ImageSQL.getCreateTableString());
//			statement.executeUpdate(LikeSQL.getCreateTableString());
//			System.out.println(LikeSQL.getCreateTableString());
//			statement.executeUpdate(TagsSQL.getCreateTableString());
//			System.out.println(TagsSQL.getCreateTableString());
//			statement.executeUpdate(CommentSQL.getCreateTableString());
//			System.out.println(CommentSQL.getCreateTableString());
//			statement.executeUpdate(FollowSQL.getCreateTableString());
//			System.out.println(FollowSQL.getCreateTableString());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("Tables Created");
//	}
//	
//	//Temporary implementation. what it should do is call the insert() methods in each tables own DAO that have not yet been written
//	//Simply add entries of all types to arraylist called entries as below, then the for loop executes each
//	private void populate() throws SQLException {
//		List<String> entries = new ArrayList();
//		entries.addAll(UserSQL.getTuples());	
//		entries.addAll(ImageSQL.getTuples());
//		entries.addAll(LikeSQL.getTuples());
//		entries.addAll(CommentSQL.getTuples());
//		entries.addAll(TagsSQL.getTuples());
//		entries.addAll(FollowSQL.getTuples());
//		
//		for(int i = 0; i<entries.size(); i++) {
//			System.out.println(entries.get(i));
//			statement.executeUpdate(entries.get(i));
//		}
//		
//	}
//
//	// close necessary items
//	private static void close() {
//		try {
//			if (resultSet != null) {
//				resultSet.close();
//			}
//
//			if (statement != null) {
//				statement.close();
//			}
//
//			if (connect != null) {
//				connect.close();
//			}
//		} catch (Exception e) {
//
//		}
//	}
//
//}
