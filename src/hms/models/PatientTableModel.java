package hms.models;

import javax.swing.table.AbstractTableModel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;

import hms.db.Database;

public class PatientTableModel extends AbstractTableModel {
	String[] colNames;
	String[] colClasses;
	Object[][] content;
	
	public PatientTableModel() {
		try {
			getTableColumnNamesAndClasses();
			getTableContents();
		} catch (SQLException sqle) {
			content = new Object[][] {{""}};
			colNames = new String[] {""};
		}
	}
	
	public int getColumnCount() {
		return colNames.length;
	}
	
	public int getRowCount() {
		return content.length;
	}
	
	public Object getValueAt(int row, int col) {
		return content[row][col];
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public String getColumnName(int col) {
		return colNames[col];
	}
	
	private void getTableColumnNamesAndClasses() throws SQLException {
		ResultSet rs = Database.getInstance().executeQuery("SELECT * FROM patient");
		
		ResultSetMetaData rsMeta = rs.getMetaData();
		
		colNames = new String[rsMeta.getColumnCount()];
		colClasses = new String[rsMeta.getColumnCount()];
		
		for (int i = 0; i < rsMeta.getColumnCount(); i++) {
			colNames[i] = rsMeta.getColumnName(i+1);
			colClasses[i] = rsMeta.getColumnClassName(i+1);
		}
		
		rs.close();
	}
	
	private void getTableContents() throws SQLException {
		ResultSet rs = Database.getInstance().executeQuery("SELECT * FROM patient");
		
		ArrayList<Object[]> rowList = new ArrayList<Object[]>();
		while (rs.next()) {
			ArrayList<Object> cellList = new ArrayList<Object>();
			for (int i = 0; i < colClasses.length; i++) {
				Object cellValue = null;
				
				if (colClasses[i].equals(String.class.getName())) {
					cellValue = rs.getString(colNames[i]);
				} else if (colClasses[i].equals(Integer.class.getName())) {
					cellValue = new Integer(rs.getInt(colNames[i]));
				} else if (colClasses[i].equals(Double.class.getName())) {
					cellValue = new Double(rs.getDouble(colNames[i]));
				} else if (colClasses[i].equals(Date.class.getName())) {
					cellValue = rs.getDate(colNames[i]);
				} else if (colClasses[i].equals(Float.class.getName())) {
					cellValue = rs.getFloat(colNames[i]);
				} else if (colClasses[i].equals(Character.class.getName())) {
					cellValue = new Character(rs.getString(colNames[i]).charAt(0));
				} else {
					cellValue = rs.getString(colNames[i]);
				}
				cellList.add(cellValue);
			}
			Object[] cells = cellList.toArray();
			rowList.add(cells);
		}
		
		rs.close();
		
		content = new Object[rowList.size()][];
		for (int i = 0; i < content.length; i++) {
			content[i] = rowList.get(i);
		}
	}
	
	public void fireTableDataChanged() {
		try {
			getTableContents();
		} catch (SQLException sqle) {
		}
		super.fireTableDataChanged();
	}
}