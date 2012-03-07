package hms.controllers;

import javax.swing.JFrame;

import hms.views.PatientView;

public class PatientManager {
	public void EditPatient(JFrame mainScreenJFrame, String[] row) {
		OpenPatientView(row);
	}

	public void CreatePatient(JFrame mainScreenJFrame) {
		OpenPatientView();
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
