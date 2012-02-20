package hms.models;

import javax.swing.table.AbstractTableModel;

public class Patient extends AbstractTableModel {
	public Object getValueAt(int row, int col) {
		return 1;
	}
	
	public int getColumnCount() {
		return 1;
	}
	
	public int getRowCount() {
		return 1;
	}
}