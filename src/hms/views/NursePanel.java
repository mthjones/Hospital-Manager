package hms.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.SQLException;

import net.miginfocom.swing.MigLayout;

import hms.models.Nurse;
import hms.models.NurseTableModel;

public class NursePanel extends JPanel implements ActionListener {
	NurseTableModel nursesTableModel = new NurseTableModel();
	JTable nursesTable = new JTable(nursesTableModel);
	JScrollPane nursesTablePane = new JScrollPane(nursesTable);
	
	final private JButton createButton = new JButton("Create");
	final private JButton editButton = new JButton("Edit");
	final private JButton deleteButton = new JButton("Delete");
	final private JButton refreshButton = new JButton("Refresh");
	
	public NursePanel() {
		initUI();
	}
	
	/**
	 * Intializes the components of this panel.
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
		
		nursesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				editButton.setEnabled(!lsm.isSelectionEmpty());
				deleteButton.setEnabled(!lsm.isSelectionEmpty());
			}
		});
		
		nursesTable.setFillsViewportHeight(true);
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		this.add(nursesTablePane, "push, grow, wrap");
		this.add(createButtonPanel(), "growx");
	}
	
	/**
	 * Forms a panel that contains the buttons for the main panel.
	 * @return A panel of buttons
	 */
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new MigLayout("nogrid, fillx"));
		buttonPanel.add(createButton, "sg, gap push");
		buttonPanel.add(editButton, "sg");
		buttonPanel.add(deleteButton, "sg");
		buttonPanel.add(refreshButton, "sg, gap unrel");
		return buttonPanel;
	}
	
	/**
	 * Returns the selected nurse from the table or null if there is no currently selected nurse.
	 * @return the selected nurse from the table or null if there is no currently selected nurse
	 */
	private Nurse getSelectedNurse() throws SQLException {
		try {
			return Nurse.find((Integer)nursesTableModel.getContent()[nursesTable.getSelectedRow()][6]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Handles action events that occur from any object that has this panel as an action listener.
	 * @param e The event to be handled
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("create")) {
			new InfoDialog(SwingUtilities.windowForComponent(this), "Create Nurse", new NurseInfoPanel());
			this.nursesTableModel.fireTableDataChanged();
		} else if (e.getActionCommand().equals("edit")) {
			try {
				NurseInfoPanel editPanel = new NurseInfoPanel();
				editPanel.loadInformation(getSelectedNurse());
				new InfoDialog(SwingUtilities.windowForComponent(this), "Edit Nurse", editPanel);
			} catch (SQLException sqle) {}
			this.nursesTableModel.fireTableDataChanged();
		} else if (e.getActionCommand().equals("delete")) {
			if (JOptionPane.showConfirmDialog(this, "Delete selected nurse?", "Delete nurse", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				try {
					getSelectedNurse().delete();
				} catch (SQLException sqle) {}
				this.nursesTableModel.fireTableDataChanged();
			}
		} else if (e.getActionCommand().equals("refresh")) {
			this.nursesTableModel.fireTableDataChanged();
		}
	}
}