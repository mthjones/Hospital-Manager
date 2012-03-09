package hms.controllers;

import hms.views.NurseView;
import hms.views.UserView;

import javax.swing.JFrame;

public class NurseController {
	public void EditNurse(JFrame mainScreenJFrame) {
		OpenNurseView();
	}
    
	public void CreateNurse(JFrame mainScreenJFrame) {
		OpenNurseView();
	}
	
	private void OpenNurseView() {
		NurseView nurseView = new NurseView();
		nurseView.frame.setVisible(true);
	}
}
