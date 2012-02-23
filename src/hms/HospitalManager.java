package hms;

import hms.Views.LoginView;
import hms.models.User;

class HospitalManager
{
	public static void main(String[] args) {
		try {
			User new_user = new User("admin", "password");
			new_user.create();
	 		LoginView loginView = new LoginView();
			loginView.frmLogin.setVisible(true);
	 	} catch (Exception e) {
	 		
	 	}
	}
}