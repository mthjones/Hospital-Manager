package hms.models;

import java.sql.SQLException;

public interface AbstractModel {
	public boolean delete() throws SQLException;
	
	public boolean create() throws SQLException;
}