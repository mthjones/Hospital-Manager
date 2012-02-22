package hms.models;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.sql.ResultSet;

import hms.db.Database;

public class Patient {
	public Object getValueAt(int row, int col) {
		return 1;
	}
	
	public int getColumnCount() {
		return 1;
	}
	
	public int getRowCount() {
		try {
			ResultSet count = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient");
			count.next();
			return count.getInt(1);
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
		return 0;
	}
	
	// public static Patient find(int id) {
		
	// }
	
	// public static Array<Patient> select_all() {
		
	// }
}