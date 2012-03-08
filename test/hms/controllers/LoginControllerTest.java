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
		LoginView loginView = new LoginView(loginController);
		loginController.setView(loginView);
		
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
	public void test_windowClosedOnSuccessfulLogin() throws SQLException {
		User test_user = new User("test", "testpass");
		test_user.create();
		
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(loginController);
		loginController.setView(loginView);
		
		JFrame testFrame = new JFrame();
		testFrame.getRootPane().add(loginView);
		testFrame.pack();
		
		loginController.login("test", "testpass");
		assertFalse(SwingUtilities.getWindowAncestor(loginView).isDisplayable());
		
		test_user.delete();
	}
	
	@Test
	public void test_windowStaysOpenOnUnsuccessfulLogin() {
		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(loginController);
		loginController.setView(loginView);
		
		JFrame testFrame = new JFrame();
		testFrame.getRootPane().add(loginView);
		testFrame.pack();
		
		loginController.login("Invalid user", "Invalid pass");
		assertTrue(SwingUtilities.getWindowAncestor(loginView).isDisplayable());
	}
}