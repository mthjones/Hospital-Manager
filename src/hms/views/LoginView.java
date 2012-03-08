package hms.views;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.*;

import hms.controllers.LoginController;

public class LoginView extends JPanel implements ActionListener {
	final private JLabel usernameLabel = new JLabel("Username:");
	final private JLabel passwordLabel = new JLabel("Password:");
	final private JTextField usernameField = new JTextField();
	final private JPasswordField passwordField = new JPasswordField();
	final private JButton loginButton = new JButton("Login");
	final private JLabel errorMessage = new JLabel("");
	
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
	
	/**
	 * Initializes the UI components of the panel.
	 */
	private void initUI() {
		// Add some padding to the panel
		this.setBorder(new EmptyBorder(20, 60, 20, 60));
		
		// Layout magic. Do not touch.
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(6, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(this.usernameLabel)
								.addComponent(this.passwordLabel))
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(this.usernameField, 170, 170, 170)
								.addComponent(this.passwordField, 170, 170, 170)))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(this.errorMessage)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(this.loginButton))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.usernameLabel)
						.addComponent(this.usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.passwordLabel)
						.addComponent(this.passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.loginButton)
						.addComponent(this.errorMessage)))
		);
		this.setLayout(layout);
		
		this.errorMessage.setForeground(Color.RED);
		
		this.loginButton.addActionListener(this);
	}
}