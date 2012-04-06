package hms.views;

import javax.swing.*;

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
		
		setTextComponentBorders();
	}
	
	public void reset() {
		
	}
	
	public boolean validateInformation() {
		return true;
	}
	
	public void loadInformation(Object objToLoad) {
		Nurse nurse = (Nurse)objToLoad;
	}
	
	public AbstractModel modelFromInformation() {
		return null;
	}
}