package hms.controllers;

import hms.views.UserView;

import javax.swing.JFrame;

public class UserController {
		public void EditUser(JFrame mainScreenJFrame) {
			OpenUserView();
		}
	    
		public void CreateUser(JFrame mainScreenJFrame) {
			OpenUserView();
		}
		
		private void OpenUserView() {
			UserView userView = new UserView();
			userView.frmUser.setVisible(true);
		}
}