package hms.views;

import java.text.SimpleDateFormat;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import net.miginfocom.swing.MigLayout;

import hms.models.Patient;

public class PatientInfoPanel extends JPanel {
	final private JTextField nameField = new JTextField();
	final private JTextField phoneField = new JTextField();
	final private JTextField emailField = new JTextField();
	final private JTextField healthcareNumberField = new JTextField();
	final private JTextArea addressField = new JTextArea();
	final private SimpleDateFormat birthdateFormat = new SimpleDateFormat("dd/MM/yyyy");
	final private JFormattedTextField birthdateField = new JFormattedTextField(birthdateFormat);
	
	final private JTextField emergencyNameField = new JTextField();
	final private JTextField emergencyPhoneField = new JTextField();
	final private JTextField emergencyEmailField = new JTextField();
	
	final private JTextArea medicationsField = new JTextArea();
	final private JTextArea specialCareField = new JTextArea();
	final private JTextArea historyField = new JTextArea();
	final private JTextArea commentsField = new JTextArea();
	
	final private JRadioButton maleButton = new JRadioButton("Male");
	final private JRadioButton femaleButton = new JRadioButton("Female");
	final private ButtonGroup genderGroup = new ButtonGroup();
	
	final private JTextField wardField = new JTextField(10);
	final private JTextField roomField = new JTextField(4);
	final private JTextField bedField = new JTextField(4);
	
	public PatientInfoPanel() {
		initUI();
	}
	
	private void initUI() {
		final JLabel nameLabel = new JLabel("Name:");
		final JLabel phoneLabel = new JLabel("Phone:");
		final JLabel emailLabel = new JLabel("Email:");
		final JLabel healthcareNumberLabel = new JLabel("Healthcare Number:");
		final JLabel addressLabel = new JLabel("Address:");
		final JLabel birthdateLabel = new JLabel("Birthdate:");
		
		final JLabel emergencyNameLabel = new JLabel("Name:");
		final JLabel emergencyPhoneLabel = new JLabel("Phone:");
		final JLabel emergencyEmailLabel = new JLabel("Email:");
		
		final JLabel medicationsLabel = new JLabel("Medications:");
		final JLabel specialCareLabel = new JLabel("Special Care:");
		final JLabel historyLabel = new JLabel("History:");
		final JLabel commentsLabel = new JLabel("Comments:");
		
		final JLabel wardLabel = new JLabel("Ward:");
		final JLabel roomLabel = new JLabel("Room:");
		final JLabel bedLabel = new JLabel("Bed:");
		
		this.setLayout(new MigLayout("fillx", "[label]rel[grow,fill]"));
		
		addSeparator("General Information");
		
		this.add(nameLabel);
		this.add(nameField, "wrap");
		
		this.add(phoneLabel);
		this.add(phoneField, "wrap");
		
		this.add(healthcareNumberLabel);
		this.add(healthcareNumberField, "wrap");
		
		this.add(birthdateLabel);
		this.add(birthdateField, "wrap");
		
		this.add(emailLabel);
		this.add(emailField, "wrap");
		
		this.add(addressLabel);
		this.addressField.setRows(3);
		this.add(addressField, "wrap para");
		
		addSeparator("Emergency Contact Information");
		
		this.add(emergencyNameLabel);
		this.add(emergencyNameField, "wrap");
		
		this.add(emergencyPhoneLabel);
		this.add(emergencyPhoneField, "wrap");
		
		this.add(emergencyEmailLabel);
		this.add(emergencyEmailField, "wrap para");
		
		addSeparator("Additional Information");
		
		this.add(medicationsLabel);
		this.medicationsField.setRows(3);
		this.add(medicationsField, "wrap");
		
		this.add(specialCareLabel);
		this.specialCareField.setRows(3);
		this.add(specialCareField, "wrap");
		
		this.add(historyLabel);
		this.historyField.setRows(3);
		this.add(historyField, "wrap");
		
		this.add(commentsLabel);
		this.commentsField.setRows(3);
		this.add(commentsField, "wrap para");
		
		addSeparator("Location");
		
		this.add(wardLabel);
		this.add(wardField, "wrap");
		this.add(roomLabel);
		this.add(roomField, "wrap");
		this.add(bedLabel);
		this.add(bedField, "wrap");
	}
	
	public void setEditable(boolean editable) {
		for (Component comp : getComponents()) {
			if (comp instanceof JTextComponent) {
				JTextComponent textComp = (JTextComponent)comp;
				textComp.setEditable(editable);
			}
		}
	}
	
	public void clearPatientInformation() {
		this.nameField.setText("");
		this.phoneField.setText("");
		this.emailField.setText("");
		this.healthcareNumberField.setText("");
		this.addressField.setText("");
		this.birthdateField.setText("");
		this.emergencyNameField.setText("");
		this.emergencyPhoneField.setText("");
		this.emergencyEmailField.setText("");
		this.medicationsField.setText("");
		this.specialCareField.setText("");
		this.historyField.setText("");
		this.commentsField.setText("");
		this.maleButton.setSelected(true);
		this.femaleButton.setSelected(false);
		this.wardField.setText("");
		this.roomField.setText("");
		this.bedField.setText("");
	}
	
	public void loadPatientInformation(Patient patient) {
		this.nameField.setText(patient.getName());
		this.phoneField.setText(patient.getPhoneNumber());
		this.emailField.setText(patient.getEmail());
		this.healthcareNumberField.setText(patient.getHealthcareNumber());
		this.addressField.setText(patient.getAddress());
		this.birthdateField.setText(birthdateFormat.format(patient.getBirthdate()));
		this.emergencyNameField.setText(patient.getEmergencyName());
		this.emergencyPhoneField.setText(patient.getEmergencyPhoneNumber());
		this.emergencyEmailField.setText(patient.getEmergencyEmail());
		this.medicationsField.setText(patient.getMedications());
		this.specialCareField.setText(patient.getSpecialCare());
		this.historyField.setText(patient.getHistory());
		this.commentsField.setText(patient.getComments());
		if (patient.getGender() == "M") {
			maleButton.setSelected(true);
			femaleButton.setSelected(false);
		} else {
			maleButton.setSelected(true);
			femaleButton.setSelected(false);
		}
		this.wardField.setText(patient.getWard().toString());
		this.roomField.setText(patient.getRoom().toString());
		this.bedField.setText(patient.getBed().toString());
	}
	
	private void addSeparator(String label) {
		this.add(new JLabel(label), "split, span, gaptop 10");
		this.add(new JSeparator(), "growx, wrap, gaptop 10");
	}
}