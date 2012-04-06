package hms.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import net.miginfocom.swing.MigLayout;

import hms.models.*;

public class NurseInfoPanel extends AbstractInfoPanel {
	private JTextField nameField = new JTextField();
	private JTextField sinField = new JTextField();
	private JTextField phoneField = new JTextField();
	private JTextField pagerField = new JTextField();
	private JTextField emailField = new JTextField();
	private JTextArea addressField = new JTextArea();
	private JTextField salaryField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JComboBox wardsDropdown = new JComboBox(Ward.getAllWards());
	
	private JRadioButton maleButton = new JRadioButton("Male");
	private JRadioButton femaleButton = new JRadioButton("Female");
	private ButtonGroup genderGroup = new ButtonGroup();
	
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel sinLabel = new JLabel("SIN#:");
	private JLabel phoneLabel = new JLabel("Phone:");
	private JLabel pagerLabel = new JLabel("Pager:");
	private JLabel emailLabel = new JLabel("Email:");
	private JLabel salaryLabel = new JLabel("Salary:");
	private JLabel genderLabel = new JLabel("Gender:");
	private JLabel wardLabel = new JLabel("Ward:");
	private JLabel passwordLabel = new JLabel("Password:");
	private JLabel addressLabel = new JLabel("Address:");
	
	private Integer id_number;
	
	public NurseInfoPanel() {
		initUI();
	}
	
	protected void initUI() {
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		
		this.setLayout(new MigLayout("fillx", "[label]rel[grow,fill][grow,fill]", "[]5[]"));
		
		addSeparator("General Information");
		
		this.add(nameLabel);
		this.add(nameField, "span 2, wrap");
		
		this.add(passwordLabel);
		this.add(passwordField, "span 2, wrap");
		
		this.add(sinLabel);
		this.add(sinField, "span 2, wrap");
		
		this.add(salaryLabel);
		this.add(salaryField, "span 2, wrap");
		
		this.add(genderLabel);
		this.add(maleButton);
		this.add(femaleButton, "wrap");
		
		this.add(wardLabel);
		this.add(wardsDropdown, "span 2, wrap");
		
		addSeparator("Contact Information");
		
		this.add(phoneLabel);
		this.add(phoneField, "span 2, wrap");
		
		this.add(pagerLabel);
		this.add(pagerField, "span 2, wrap");
		
		this.add(emailLabel);
		this.add(emailField, "span 2, wrap");
		
		this.add(addressLabel);
		addressField.setRows(3);
		this.add(addressField, "span 2, wrap");
		
		requiredComponents.add(nameLabel);
		requiredComponents.add(phoneLabel);
		requiredComponents.add(salaryLabel);
		requiredComponents.add(sinLabel);
		
		setTextComponentBorders();
	}
	
	public void reset() {
		for (Component comp : getComponents()) {
			if (comp instanceof JTextComponent) {
				JTextComponent textComp = (JTextComponent)comp;
				textComp.setText("");
				editableComponents.add((JTextComponent)comp);
			}
		}
		maleButton.setSelected(false);
		femaleButton.setSelected(false);
		wardsDropdown.setSelectedIndex(0);
	}
	
	public boolean validateInformation() {
		boolean validated = true;
		
		if (nameField.getText().equals("")) {
			nameLabel.setForeground(Color.RED);
			validated = false;
		}
		
		if (sinField.getText().equals("")) {
			sinLabel.setForeground(Color.RED);
			validated = false;
		}
		
		if (phoneField.getText().equals("")) {
			phoneLabel.setForeground(Color.RED);
			validated = false;
		}
		
		try {
			Integer.parseInt(salaryField.getText());
		} catch (NumberFormatException e) {
			salaryLabel.setForeground(Color.RED);
			validated = false;
		}
		
		return validated;
	}
	
	public void loadInformation(Object objToLoad) {
		Nurse nurse = (Nurse)objToLoad;
		nameField.setText(nurse.getName());
		phoneField.setText(nurse.getPhoneNumber());
		pagerField.setText(nurse.getPagerNumber());
		emailField.setText(nurse.getEmail());
		addressField.setText(nurse.getAddress());
		sinField.setText(nurse.getSIN());
		salaryField.setText(Integer.toString(nurse.getSalary()));
		passwordField.setText(nurse.getPassword());
		wardsDropdown.setSelectedItem(nurse.getWard());
		if (nurse.getGender().equals("M")) {
			maleButton.setSelected(true);
			femaleButton.setSelected(false);
		} else {
			maleButton.setSelected(false);
			femaleButton.setSelected(true);
		}
		id_number = nurse.getID();
	}
	
	public AbstractModel modelFromInformation() {
		return new Nurse(nameField.getText(),
						 phoneField.getText(),
						 pagerField.getText(),
						 emailField.getText(),
						 addressField.getText(),
						 sinField.getText(),
						 (id_number == null) ? Nurse.generateIDNumber() : id_number,
						 maleButton.isSelected() ? "M" : "F",
						 Integer.parseInt(salaryField.getText()),
						 wardsDropdown.getSelectedIndex(),
						 new String(passwordField.getPassword()));
	}
}