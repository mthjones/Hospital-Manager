package hms.controllers;

import org.junit.*;
import static org.junit.Assert.*;

import javax.swing.*;
import java.sql.SQLException;

import hms.controllers.LoginController;
import hms.views.LoginView;
import hms.util.Database;
import hms.models.User;

public class LoginControllerTest {
	@Test
	public void test_invalidLoginSetsInvalidLoginErrorMessage() {
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(null, loginController);
		
		loginController.login("Invalid user", "Invalid pass");
		assertEquals(loginView.getErrorMessage(), "Invalid login");
	}
	
	// @Test
	// public void test_databaseErrorSetsDatabaseErrorErrorMessage() {
	// 	LoginController loginController = new LoginController();
	// 	LoginView loginView = new LoginView(loginController);
	// 	loginController.setView(loginView);
		
	// 	// Simulate database connection error?
		
	// 	loginController.login("Hello", "World");
	// 	assertEquals(loginView.getErrorMessage(), "Database error");
	// }
	
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
}