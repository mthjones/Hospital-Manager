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
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new MigLayout("nogrid, fillx"));
		buttonPanel.add(createButton, "sg, gap push");
		buttonPanel.add(editButton, "sg");
		buttonPanel.add(deleteButton, "sg");
		buttonPanel.add(refreshButton, "sg, gap unrel");
		return buttonPanel;
	}
	
	private Nurse getSelectedNurse() throws SQLException {
		try {
			return Nurse.find((Integer)nursesTableModel.getContent()[nursesTable.getSelectedRow()][6]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("create")) {
			NurseView nView = new NurseView(nursesTableModel);
			nView.frame.setVisible(true);
		} else if (e.getActionCommand().equals("edit")) {
			// Broken
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