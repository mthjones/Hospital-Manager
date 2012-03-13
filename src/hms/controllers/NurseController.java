package hms.controllers;

import hms.views.NurseView;
import hms.views.UserView;
import hms.models.NurseTableModel;

import javax.swing.JFrame;

public class NurseController {
	
	private NurseTableModel mainViewTableModel;

	public NurseController(NurseTableModel tableModel) {
		this.mainViewTableModel = tableModel;
	}
	
	public void EditNurse(JFrame mainScreenJFrame, String[] row) {
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
