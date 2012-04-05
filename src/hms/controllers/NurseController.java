package hms.controllers;

import hms.views.NurseView;
//import hms.views.UserView;
// import hms.views.DeleteNurseConfirmationView;
import hms.models.NurseTableModel;
import hms.models.Nurse;

import javax.swing.JFrame;

public class NurseController {

	private NurseTableModel mainViewTableModel;

	public NurseController(NurseTableModel tableModel) {
		this.mainViewTableModel = tableModel;
	}
	public void EditNurse(String[] row) {
		if(row != null)
			OpenNurseView(row);
	}
	
	public void CreateNurse() {
		OpenNurseView();
	}

	private void OpenNurseView() {
		NurseView nurseView = new NurseView(mainViewTableModel);
		nurseView.frame.setVisible(true);
	}

	private void OpenNurseView(String[] row) {
		NurseView nurseView = new NurseView(mainViewTableModel, row);
		nurseView.frame.setVisible(true);
	}
}
