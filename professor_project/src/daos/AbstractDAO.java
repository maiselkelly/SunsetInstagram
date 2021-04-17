package daos;

import static daos.AbstractDAO.resultSet;
import static daos.AbstractDAO.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public abstract class AbstractDAO extends HttpServlet{

	protected static final long serialVersionUID = 1L;
	protected static Connection connect = null;
	protected static Statement statement = null;
	protected static PreparedStatement preparedStatement = null;
	protected static ResultSet resultSet = null;
	
	
	protected void connect_func() throws SQLException {
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/project?" + "useSSL=false&user=john&password=pass1234");
			System.out.println(connect);
			statement = (Statement) connect.createStatement();
		}
	}
	
	// close necessary items
	protected void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}
		
			
		} catch (Exception e) {

		}
	}
	
	
	
}
