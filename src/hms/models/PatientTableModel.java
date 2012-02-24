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
	private String[] colNames;
	private String[] colClasses;
	private Object[][] content;
	
	/**
	 * Constructs a new PatientTableModel. Sets the colNames and colClasses and content
	 * instance variables. If they are inaccessible, sets them to arrays of empty strings.
	 */
	public PatientTableModel() {
		try {
			getTableColumnNamesAndClasses();
			getTableContents();
		} catch (SQLException sqle) {
			content = new Object[][] {{""}};
			colNames = new String[] {""};
		}
	}
	
	/**
	 * Returns the number of columns in the table.
	 * @return The number of columns in the table.
	 */
	public int getColumnCount() {
		return colNames.length;
	}
	
	/**
	 * Returns the number of rows in the table.
	 * @return The number of rows in the table.
	 */
	public int getRowCount() {
		return content.length;
	}
	
	/**
	 * Returns the object in the database in the row'th row and the col'th column in that
	 * row.
	 * @param row The row of the object to be returned
	 * @param col The column of the object to be returned in the row
	 * @return The object at the specified location in the database
	 */
	public Object getValueAt(int row, int col) {
		return content[row][col];
	}
	
	/**
	 * Cells are not editable because we have the create and edit forms
	 * @return false
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	/**
	 * Returns the name of the given column
	 * @param col The column to find the name of
	 * @return The name of the given column
	 */
	public String getColumnName(int col) {
		return colNames[col];
	}
	
	/**
	 * Gets all of the table column names and storage classes from the database metadata and
	 * stores them in the appropriate instance variables.
	 */
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
	
	/**
	 * Gets all of the table contents and stores them in an array of an array of Objects
	 * so we do not need to query the database all of the time.
	 */
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
	
	/**
	 * Called when the table data has been changed. Re-fetches the table contents from
	 * the database and then calls the superclass' implementation.
	 */
	public void fireTableDataChanged() {
		try {
			getTableContents();
		} catch (SQLException sqle) {
		}
		super.fireTableDataChanged();
	}
}