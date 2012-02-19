package hms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	private static Database instance = null;
	private Connection connection = null;
	private String url = "tomdabom.no-ip.biz";
	private String userName = "HMSUsers";
	private String password = "hmsistheshit";
	private String port = "3306";
	
	/**
	 * Sole constructor. Is used by the getInstance() method to create an instance of
	 * the Database to give out.
	 */
	protected Database() throws SQLException {
		
	}
	
	/**
	 * Creates a database connection to the mysql database and stores the connection
	 * object in the object.
	 */
	private void makeConnection() throws SQLException {
		Properties connectionProperties = null;
		connectionProperties.put("user", this.userName);
		connectionProperties.put("password", this.password);
		
		this.connection = DriverManager.getConnection("jdbc:mysql://" + 
			this.url + ":" + this.port + "/" + connectionProperties);
	}
	
	/**
	 * Closes the database connection. This should be called when the application is quit.
	 */
	// public void closeConnection() {
	// 	System.out.println("Releasing database resources...");
	// 	this.connection.close();
	// 	this.connection = null;
	// }
	
	/**
	 * Returns the singleton instance of the database. If there is already an instance of
	 * the database, returns the instance. If there is not, it creates the instance before
	 * returning it.
	 * @return 	The database object
	 */
	public static Database getInstance() throws SQLException {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
}