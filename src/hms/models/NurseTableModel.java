package hms.models;

import javax.swing.table.AbstractTableModel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import hms.util.Database;

public class NurseTableModel extends AbstractTableModel{
	private String[] columnNames;
	private String[] columnClasses;
	private Object[][] content;

	/**
	 * Constructs a new NurseTableModel. Sets the columnNames and columnClasses and content
	 * instance variables. If they are inaccessible, sets them to arrays of empty strings.
	 */

	public Object[][] getContent(){
		return content;
	}

	public NurseTableModel(){
		try {
			getTableColumnNamesAndClasses();
			getTableContents();
		} catch (SQLException sqle) {
			content = new Object[][] {{"", ""}};
			columnNames = new String[] {"", ""};
		}
	}

	/**
	 * Returns the number of columns in the table.
	 * @return The number of columns in the table.
	 */
	public int getColumnCount() {
		return columnNames.length;
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
		return columnNames[col];
	}

	/**
	 * Gets all of the table column names and storage classes from the database metadata and
	 * stores them in the appropriate instance variables.
	 */
	private void getTableColumnNamesAndClasses() throws SQLException {
		ResultSet nurses = Database.getInstance().executeQuery("SELECT * FROM nurse");

		ResultSetMetaData nurseMeta = nurses.getMetaData();

		columnNames = new String[nurseMeta.getColumnCount()];
		columnClasses = new String[nurseMeta.getColumnCount()];

		for (int i = 0; i < nurseMeta.getColumnCount(); i++) {
			columnNames[i] = nurseMeta.getColumnName(i+1);
			columnClasses[i] = nurseMeta.getColumnClassName(i+1);
		}

		nurses.close();
	}

	/**
	 * Gets all of the table contents and stores them in an array of an array of Objects
	 * so we do not need to query the database all of the time.
	 */
	private void getTableContents() throws SQLException {
		ResultSet nurses = Database.getInstance().executeQuery("SELECT * FROM nurse");

		ArrayList<Object[]> rowList = new ArrayList<Object[]>();
		while (nurses.next()) {
			ArrayList<Object> cellList = new ArrayList<Object>();
			for (int i = 0; i < columnClasses.length; i++) {
				Object cellValue = null;

				if (columnClasses[i].equals(String.class.getName())) {
					cellValue = nurses.getString(columnNames[i]);
				} else if (columnClasses[i].equals(Integer.class.getName())) {
					//This if statement is pretty shoddy...update if time allows
					//i = 9 is first ward, i = 19 is second ward, basically i + 10 from then on
					if((i % 9) == 0 || (i % 9) == 1)
					{
						//Assigns ward names instead of numbers for wards
						cellValue = Ward.getSingleWardName(nurses.getInt(columnNames[i]));
					}
					else
					{
						cellValue = new Integer(nurses.getInt(columnNames[i]));
					}
				} else if (columnClasses[i].equals(Double.class.getName())) {
					cellValue = new Double(nurses.getDouble(columnNames[i]));
				} else if (columnClasses[i].equals(Date.class.getName())) {
					cellValue = nurses.getDate(columnNames[i]);
				} else if (columnClasses[i].equals(Float.class.getName())) {
					cellValue = nurses.getFloat(columnNames[i]);
				} else if (columnClasses[i].equals(Character.class.getName())) {
					cellValue = new Character(nurses.getString(columnNames[i]).charAt(0));
				} else {
					cellValue = nurses.getString(columnNames[i]);
				}
				cellList.add(cellValue);
			}
			Object[] cells = cellList.toArray();
			rowList.add(cells);
		}

		nurses.close();

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
			// Do nothing. Keep old contents
		}
		super.fireTableDataChanged();
	}
}