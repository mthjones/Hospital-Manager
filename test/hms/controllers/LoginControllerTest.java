package hms.controllers;

import org.junit.*;
import static org.junit.Assert.*;

import javax.swing.*;
import java.sql.SQLException;

import hms.views.*;
import hms.util.*;
import hms.models.*;

public class LoginControllerTest {
	@Test
	public void test_invalidLoginSetsInvalidLoginErrorMessage() {
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(null, loginController);
		
		loginController.login("Invalid user", "Invalid pass");
		assertEquals(loginView.getErrorMessage(), "Invalid login");
	}
	
	@Test
	public void test_dialogClosedOnSuccessfulLogin() throws SQLException {
		User test_user = new User("test", "testpass");
		test_user.create();
		
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(null, loginController);
		
		loginController.login("test", "testpass");
		assertFalse(loginView.getDialog().isDisplayable());
		
		test_user.delete();
	}
	
	@Test
	public void test_dialogStaysOpenOnUnsuccessfulLogin() {
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(null, loginController);
		
		loginController.login("Invalid user", "Invalid pass");
		assertTrue(loginView.getDialog().isDisplayable());
	}
		
	@Test
	public void test_canLoginWithNurse() throws SQLException {
		Nurse nurse = new Nurse("","","","","","",Nurse.generateIDNumber(),"",0,0,"password");
		nurse.create();
		
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(null, loginController);
		
		loginController.login(Integer.toString(nurse.getID()), nurse.getPassword());
		assertFalse(loginView.getDialog().isDisplayable());
		
		nurse.delete();
	}
	
	@Test
	public void test_nurseLoginIsNotAdminAuthorized() throws SQLException {
		Nurse nurse = new Nurse("","","","","","",Nurse.generateIDNumber(),"",0,0,"password");
		nurse.create();
		
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(null, loginController);
		
		loginController.login(Integer.toString(nurse.getID()), nurse.getPassword());
		assertFalse(loginController.getIsAdminAuthorized());
		
		nurse.delete();
	}
	
	@Test
	public void test_userLoginIsAdminAuthorized() throws SQLException {
		User test_user = new User("test", "testpass");
		test_user.create();
		
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(null, loginController);
		
		loginController.login("test", "testpass");
		assertTrue(loginController.getIsAdminAuthorized());
		
		test_user.delete();
	}
}