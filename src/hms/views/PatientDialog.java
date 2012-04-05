package hms.views;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import hms.models.Patient;

public class PatientDialog {
	private String title;
	private Window parent;
	
	public PatientDialog(Window parent) {
		this.title = "Create Patient";
		this.parent = parent;
		initUI();
	}
	
	public PatientDialog(Window parent, Patient patient) {
		this.title = "Edit Patient";
		this.parent = parent;
		initUI();
	}
	
	private void initUI() {
		JDialog pDialog = new JDialog(this.parent, this.title, Dialog.ModalityType.APPLICATION_MODAL);
		
		pDialog.add(new PatientInfoPanel());
		pDialog.setMinimumSize(new Dimension(400, 0));
		pDialog.pack();
		pDialog.setResizable(false);
		pDialog.setLocationRelativeTo(this.parent);
		pDialog.setVisible(true);
	}
}