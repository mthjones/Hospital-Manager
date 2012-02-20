package hms.Managers;

import javax.swing.JFrame;

import hms.Views.PatientView;

public class PatientManager {
	public void EditPatient(JFrame mainScreenJFrame) {
		OpenPatientView();
	}

	public void CreatePatient(JFrame mainScreenJFrame) {
		OpenPatientView();
	}
	
	private void OpenPatientView() {
		PatientView patientView = new PatientView();
		patientView.frmPatient.setVisible(true);
	}
}
