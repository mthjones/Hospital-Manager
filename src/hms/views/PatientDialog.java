package hms.views;

import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;

import net.miginfocom.swing.MigLayout;

import hms.models.Patient;

public class PatientDialog {
	final private String title;
	final private Window parent;
	private JDialog dialog;
	final private PatientInfoPanel patientInfoPanel = new PatientInfoPanel();
	
	final private JButton saveButton = new JButton("Save");
	final private JButton cancelButton = new JButton("Cancel");
	
	public PatientDialog(Window parent) {
		this.title = "Create Patient";
		this.parent = parent;
		initUI();
	}
	
	public PatientDialog(Window parent, Patient patient) {
		this.title = "Edit Patient";
		this.parent = parent;
		this.patientInfoPanel.loadPatientInformation(patient);
		initUI();
	}
	
	/**
	 * Intitializes the components of the dialog.
	 */
	private void initUI() {
		this.dialog = new JDialog(this.parent, this.title, Dialog.ModalityType.APPLICATION_MODAL);
		
		Container contentPane = this.dialog.getContentPane();
		
		contentPane.setLayout(new MigLayout("", "[grow,fill]"));
		contentPane.add(this.patientInfoPanel, "wrap, growx");
		contentPane.add(createButtonPanel());
		
		this.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		this.saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Patient patient = patientInfoPanel.patientFromInformation();
					try {
						patient.delete();
						patient.create();
					} catch (SQLException sqle) {}
				} catch (ParseException pe) {}
				close();
			}
		});
		
		this.dialog.setMinimumSize(new Dimension(400, 0));
		this.dialog.pack();
		this.dialog.setLocationRelativeTo(this.parent);
		this.dialog.setVisible(true);
	}
	
	/**
	 * Closes the patient dialog.
	 */
	public void close() {
		this.dialog.dispose();
	}
	
	/**
	 * Creates the button panel for the dialog.
	 * @return the button panel for the dialog.
	 */
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new MigLayout("nogrid, fillx"));
		buttonPanel.add(saveButton, "sg, gap push");
		buttonPanel.add(cancelButton, "sg");
		return buttonPanel;
	}
}