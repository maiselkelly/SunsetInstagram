package daos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityTypes.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserDAO")
public class UserDAO extends AbstractDAO {

	public UserDAO() {

	}

	@SuppressWarnings("finally")
	public boolean dropTable() {
		boolean result = false;
		try {
			connect_func();
			result = (statement.executeUpdate("DROP TABLE IF EXISTS User") > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			return result;
		}
	}

	public boolean initUserTable() throws SQLException {
		connect_func();
		String createUser = "CREATE TABLE IF NOT EXISTS User (" 
				+ " firstname VARCHAR(20) , " 
				+ " lastname VARCHAR(20), " 
				+ " email VARCHAR(100) NOT NULL, "
				+ " password VARCHAR(20), " 
				+ " gender CHAR(1) , " 
				+ " birthday DATE , " 
				+ " numoffollowers int , " 
				+ " numoffollowings int , " 
				+ " PRIMARY KEY (email) "
				+ ")";

		boolean tableCreated = statement.executeUpdate(createUser) > 0;

		String insertUsers = "INSERT INTO User(firstname, lastname, email, password, gender, birthday, numoffollowers, numoffollowings)"
				+ "VALUES('root', 'root', 'email@mail.com', 'pass1234', 'o', '2020-01-01', '0', '0'),"
				+ "('Lloyd','Brombach', 'lloyd@wayne.edu', 'pass1234', 'm','1975-11-04' , '2', '4'),"
				+ "('Joe','Student', 'joe@wayne.edu', 'pass1234','m','1990-10-10' , '10', '3'), "
				+ "('John', 'Smith', 'smith@mail.com', 'password1', 'm', '1980-01-21' , '1', '3'), "
				+ "('Sally', 'Ride', 'ride@gmail.com', 'password3', 'f', '1991-12-21' , '6', '5'),"
				+ "('Bob', 'Marley', 'marley@yahoo.com', 'password4', 'm', '1979-01-02' , '7', '10'),"
				+ "('Molly', 'Ringwald', 'ringwald1@gmail.com', 'password5', 'f', '1989-11-10' , '4', '2'),"
				+ "('Jack', 'White', 'jackwhite@wayne.edu', 'password6', 'm', '1989-11-11' , '9', '7'), "
				+ "('Mary', 'Poppins', 'mpoppins@yahoo.com', 'password7', 'f', '1948-10-01' , '4', '1'), "
				+ "('Jimi', 'Hendrix', 'hendrix1@gmail.com', 'passwprd8', 'm', '1965-04-08' , '10', '9')";

		boolean usersInserted = statement.executeUpdate(insertUsers) > 0;
		close();

		System.out.println("Users initialized in db");
		return (tableCreated && usersInserted);
	}



	// Check to see if email is taken during registration
	public boolean duplicatedUser(String email) throws SQLException {
		String getAllUsers = "SELECT * FROM User";
		connect_func();
		ResultSet resultSet = statement.executeQuery(getAllUsers);

		while (resultSet.next()) {
			if (resultSet.getString("email").equals(email)) {
				// User name taken
				System.out.println("Email already used.");
				return true;
			}
			// User name not taken
		}
		close();
		return false;
	}

	// Validate the user at login
	public boolean isUserValid(String email, String password) throws SQLException {
		String getAllUsers = "SELECT * FROM User";
		connect_func();
		ResultSet resultSet = statement.executeQuery(getAllUsers);

		resultSet.last();
		int setSize = resultSet.getRow();
		resultSet.beforeFirst();

		for (int i = 0; i < setSize; i++) {
			resultSet.next();

			// User and password created
			if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
				return true;
			}
			// no user exists
		}
		close();
		return false;

	}
	
	
	
	

	public List<User> listAllUsers() throws SQLException {
		List<User> listUsers = new ArrayList<User>();
		String sql = "SELECT * FROM User ORDER BY email ASC";
		connect_func();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {

			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String gender = resultSet.getString("gender");
			String birthday = resultSet.getString("birthday");

			User user = new User(firstname, lastname, email, password, gender, birthday);
			listUsers.add(user);
		}
		close();
		return listUsers;
	}

	
	
	public List<User> searchName(String name) throws SQLException {
		
		List<User> searchUsers = new ArrayList<User>();
		String[] users = name.split(" ");
		String sql;
	
		//First and Last
		if(users.length > 1) {	
			sql = "SELECT * FROM User WHERE firstname = '" + users[0] + "' AND lastname = '" + users[1] + "' ORDER by lastname ASC, firstname ASC";	
		}
		//First or last name
		else{		
			sql = "SELECT * FROM User WHERE firstname = '" + users[0] + "' OR lastname = '" + users[0] + "' ORDER by lastname ASC, firstname ASC";
		}
	
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			String fname = resultSet.getString("firstname");
			String lname = resultSet.getString("lastname");
			String useremail = resultSet.getString("email");
			String password = resultSet.getString("password");
			String gender = resultSet.getString("gender");
			String birthday = resultSet.getString("birthday");

			User user = new User(fname, lname, useremail, password, gender, birthday);
			searchUsers.add(user);
			System.out.println("founds user : " + user.toString());
		}

		close();
		return searchUsers;
	}
	
	
	public boolean insertUser(User user) throws SQLException {

		connect_func();
		String sql = "insert into  User(firstname, lastname, email, password, gender, birthday) values (?, ?, ?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setString(5, user.getGender());
		preparedStatement.setString(6, user.getBirthday());

		boolean rowInserted = preparedStatement.executeUpdate() > 0;

		close();
		return rowInserted;
	}

	public boolean deleteUser(int userid) throws SQLException {
		String sql = "DELETE FROM User WHERE id = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, userid);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		close();
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		String sql = "update User set firstname=?, lastname =?,email = ?, password = ?, gender = ?, birthday = ? where id = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setString(5, user.getGender());
		preparedStatement.setString(6, user.getBirthday());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		close();
		return rowUpdated;
	}

	
	
	public User getUser(String email) throws SQLException {
		User user = null;
		String sql = "SELECT * FROM User WHERE email = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			String useremail = resultSet.getString("email");
			String password = resultSet.getString("password");
			String gender = resultSet.getString("gender");
			String birthday = resultSet.getString("birthday");

			user = new User(firstname, lastname, useremail, password, gender, birthday);
		}

		close();
		return user;

	}

}