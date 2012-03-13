package hms.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;

public class NurseView {

	public JFrame frame;
	private JTextField txtName;
	private JTextField txtSocialInsuranceNumber;
	private JTextField txtPhoneNumber;
	private JTextField txtPagerNumber;
	private JTextField txtEmail;
	private JTextField txtHomeAddress;
	private JTextField textField;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;

	/**
	 * Create the application.
	 */
	public NurseView() {
		initialize(false);
	}
	
	public NurseView(String[] row) {
		initialize(true);
		
		if(row.length == 9) {
			if(row[0] != null) {
				txtName.setText(row[0]);
			}
			if(row[1] != null) {
				txtPhoneNumber.setText(row[1]);
			}
			if(row[2] != null) {
				txtPagerNumber.setText(row[2]);
			}
			if(row[3] != null) {
				txtEmail.setText(row[3]);
			}
			if(row[4] != null) {
				txtHomeAddress.setText(row[4]);
			}
			if(row[5] != null) {
				txtSocialInsuranceNumber.setText(row[5]);
			}
			if(row[7] != null) {
				if(row[7].equals("m") || row[7].equals("M")) {
					rdbtnMale.setSelected(true);
					rdbtnFemale.setSelected(false);
				}
				else if(row[7].equals("f") || row[7].equals("F")) {
					rdbtnMale.setSelected(false);
					rdbtnFemale.setSelected(true);
				}
			}
			if(row[8] != null) {
				textField.setText(row[8]);
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean edit) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 321);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Save");
		
		JButton button_1 = new JButton("Save and Close");
		
		JButton button_2 = new JButton("Close");
		
		JLabel lblName = new JLabel("Name");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JLabel lblSocialInsuranceNumber = new JLabel("Social Insurance Number");
		
		txtSocialInsuranceNumber = new JTextField();
		txtSocialInsuranceNumber.setColumns(10);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 11, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtName, 165, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtName, 426, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtSocialInsuranceNumber, 37, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtSocialInsuranceNumber, 165, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtSocialInsuranceNumber, 426, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblSocialInsuranceNumber, 40, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSocialInsuranceNumber, 11, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSocialInsuranceNumber, 160, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 11, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblName, 11, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblName, 52, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, button_2, 242, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button_2, 319, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button_2, 426, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 242, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button_1, 206, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, button, 242, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button, 92, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button, 200, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		frame.getContentPane().add(button);
		frame.getContentPane().add(button_1);
		frame.getContentPane().add(button_2);
		frame.getContentPane().add(lblName);
		frame.getContentPane().add(lblSocialInsuranceNumber);
		frame.getContentPane().add(txtSocialInsuranceNumber);
		frame.getContentPane().add(txtName);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnMale, 6, SpringLayout.SOUTH, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnMale, 0, SpringLayout.WEST, txtSocialInsuranceNumber);
		frame.getContentPane().add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnFemale, 6, SpringLayout.SOUTH, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnFemale, 25, SpringLayout.EAST, rdbtnMale);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnFemale, 0, SpringLayout.EAST, button_1);
		frame.getContentPane().add(rdbtnFemale);
		
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnFemale.setSelected(!rdbtnMale.isSelected());
			}
		});
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMale.setSelected(!rdbtnFemale.isSelected());
			}
		});
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		springLayout.putConstraint(SpringLayout.NORTH, lblPhoneNumber, 41, SpringLayout.SOUTH, lblSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.WEST, lblPhoneNumber, 0, SpringLayout.WEST, lblName);
		frame.getContentPane().add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPhoneNumber, 6, SpringLayout.SOUTH, rdbtnMale);
		springLayout.putConstraint(SpringLayout.WEST, txtPhoneNumber, 0, SpringLayout.WEST, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.EAST, txtPhoneNumber, -8, SpringLayout.EAST, frame.getContentPane());
		txtPhoneNumber.setColumns(10);
		frame.getContentPane().add(txtPhoneNumber);
		
		JLabel lblPagerNumber = new JLabel("Pager Number");
		springLayout.putConstraint(SpringLayout.EAST, lblPhoneNumber, 0, SpringLayout.EAST, lblPagerNumber);
		springLayout.putConstraint(SpringLayout.WEST, lblPagerNumber, 11, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblPagerNumber, 102, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblPagerNumber);
		
		txtPagerNumber = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPagerNumber, 8, SpringLayout.SOUTH, txtPhoneNumber);
		springLayout.putConstraint(SpringLayout.WEST, txtPagerNumber, 0, SpringLayout.WEST, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.EAST, txtPagerNumber, -9, SpringLayout.EAST, frame.getContentPane());
		txtPagerNumber.setColumns(10);
		frame.getContentPane().add(txtPagerNumber);
		
		JLabel lblEmail = new JLabel("Email");
		springLayout.putConstraint(SpringLayout.SOUTH, lblPagerNumber, -12, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 149, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, 0, SpringLayout.EAST, lblName);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtEmail, 6, SpringLayout.SOUTH, txtPagerNumber);
		springLayout.putConstraint(SpringLayout.WEST, txtEmail, 0, SpringLayout.WEST, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.EAST, txtEmail, -10, SpringLayout.EAST, frame.getContentPane());
		txtEmail.setColumns(10);
		frame.getContentPane().add(txtEmail);
		
		txtHomeAddress = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtHomeAddress, 6, SpringLayout.SOUTH, txtEmail);
		springLayout.putConstraint(SpringLayout.WEST, txtHomeAddress, 0, SpringLayout.WEST, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.EAST, txtHomeAddress, 0, SpringLayout.EAST, txtPagerNumber);
		txtHomeAddress.setColumns(10);
		frame.getContentPane().add(txtHomeAddress);
		
		JLabel lblHomeAdress = new JLabel("Home Address");
		springLayout.putConstraint(SpringLayout.NORTH, lblHomeAdress, 3, SpringLayout.NORTH, txtHomeAddress);
		springLayout.putConstraint(SpringLayout.WEST, lblHomeAdress, 0, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.EAST, lblHomeAdress, 0, SpringLayout.EAST, lblPhoneNumber);
		frame.getContentPane().add(lblHomeAdress);
		
		JLabel lblSalary = new JLabel("Salary");
		springLayout.putConstraint(SpringLayout.WEST, lblSalary, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSalary, 75, SpringLayout.WEST, lblName);
		frame.getContentPane().add(lblSalary);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblSalary, 3, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, txtHomeAddress);
		springLayout.putConstraint(SpringLayout.WEST, textField, 22, SpringLayout.WEST, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, txtPagerNumber);
		textField.setColumns(10);
		frame.getContentPane().add(textField);
		
		JLabel label = new JLabel("$");
		springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, txtSocialInsuranceNumber);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, lblSalary);
		springLayout.putConstraint(SpringLayout.EAST, label, -6, SpringLayout.WEST, textField);
		frame.getContentPane().add(label);
	}
}
