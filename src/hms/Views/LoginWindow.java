package hms.Views;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import hms.models.User;
import hms.Views.MainView;
import hms.db.Database;

public class LoginWindow extends JFrame implements ActionListener {
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel invalidLoginText;
	private JButton loginButton;
	
	public LoginWindow() {
		initUI();
	}
	
	/**
	 * Initializes the UI
	 */
	public final void initUI() {
		JPanel panel = new JPanel();
		// Add some padding to the window
		panel.setBorder(new EmptyBorder(20, 60, 20, 60));
		
		this.usernameLabel = new JLabel("Username");
		this.passwordLabel = new JLabel("Password");
		this.usernameField = new JTextField();
		this.passwordField = new JPasswordField();
		this.invalidLoginText = new JLabel("Invalid Login");
		this.loginButton = new JButton("Login");
		
		// Layout magic. Do not touch.
		GroupLayout layout = new GroupLayout(panel);
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
							.addComponent(this.invalidLoginText)
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
						.addComponent(this.invalidLoginText)))
		);
		
		panel.setLayout(layout);
		this.add(panel);
				
		this.loginButton.setActionCommand("login");
		this.loginButton.addActionListener(this);
		
		// Set the default window button to the login button so you can press enter to log in
		this.getRootPane().setDefaultButton(this.loginButton);
		
		this.invalidLoginText.setForeground(Color.RED);
		this.invalidLoginText.setVisible(false);
		
		this.pack();						// Compress the contents of the window
		this.setLocationRelativeTo(null);	// Center the window on the screen
		this.setResizable(false);
		this.setTitle("Login");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					Database.getInstance().closeConnection();
				} catch (SQLException sqle) {
					// If we can't close the connection, we probably didn't have to
				}
				System.exit(0);
			}
		});
	}
	
	/**
	 * Allows the class to act as an action event handler. Takes an ActionEvent and maps it to the correct action.
	 * @param event The event that is performed
	 */
	public void actionPerformed(ActionEvent event) {
		if ("login".equals(event.getActionCommand())) {
			try {
				if (User.authenticate(this.usernameField.getText(), new String(this.passwordField.getPassword()))) {
					this.invalidLoginText.setVisible(false);
					MainView mv = new MainView();
					mv.frmMain.setVisible(true);
					this.dispose();
				} else {
					this.invalidLoginText.setVisible(true);
				}
			} catch (SQLException sqle) {
				this.invalidLoginText.setVisible(true);
				this.invalidLoginText.setText("Database Error");
			}
		}
	}
}