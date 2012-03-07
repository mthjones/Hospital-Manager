package hms.Views;

import javax.swing.JFrame;
import hms.models.*;
import hms.Managers.LoginManager;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginView {

	public JFrame frmLogin;
	private JTextField textFieldUsername;
	private LoginManager loginManager;
	private JLabel lblPassword;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();

		loginManager = new LoginManager();
		centreWindow(frmLogin);
	}

	public static void centreWindow(JFrame frmLogin) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frmLogin.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frmLogin.getHeight()) / 2);
		frmLogin.setLocation(x, y);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon.png")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Username");

		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		
		final JLabel lblInvalidLogin = new JLabel("Invalid Login");
		lblInvalidLogin.setVisible(false);
		lblInvalidLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidLogin.setForeground(Color.RED);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isValidUser = false;//TODO
				try{
					//User.create(textFieldUsername.getText(), passwordField.getText()).save();//uncomment for test with clients
					isValidUser = User.authenticate(textFieldUsername.getText(), passwordField.getText());
				}catch(SQLException e){
					isValidUser = false;
					lblPassword.setText("Conect");
					lblPassword.setForeground(Color.red);

				}
				if(isValidUser){
					JFrame jFrame = frmLogin;
					loginManager.Login(jFrame);
				}else{
					lblPassword.setText("Incorrect");
					lblPassword.setForeground(Color.red);
				}
				JFrame jFrame = frmLogin;
				loginManager.Login(jFrame, lblInvalidLogin, textFieldUsername.getText(), passwordField.getPassword());
			}
		});

		lblPassword = new JLabel("Password");

		passwordField = new JPasswordField();
		
		GroupLayout groupLayout = new GroupLayout(frmLogin.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(187)
										.addComponent(btnLogin))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(81)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																.addComponent(passwordField)
																.addComponent(textFieldUsername, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
																.addContainerGap(101, Short.MAX_VALUE))
				);
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(passwordField)
							.addComponent(textFieldUsername, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(btnLogin)))
					.addContainerGap(115, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(172, Short.MAX_VALUE)
					.addComponent(lblInvalidLogin, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(169))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(82)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPassword)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(btnLogin)
										.addContainerGap(99, Short.MAX_VALUE))
				);
					.addGap(82)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLogin)
					.addGap(18)
					.addComponent(lblInvalidLogin)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		frmLogin.getContentPane().setLayout(groupLayout);
	}
}