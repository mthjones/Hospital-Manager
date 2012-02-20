package hms.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class PatientView {

	public JFrame frmPatient;

	/**
	 * Create the application.
	 */
	public PatientView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPatient = new JFrame();
		frmPatient.setTitle("Patient");
		frmPatient.setBounds(100, 100, 541, 425);
		frmPatient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
