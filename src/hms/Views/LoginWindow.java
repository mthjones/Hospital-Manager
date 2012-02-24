package hms.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;

import hms.models.User;
import hms.Views.MainView;

public class LoginWindow extends JFrame implements ActionListener {
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	private JTextField usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel invalidLoginText = new JLabel("Invalid Login");
	private JButton loginButton = new JButton("Login");
	
	public LoginWindow() {
		initUI();
	}
	
	public final void initUI() {
		JPanel panel = new JPanel();
		// Add some padding to the window
		panel.setBorder(new EmptyBorder(20, 60, 20, 60));
		
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
								.addComponent(usernameLabel)
								.addComponent(passwordLabel))
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(usernameField, 170, 170, 170)
								.addComponent(passwordField, 170, 170, 170)))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(invalidLoginText)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(loginButton))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameLabel)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton)
						.addComponent(invalidLoginText)))
		);
		
		panel.setLayout(layout);
		this.add(panel);
		
		this.loginButton.setActionCommand("login");
		this.loginButton.addActionListener(this);
		
		this.invalidLoginText.setForeground(Color.RED);
		this.invalidLoginText.setVisible(false);
		
		this.pack();						// Compress the contents of the window
		this.setLocationRelativeTo(null);	// Center the window on the screen
		this.setResizable(false);
		this.setTitle("Login");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
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