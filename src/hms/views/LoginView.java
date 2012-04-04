package hms.views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import net.miginfocom.swing.MigLayout;

import hms.controllers.LoginController;

public class LoginView implements ActionListener {
	final private JDialog dialog;
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
	public LoginView(JFrame parent, LoginController controller) {
		this.controller = controller;
		dialog = new JDialog(parent, "Login", Dialog.ModalityType.APPLICATION_MODAL);
		dialog.getRootPane().setDefaultButton(this.loginButton);
		
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dialog.dispose();
				System.exit(0);
			}
		});
		
		initUI();
		
		dialog.setResizable(false);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		
		controller.setView(this);
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
	 * Returns the dialog that the login view uses.
	 * @return The dialog that the login view uses
	 */
	public JDialog getDialog() {
		return this.dialog;
	}
	
	/**
	 * Handles all actions performed on components with the login panel as the action listener.
	 * @param e The event to be handled
	 */
	public void actionPerformed(ActionEvent e) {
		this.controller.login(this.usernameField.getText(), new String(this.passwordField.getPassword()));
	}
	
	/**
	 * Shows the dialog.
	 */
	public void show() {
		this.dialog.setVisible(true);
	}
	
	/**
	 * Closes the containing window of the login panel, if there is one.
	 */
	public void close() {
		this.dialog.dispose();
	}
	
	public void close(int i) {
		isNurse = true;
		this.dialog.dispose();
	}
	
	/**
	 * Initializes the UI components of the panel.
	 */
	private void initUI() {
		Container contentPane = this.dialog.getContentPane();
		
		// Add some padding to the panel
		this.dialog.getRootPane().setBorder(new EmptyBorder(10, 30, 10, 30));
		
		contentPane.setLayout(new MigLayout("", "[grow]"));
		contentPane.add(usernameLabel, "align label");
		contentPane.add(usernameField, "span 2, growx, wrap");
		
		contentPane.add(passwordLabel, "align label");
		contentPane.add(passwordField, "span 2, growx, wrap");
		
		contentPane.add(errorMessage, "span 2, growx");
		contentPane.add(loginButton, "right");
		
		this.errorMessage.setForeground(Color.RED);
		
		this.loginButton.addActionListener(this);
	}
}