package a00123456.assignment.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
 

public class Database {

	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://Beangrinder.bcit.ca";
	private String user = "javastudent";
	private String password = "compjava";
	public static Connection connection;
	
	
	public Database(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Database() {
		this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		this.url = "jdbc:sqlserver://Beangrinder.bcit.ca";
		this.user = "javastudent";
		this.password = "compjava";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Connect() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createTable(String createQuery) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(createQuery);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;		
	}
	
	public int insertData(String insertQuery) {
		int numResults = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numResults;
	}
	
	public ResultSet getData(String selectQuery) {
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(selectQuery);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public boolean dropTable(String dropQuery) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(dropQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void shutDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean tableExists(String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
		String name = null;
		while (resultSet.next()) {
			name = resultSet.getString("TABLE_NAME");
			if (name.equalsIgnoreCase(tableName)) {
				return true;
			}
		}
		return false; 

	}
}
