package hms.controllers;

import javax.swing.JFrame;

import hms.views.PatientView;
import hms.views.DeletePatientConfirmationView;
import hms.models.Patient;

public class PatientManager {
	public void EditPatient(JFrame mainScreenJFrame, String[] row) {
		OpenPatientView(row);
	}
    
	public void CreatePatient(JFrame mainScreenJFrame) {
		OpenPatientView();
	}
	
	public void deletePatient(JFrame frmMain, String healthcareNumber) {
		DeletePatientConfirmationView confirmationView = new DeletePatientConfirmationView(healthcareNumber);
		confirmationView.frmConfirmDeletePatient.setVisible(true);
	}
	
	public static boolean doDeletePatient(String healthcareNumber) {
		return Patient.deleteFromString(healthcareNumber);
	}
	
	private void OpenPatientView() {
		PatientView patientView = new PatientView();
		patientView.frmPatient.setVisible(true);
	}
	
	private void OpenPatientView(String[] row) {
		PatientView patientView = new PatientView(row);
		patientView.frmPatient.setVisible(true);
	}
}
