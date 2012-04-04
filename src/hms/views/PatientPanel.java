package hms.views;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import hms.models.PatientTableModel;

public class PatientPanel extends JPanel {
	final private JTable patientsTable = new JTable(new PatientTableModel());
	final private JScrollPane patientsTablePane = new JScrollPane(patientsTable);
	//final private PatientInfoPanel patientInfoPanel = new PatientInfoPanel();
	final private JPanel patientInfoPanel = new JPanel();
	
	final private JTextField searchField = new JTextField(20);
	final private JButton searchButton = new JButton("Search");
	final private JButton createButton = new JButton("Create");
	final private JButton editButton = new JButton("Edit");
	final private JButton deleteButton = new JButton("Delete");
	final private JButton refreshButton = new JButton("Refresh");
	
	public PatientPanel() {
		initComponents();
	}
	
	private void initComponents() {
		this.setLayout(new MigLayout("", "[grow]"));
		
		patientsTable.setFillsViewportHeight(true);
		patientsTablePane.setMinimumSize(new Dimension(400, 50));
		patientInfoPanel.setMinimumSize(new Dimension(400, 50));
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		this.add(createSplitPane(), "push, grow, wrap");
		this.add(createButtonPanel(), "growx");
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new MigLayout("nogrid, fillx"));
		buttonPanel.add(searchField);
		buttonPanel.add(searchButton, "sg");
		buttonPanel.add(createButton, "sg, gap push");
		buttonPanel.add(editButton, "sg");
		buttonPanel.add(deleteButton, "sg");
		buttonPanel.add(refreshButton, "sg, gap unrel");
		return buttonPanel;
	}
	
	private JSplitPane createSplitPane() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, patientsTablePane, patientInfoPanel);
		splitPane.setDividerLocation(400);
		splitPane.setResizeWeight(0.5);
		splitPane.setBorder(null);
		return splitPane;
	}
}