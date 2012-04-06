package hms.models;

import java.sql.SQLException;

public interface AbstractModel {
	/**
	 * Should delete the object's representation in the database.
	 * @throws SQLException If there is an SQL error
	 */
	public boolean delete() throws SQLException;
	
	/**
	 * Should create the object's representation in the database.
	 * @throws SQLException If there is an SQL error
	 */
	public boolean create() throws SQLException;
}