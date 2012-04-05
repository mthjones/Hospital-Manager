package hms.views;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import hms.models.NurseTableModel;

public class NursePanel extends JPanel {
	NurseTableModel nursesTableModel = new NurseTableModel();
	JTable nursesTable = new JTable(nursesTableModel);
	JScrollPane nursesTablePane = new JScrollPane(nursesTable);
	
	public NursePanel() {
		initUI();
	}
	
	private void initUI() {
		this.setLayout(new MigLayout("", "[grow]"));
		
		nursesTable.setFillsViewportHeight(true);
		
		this.add(nursesTablePane, "push, grow");
	}
}