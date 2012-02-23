package hms;

import hms.Views.LoginView;

class HospitalManager
{
	public static void main(String[] args) {
		try {
	 		LoginView loginView = new LoginView();
			loginView.frmLogin.setVisible(true);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 	}
	}
}