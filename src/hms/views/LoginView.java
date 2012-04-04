package hms.views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import net.miginfocom.swing.MigLayout;

import hms.controllers.LoginController;

public class LoginView extends JPanel implements ActionListener {
	final private JLabel usernameLabel = new JLabel("Username:");
	final private JLabel passwordLabel = new JLabel("Password:");
	final private JTextField usernameField = new JTextField(15);
	final private JPasswordField passwordField = new JPasswordField(15);
	final private JButton loginButton = new JButton("Login");
	final private JLabel errorMessage = new JLabel("");
	public boolean isNurse = false;
	
	private LoginController controller;
	
	/**
	 * Constructs a new LoginView panel and sets up the controller for it.
	 * @param controller The controller that is to handle actions from the login panel
	 */
	public LoginView(LoginController controller) {
		this.controller = controller;
		
		initUI();
	}
	
	/**
	 * Sets the error message for the login panel.
	 * @param message The message to be displayed for the error
	 */
	public void setErrorMessage(String message) {
		this.errorMessage.setText(message);
	}
	
	/**
	 * Returns the current value of the error message on the login panel.
	 * @return The value of the error message
	 */
	public String getErrorMessage() {
		return this.errorMessage.getText();
	}
	
	/**
	 * Returns the login button of the login panel
	 * @return The login button of the login panel
	 */
	public JButton getLoginButton() {
		return this.loginButton;
	}
	
	/**
	 * Handles all actions performed on components with the login panel as the action listener.
	 * @param e The event to be handled
	 */
	public void actionPerformed(ActionEvent e) {
		this.controller.login(this.usernameField.getText(), new String(this.passwordField.getPassword()));
	}
	
	/**
	 * Closes the containing window of the login panel, if there is one.
	 */
	public void close() {
		Window ancestor = SwingUtilities.getWindowAncestor(this);
		if (ancestor != null) {
			ancestor.dispose();
		}
	}
	
	public void close(int i) {
		isNurse = true;
		Window ancestor = SwingUtilities.getWindowAncestor(this);
		if (ancestor != null) {
			ancestor.dispose();
		}
	}
	
	/**
	 * Initializes the UI components of the panel.
	 */
	private void initUI() {
		// Add some padding to the panel
		this.setBorder(new EmptyBorder(10, 30, 10, 30));
		
		this.setLayout(new MigLayout("", "[grow]"));
		this.add(usernameLabel, "align label");
		this.add(usernameField, "span 2, growx, wrap");
		
		this.add(passwordLabel, "align label");
		this.add(passwordField, "span 2, growx, wrap");
		
		this.add(errorMessage, "span 2, growx");
		this.add(loginButton, "right");
		
		this.errorMessage.setForeground(Color.RED);
		
		this.loginButton.addActionListener(this);
	}
}