package hms.controllers;

import java.sql.SQLException;

import hms.models.*;
import hms.views.LoginView;

public class LoginController {
	private LoginView view;
	
	public LoginController() {
		
	}
	
	public void login(String username, String password) {
		try {
			if (User.authenticate(username, password)) {
				this.view.close();
			} else if (Nurse.authenticate(username, password)){
				this.view.close();
			} else {
				view.setErrorMessage("Invalid login");
			}
		} catch (SQLException sqle) {
			view.setErrorMessage("Database error");
		}
	}
	
	public void setView(LoginView view) {
		this.view = view;
	}
}