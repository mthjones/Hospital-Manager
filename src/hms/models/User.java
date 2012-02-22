package hms.models;

import java.sql.SQLException;
import java.sql.ResultSet;

import hms.db.Database;
import hms.Managers.Encryptor;

public class User {
	/**
	 * Authenticates the given user credentials against the user table in the database.
	 * If the username and password match the data in the table, returns true. Otherwise 
	 * returns false.
	 * @param username Username of the user to be authenticated
	 * @param password Password of the user to be authenticated
	 * @return true if the credentials match; false otherwise
	 */
	public static boolean authenticate(String username, String password) throws SQLException {
		ResultSet user = Database.getInstance().executeQuery("SELECT username, password FROM user WHERE username = '" + username + "'");
		if (user.getString("password") == Encryptor.encode(password)) {
			return true;
		}
		return false;
	}
	
	public static User create(String username, String password) throws SQLException {
		ResultSet user = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = '" + username + "'");
		user.next();
		int count = user.getInt(1);
		if (count > 0) {
			return null;
		} else {
			return null;
		}
	}
}