package hms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.io.IOException;

public class Database {
	private static Database instance = null;
	private Connection connection = null;
	
	/**
	 * Sole constructor. Is used by the getInstance() method to create an instance of
	 * the Database to give out.
	 */
	protected Database() throws SQLException {
		makeConnection();
	}
	
	/**
	 * Creates a database connection to the mysql database and stores the connection
	 * object in the object.
	 */
	private void makeConnection() throws SQLException {
		Properties configFile = new Properties();
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("db/test.properties"));
		} catch (IOException ioe) {
			System.err.println("Couldn't open config file");
			System.exit(0);
		}
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", configFile.getProperty("user"));
		connectionProperties.put("password", configFile.getProperty("password"));
		
		this.connection = DriverManager.getConnection("jdbc:" + configFile.getProperty("dbms") + 
			"://" + configFile.getProperty("url") + 
			":" + configFile.getProperty("port") + 
			"/" + configFile.getProperty("db"),
			configFile.getProperty("user"), configFile.getProperty("password"));
	}
	
	/**
	 * Closes the database connection. This should be called when the application is quit.
	 */
	public void closeConnection() throws SQLException {
		System.out.println("Releasing database resources...");
		if (this.connection != null) {
			this.connection.close();
			this.connection = null;
		}
	}
	
	/**
	 * Sends the query to the db, and receives the result of the query from the db.
	 * 
	 * @param {@link String} that represents the query to be made to the db.
	 * @return {@link ResultSet} containing the results of the query
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
		ResultSet results = statement.executeQuery(query);
		
		return results;
	}
	
	/**
	 * Sends the update statement to the db and returns the rows affected
	 * 
	 * @param {@link String} that represents the query to be made to the db.
	 * @return {@link int} row count for inserts, updates and deletes or 0 for queries that
	 *	return nothing
	 * @throws SQLException
	 */
	public int executeUpdate(String query) throws SQLException {
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
		int results = statement.executeUpdate(query);
		
		return results;
	}
	
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