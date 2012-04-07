package hms.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.SQLException;
import java.util.ArrayList;

import net.miginfocom.swing.MigLayout;

import hms.models.Patient;
import hms.models.PatientTableModel;

public class PatientPanel extends JPanel implements ActionListener {
	final private PatientTableModel patientsTableModel = new PatientTableModel();
	final private JTable patientsTable = new JTable(patientsTableModel);
	final private JScrollPane patientsTablePane = new JScrollPane(patientsTable);
	final private PatientInfoPanel patientInfoPanel = new PatientInfoPanel();
	final private JScrollPane patientInfoScrollPane = new JScrollPane(patientInfoPanel);
	
	final private JTextField searchField = new JTextField(20);
	final private JButton searchButton = new JButton("Search");
	final private JButton clearButton = new JButton("Clear");
	final private JButton createButton = new JButton("Create");
	final private JButton editButton = new JButton("Edit");
	final private JButton deleteButton = new JButton("Delete");
	final private JButton refreshButton = new JButton("Refresh");
	final private JCheckBox viewAllCheckbox = new JCheckBox("View All");
	
	final private TableRowSorter<PatientTableModel> sorter = new TableRowSorter<PatientTableModel>(patientsTableModel);
	final private ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
	private RowFilter<Object,Object> searchFilter;
	
	public PatientPanel() {
		initUI();
	}
	
	/**
	 * Initializes the components for this panel.
	 */
	private void initUI() {
		this.setLayout(new MigLayout("", "[grow]"));
		
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
				final ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				editButton.setEnabled(!lsm.isSelectionEmpty());
				deleteButton.setEnabled(!lsm.isSelectionEmpty());
				patientInfoPanel.reset();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						try{
							Patient selectedPatient = getSelectedPatient();
							if (selectedPatient != null) {
								patientInfoPanel.loadInformation(selectedPatient);
							}
						} catch (SQLException sqle) {}
					}
				});
			}
		});
		
		final RowFilter<Object,Object> showOnlyInHospital = new RowFilter<Object,Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				for (int i = entry.getValueCount() - 1; i >= 0; i--) {
					if (entry.getValue(i).equals(true)) {
						return true;
					}
				}
				return false;
			}
		};
		
		patientsTable.setRowSorter(sorter);
		filters.add(showOnlyInHospital);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		
		searchButton.setActionCommand("search");
		searchButton.addActionListener(this);
		searchField.addFocusListener(new FocusListener () {

			@Override
			public void focusGained(FocusEvent arg0) {
				searchButton.getRootPane().setDefaultButton(searchButton);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				searchButton.getRootPane().setDefaultButton(null);
				
			}

		});
		
		clearButton.setActionCommand("clear");
		clearButton.addActionListener(this);
		
		viewAllCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filters.remove(showOnlyInHospital);
				} else {
					filters.add(showOnlyInHospital);
				}
				sorter.setRowFilter(RowFilter.andFilter(filters));
			}
		});
		
		patientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		patientsTable.setFillsViewportHeight(true);
		patientsTablePane.setMinimumSize(new Dimension(600, 50));
		patientInfoScrollPane.setMinimumSize(new Dimension(400, 50));
		
		patientInfoPanel.setEditable(false);
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		this.add(createSplitPane(), "push, grow, wrap");
		this.add(createButtonPanel(), "growx");

		
		
	}
	
	/**
	 * Forms the split pane with the table of patients on the left and the patient information
	 * section on the right and returns it.
	 * @return the split pane with the table and information
	 */
	private JSplitPane createSplitPane() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, patientsTablePane, patientInfoScrollPane);
		splitPane.setDividerLocation((int)this.getSize().getWidth() - 400);
		splitPane.setResizeWeight(0.9);
		splitPane.setBorder(null);
		return splitPane;
	}
	
	/**
	 * Forms a panel that contains the buttons for the main panel as well as the search field.
	 * @return A panel of buttons
	 */
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new MigLayout("nogrid, fillx"));
		buttonPanel.add(searchField);
		buttonPanel.add(searchButton, "sg");
		buttonPanel.add(clearButton, "sg");
		buttonPanel.add(viewAllCheckbox, "sg");
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
			return Patient.find((String)patientsTable.getValueAt(patientsTable.getSelectedRow(), 0));
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Handles action events that occur from any object that has this panel as an action listener.
	 * @param e The event to be handled
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("search")) {
			try {
				try {
					searchFilter = RowFilter.regexFilter("" + Integer.parseInt(searchField.getText()), 0);
				} catch (NumberFormatException nfe) {
					searchFilter = RowFilter.regexFilter("(?i)" + searchField.getText(), 1);
				}
				filters.add(searchFilter);
				sorter.setRowFilter(RowFilter.andFilter(filters));
			} catch (java.util.regex.PatternSyntaxException pse) { }
		} else if (e.getActionCommand().equals("clear")) {
			filters.remove(searchFilter);
			searchFilter = null;
			searchField.setText("");
			sorter.setRowFilter(RowFilter.andFilter(filters));
		} else if (e.getActionCommand().equals("create")) {
			new InfoDialog(SwingUtilities.windowForComponent(this), "Create Patient", new PatientInfoPanel());
			this.patientsTableModel.fireTableDataChanged();
		} else if (e.getActionCommand().equals("edit")) {
			try {
				PatientInfoPanel editPanel = new PatientInfoPanel();
				editPanel.loadInformation(getSelectedPatient());
				new InfoDialog(SwingUtilities.windowForComponent(this), "Edit Patient", editPanel);
			} catch (SQLException sqle) {}
			this.patientsTableModel.fireTableDataChanged();
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