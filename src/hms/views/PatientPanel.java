package hms.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.SQLException;

import net.miginfocom.swing.MigLayout;

import hms.models.Patient;
import hms.models.PatientTableModel;

public class PatientPanel extends JPanel implements ActionListener {
	final private PatientTableModel patientsTableModel = new PatientTableModel();
	final private JTable patientsTable = new JTable(patientsTableModel);
	final private JScrollPane patientsTablePane = new JScrollPane(patientsTable);
	final private PatientInfoPanel patientInfoPanel = new PatientInfoPanel();
	
	final private JTextField searchField = new JTextField(25);
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
		
		searchButton.setActionCommand("search");
		searchButton.addActionListener(this);
		createButton.setActionCommand("create");
		createButton.addActionListener(this);
		editButton.setActionCommand("edit");
		editButton.addActionListener(this);
		deleteButton.setActionCommand("delete");
		deleteButton.addActionListener(this);
		refreshButton.setActionCommand("refresh");
		refreshButton.addActionListener(this);
		
		patientsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				editButton.setEnabled(!lsm.isSelectionEmpty());
				deleteButton.setEnabled(!lsm.isSelectionEmpty());
				patientInfoPanel.clearPatientInformation();
				try {
					Patient selectedPatient = getSelectedPatient();
					if (selectedPatient != null) {
						patientInfoPanel.loadPatientInformation(selectedPatient);
					}
				} catch (SQLException sqle) {}
			}
		});
		
		patientsTable.setFillsViewportHeight(true);
		patientsTablePane.setMinimumSize(new Dimension(600, 50));
		patientInfoPanel.setMinimumSize(new Dimension(400, 50));
		
		patientInfoPanel.setEditable(false);
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		this.add(createSplitPane(), "push, grow, wrap");
		this.add(createButtonPanel(), "growx");
	}
	
	private JSplitPane createSplitPane() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, patientsTablePane, patientInfoPanel);
		splitPane.setDividerLocation((int)this.getSize().getWidth() - 400);
		splitPane.setResizeWeight(0.9);
		splitPane.setBorder(null);
		return splitPane;
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
	
	/**
	 * Returns a Patient object that corresponds to the currently selected patient in the table.
	 * @return A patient object corresponding to the currently selected patient
	 */
	private Patient getSelectedPatient() throws SQLException {
		try {
			return Patient.find((String)patientsTableModel.getContent()[patientsTable.getSelectedRow()][0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("search")) {
			// NYI
		} else if (e.getActionCommand().equals("create")) {
			PatientView pView = new PatientView(patientsTableModel);
			pView.frmPatient.setVisible(true);
		} else if (e.getActionCommand().equals("edit")) {
			// Broken
		} else if (e.getActionCommand().equals("delete")) {
			if (JOptionPane.showConfirmDialog(this, "Delete selected patient?", "Delete patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				try {
					getSelectedPatient().delete();
				} catch (SQLException sqle) {}
				patientsTableModel.fireTableDataChanged();
			}
		} else if (e.getActionCommand().equals("refresh")) {
			this.patientsTableModel.fireTableDataChanged();
		}
	}
}