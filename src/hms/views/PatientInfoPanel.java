package hms.views;

import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

import net.miginfocom.swing.MigLayout;

import hms.util.Priority;
import hms.models.*;

public class PatientInfoPanel extends JPanel {
	final private JTextField nameField = new JTextField();
	final private JTextField phoneField = new JTextField();
	final private JTextField emailField = new JTextField();
	final private JTextField healthcareNumberField = new JTextField();
	final private JTextArea addressField = new JTextArea();
	final private SimpleDateFormat birthdateFormat = new SimpleDateFormat("MM/dd/yyyy");
	final private JFormattedTextField birthdateField = new JFormattedTextField(birthdateFormat);
	final private JComboBox priorityDropdown = new JComboBox();
	final private JCheckBox inHospitalCheckbox = new JCheckBox();
	
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
	
	final private JComboBox wardDropdown = new JComboBox();
	final private JComboBox roomDropdown = new JComboBox();
	final private JComboBox bedDropdown = new JComboBox();
	
	public PatientInfoPanel() {
		initUI();
	}
	
	/**
	 * Initializes the components for this panel.
	 */
	private void initUI() {
		final JLabel nameLabel = new JLabel("Name:");
		final JLabel phoneLabel = new JLabel("Phone:");
		final JLabel emailLabel = new JLabel("Email:");
		final JLabel healthcareNumberLabel = new JLabel("Health #:");
		final JLabel addressLabel = new JLabel("Address:");
		final JLabel birthdateLabel = new JLabel("Birthdate:");
		final JLabel genderLabel = new JLabel("Gender:");
		final JLabel priorityLabel = new JLabel("Priority:");
		final JLabel inHospitalLabel = new JLabel("In Hospital:");
		
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
		
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		
		wardDropdown.setModel(new DefaultComboBoxModel(Ward.getWardNames()));
		
		ActionListener locationListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ActionEvent finalEvent = e;
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						if (finalEvent.getActionCommand().equals("UpdateRooms")) {
							roomDropdown.setModel(new DefaultComboBoxModel(Room.getRoomNumbers(wardDropdown.getSelectedIndex())));
						}
						bedDropdown.setModel(new DefaultComboBoxModel(Bed.getBedNumbers((Integer)roomDropdown.getSelectedItem())));
					}
				});
			}
		};
		
		wardDropdown.setActionCommand("UpdateRooms");
		wardDropdown.addActionListener(locationListener);
		roomDropdown.addActionListener(locationListener);
		
		priorityDropdown.addItem(Priority.HIGH);
		priorityDropdown.addItem(Priority.MEDIUM);
		priorityDropdown.addItem(Priority.LOW);
		
		this.setLayout(new MigLayout("fillx", "[label]rel[grow,fill][grow,fill]", "[]5[]"));
		
		addSeparator("General Information");
		
		this.add(nameLabel);
		this.add(nameField, "span 2, wrap");
		
		this.add(phoneLabel);
		this.add(phoneField, "span 2, wrap");
		
		this.add(healthcareNumberLabel);
		this.add(healthcareNumberField, "span 2, wrap");
		
		this.add(birthdateLabel);
		this.add(birthdateField, "span 2, wrap");
		
		this.add(emailLabel);
		this.add(emailField, "span 2, wrap");
		
		this.add(addressLabel);
		this.addressField.setRows(3);
		this.add(addressField, "span 2, wrap");
		
		this.add(genderLabel);
		this.add(maleButton);
		this.add(femaleButton, "wrap");
		
		this.add(priorityLabel);
		this.add(priorityDropdown, "span 2, wrap");
		
		this.add(inHospitalLabel);
		this.add(inHospitalCheckbox, "span 2, wrap para");
		
		addSeparator("Emergency Contact Information");
		
		this.add(emergencyNameLabel);
		this.add(emergencyNameField, "span 2, wrap");
		
		this.add(emergencyPhoneLabel);
		this.add(emergencyPhoneField, "span 2, wrap");
		
		this.add(emergencyEmailLabel);
		this.add(emergencyEmailField, "span 2, wrap para");
		
		addSeparator("Additional Information");
		
		this.add(medicationsLabel);
		this.medicationsField.setRows(3);
		this.add(medicationsField, "span 2, wrap");
		
		this.add(specialCareLabel);
		this.specialCareField.setRows(3);
		this.add(specialCareField, "span 2, wrap");
		
		this.add(historyLabel);
		this.historyField.setRows(3);
		this.add(historyField, "span 2, wrap");
		
		this.add(commentsLabel);
		this.commentsField.setRows(3);
		this.add(commentsField, "span 2, wrap para");
		
		addSeparator("Location");
		
		this.add(wardLabel);
		this.add(wardDropdown, "span 2, wrap");
		this.add(roomLabel);
		this.add(roomDropdown, "span 2, wrap");
		this.add(bedLabel);
		this.add(bedDropdown, "span 2, wrap");
		
		setTextComponentBorders();
	}
	
	/**
	 * Sets all text component borders to the same style so we have a more unified look
	 * across JTextFields and JTextAreas.
	 */
	private void setTextComponentBorders() {
		for (Component comp : getComponents()) {
			if (comp instanceof JTextComponent) {
				JTextComponent textComp = (JTextComponent)comp;
				textComp.setBorder(BorderFactory.createEtchedBorder());
			}
		}
	}
	
	/**
	 * Sets whether the text components on this panel are editable.
	 * @param editable A boolean value to set whether the text components should be editable.
	 */
	public void setEditable(boolean editable) {
		for (Component comp : getComponents()) {
			if (comp instanceof JTextComponent) {
				JTextComponent textComp = (JTextComponent)comp;
				textComp.setEditable(editable);
			}
		}
		maleButton.setEnabled(editable);
		femaleButton.setEnabled(editable);
		priorityDropdown.setEnabled(editable);
		inHospitalCheckbox.setEnabled(editable);
	}
	
	/**
	 * Clears all of the patient information from the panel.
	 */
	public void clearPatientInformation() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				nameField.setText("");
				phoneField.setText("");
				emailField.setText("");
				healthcareNumberField.setText("");
				addressField.setText("");
				birthdateField.setText("");
				emergencyNameField.setText("");
				emergencyPhoneField.setText("");
				emergencyEmailField.setText("");
				medicationsField.setText("");
				specialCareField.setText("");
				historyField.setText("");
				commentsField.setText("");
				maleButton.setSelected(false);
				femaleButton.setSelected(false);
				wardDropdown.setSelectedItem("Hospital");
				roomDropdown.removeAllItems();
				bedDropdown.removeAllItems();
				priorityDropdown.setSelectedItem(Priority.HIGH);
				inHospitalCheckbox.setSelected(false);
			}
		});
	}
	
	/**
	 * Loads the patient information into the form from the given patient.
	 * @param patient The patient to take the information from.
	 */
	public void loadPatientInformation(Patient patient) {
		final Patient finalPatient = patient;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				nameField.setText(finalPatient.getName());
				phoneField.setText(finalPatient.getPhoneNumber());
				emailField.setText(finalPatient.getEmail());
				healthcareNumberField.setText(finalPatient.getHealthcareNumber());
				addressField.setText(finalPatient.getAddress());
				birthdateField.setText(birthdateFormat.format(finalPatient.getBirthdate()));
				emergencyNameField.setText(finalPatient.getEmergencyName());
				emergencyPhoneField.setText(finalPatient.getEmergencyPhoneNumber());
				emergencyEmailField.setText(finalPatient.getEmergencyEmail());
				medicationsField.setText(finalPatient.getMedications());
				specialCareField.setText(finalPatient.getSpecialCare());
				historyField.setText(finalPatient.getHistory());
				commentsField.setText(finalPatient.getComments());
				if (finalPatient.getGender().equals("M")) {
					maleButton.setSelected(true);
					femaleButton.setSelected(false);
				} else {
					maleButton.setSelected(false);
					femaleButton.setSelected(true);
				}
				wardDropdown.setSelectedItem(Ward.getSingleWardName(finalPatient.getWard()));
				roomDropdown.setModel(new DefaultComboBoxModel(Room.getRoomNumbers(wardDropdown.getSelectedIndex())));
				roomDropdown.addItem(finalPatient.getRoom());
				roomDropdown.setSelectedItem(finalPatient.getRoom());
				bedDropdown.setModel(new DefaultComboBoxModel(Bed.getBedNumbers((Integer)roomDropdown.getSelectedItem())));
				bedDropdown.addItem(finalPatient.getBed());
				bedDropdown.setSelectedItem(finalPatient.getBed());
				priorityDropdown.setSelectedItem(finalPatient.getPriority());
				inHospitalCheckbox.setSelected(finalPatient.getInHospital());
			}
		});
	}
	
	/**
	 * Creates a new Patient object from the information present on the panel.
	 * @return A Patient object corresponding to the information on the panel.
	 */
	public Patient patientFromInformation() throws ParseException {
		return new Patient(healthcareNumberField.getText(),
						   nameField.getText(),
						   phoneField.getText(),
						   emailField.getText(),
						   maleButton.isSelected() ? "M" : "F",
						   "",
						   addressField.getText(),
						   birthdateFormat.parse(birthdateField.getText()),
						   medicationsField.getText(),
						   specialCareField.getText(),
						   historyField.getText(),
						   commentsField.getText(),
						   emergencyNameField.getText(),
						   emergencyPhoneField.getText(),
						   emergencyEmailField.getText(),
						   inHospitalCheckbox.isSelected(),
						   wardDropdown.getSelectedIndex(),
						   (Integer)roomDropdown.getSelectedItem(),
						   (Integer)bedDropdown.getSelectedItem(),
						   (Priority)priorityDropdown.getSelectedItem());
	}
	
	/**
	 * Creates a form separator.
	 * @param label The label to use on the separator.
	 */
	private void addSeparator(String message) {
		JLabel label = new JLabel(message);
		Font font = label.getFont();
		label.setFont(font.deriveFont(font.getStyle() ^ Font.BOLD));
		this.add(label, "split, span, gapbottom 10");
		this.add(new JSeparator(), "growx, wrap, gapbottom 10");
	}
}