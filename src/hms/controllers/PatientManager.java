package hms.controllers;

import javax.swing.JFrame;

import hms.views.PatientView;
import hms.views.DeletePatientConfirmationView;
import hms.models.Patient;
import hms.models.PatientTableModel;

public class PatientManager {
	private PatientTableModel mainViewTableModel;

	public PatientManager(PatientTableModel tableModel) {
		this.mainViewTableModel = tableModel;
	}

	public void EditPatient(JFrame mainScreenJFrame, String[] row) {
		OpenPatientView(row);
	}
    
	public void CreatePatient(JFrame mainScreenJFrame) {
		OpenPatientView();
	}
	
	public void deletePatient(JFrame frmMain, String healthcareNumber) {
		DeletePatientConfirmationView confirmationView = new DeletePatientConfirmationView(healthcareNumber, mainViewTableModel);
		confirmationView.frmConfirmDeletePatient.setVisible(true);
	}
	
	public static boolean doDeletePatient(String healthcareNumber) {
		return Patient.deleteFromString(healthcareNumber);
	}
	
	private void OpenPatientView() {
		PatientView patientView = new PatientView(mainViewTableModel);
		patientView.frmPatient.setVisible(true);
	}
	
	private void OpenPatientView(String[] row) {
		PatientView patientView = new PatientView(row, mainViewTableModel);
		patientView.frmPatient.setVisible(true);
	}
}
