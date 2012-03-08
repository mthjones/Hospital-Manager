package hms.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class UserView {

	private JFrame frmUser;
	private JTextField textField;
	private JPasswordField passwordField;	


	/**
	 * Create the application.
	 */
	public UserView() {
		initialize();
		centreWindow(frmUser);
	}
	
	public static void centreWindow(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUser = new JFrame();
		frmUser.setTitle("User");
		frmUser.setBounds(100, 100, 384, 191);
		frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Save");
		
		JButton button_1 = new JButton("Save and Close");
		
		JButton button_2 = new JButton("Close");
		
		JLabel lblUsername = new JLabel("Username");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		passwordField = new JPasswordField();
		
		JLabel lblWard = new JLabel("Ward");
		
		JComboBox wardComboBox = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(frmUser.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(45)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblWard, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(wardComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblUsername)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(126, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(wardComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWard))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addComponent(button_1)
						.addComponent(button_2))
					.addContainerGap(236, Short.MAX_VALUE))
		);
		frmUser.getContentPane().setLayout(groupLayout);
	}
}
