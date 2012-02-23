package hms.models;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

import org.apache.commons.codec.digest.DigestUtils;

import hms.db.Database;
import hms.Managers.Encryptor;

public class User {
	private String username;
	private String password;
	public ArrayList<String> errors = new ArrayList<String>();
	
	/**
	 * Authenticates the given user credentials against the user table in the database.
	 * If the username and password match the data in the table, returns true. Otherwise 
	 * returns false.
	 * @param username Username of the user to be authenticated
	 * @param password Password of the user to be authenticated
	 * @return true if the credentials match; false otherwise
	 */
	public static boolean authenticate(String username, String password) throws SQLException {
		ResultSet user = Database.getInstance().executeQuery("SELECT username, password, COUNT(*) FROM user WHERE username = '" + username + "'");
		user.next();
		if (user.getInt(3) == 0) {
			return false;
		}
		if (user.getString("password").equals(DigestUtils.md5Hex(password))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Tries to save the user to the database. If the username already exists in the database,
	 * it returns false. If the user is saved successfully, then it returns true.
	 * @return true if the user is saved succesfully; false otherwise
	 */
	public boolean save() throws SQLException {
		ResultSet user = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = '" + this.username + "'");
		user.next();
		int count = user.getInt(1);
		if (count > 0) {
			this.errors.add("Username exists");
			return false;
		} else {
			try {
				ResultSet users = Database.getInstance().executeQuery("SELECT * FROM user");
				users.moveToInsertRow();
				users.updateString("username", this.username);
				users.updateString("password", this.password);
				users.insertRow();
				this.errors.clear();
				return true;
			} catch (SQLException sqle) {
				System.err.println(sqle);
				this.errors.add("Couldn't add to database");
				return false;
			}
		}
	}
	
	/**
	 * Tries to delete the user from the database. If the username already exists in the database,
	 * it returns false. If the user is saved successfully, then it returns true.
	 * @return true if the user is saved succesfully; false otherwise
	 */
	public boolean delete() throws SQLException {
		try {
			int users = Database.getInstance().executeUpdate("DELETE FROM user WHERE username = '" + this.username + "'");
			if (users == 0) {
				this.errors.add("User does not exist");
				return false;
			}
			this.errors.clear();
			return true;
		} catch (SQLException sqle) {
			System.err.println(sqle);
			this.errors.add("Could not delete user");
			return false;
		}
	}
	
	/**
	 * Creates a new User instance.
	 * @param username The username of the user
	 * @param password The unencrypted password of the user
	 */
	protected User(String username, String password) {
		this.username = username;
		this.password = DigestUtils.md5Hex(password);
	}
}