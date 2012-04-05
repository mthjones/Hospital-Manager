package hms.views;

import hms.controllers.NurseController;
import hms.controllers.PatientManager;
import hms.controllers.UserController;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.util.Vector;

import hms.models.*;
import hms.util.Encryptor;

public class MainView {

	public JFrame frmMain;
	private JTable tablePatients;
	private JTable tableNurses;
	private PatientManager patientManager;
	private PatientTableModel patientTableModel;
	private NurseTableModel nurseTableModel = new NurseTableModel();
	private UserController userController;
	private NurseController nurseController;
	private JLabel wardNumber;
	private JLabel email;
	private JLabel phoneNumber;
	private JLabel address;
	private JLabel room;
	private JLabel bed;
	private JLabel medication;
	private JLabel specialCare;
	private JLabel history;
	private JLabel comments;
	private JLabel emergencyName;
	private JLabel emergencyPhoneNumber;
	private JLabel emergencyEmail;
	private String searchTerm;
	private JButton btnSearch;
	private JTextField txtSearchBar;
	private boolean isNurse;

	/**
	 * Create the application.
	 */
	public MainView(boolean isNurse) {
		this.isNurse = isNurse;
		initialize();

		address.setText("");
		phoneNumber.setText("");
		email.setText("");
		wardNumber.setText("");
		room.setText("");
		bed.setText("");
		medication.setText("");
		specialCare.setText("");
		history.setText("");
		comments.setText("");
		emergencyName.setText("");
		emergencyPhoneNumber.setText("");
		emergencyEmail.setText("");

		maximizeWindow();
		patientManager = new PatientManager(patientTableModel);
		nurseController = new NurseController(nurseTableModel);
	}

	private void maximizeWindow() {
		frmMain.setExtendedState(frmMain.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
	
	private String[] getSelectedNurse() {
		if(tableNurses.getSelectedRowCount() != 1) return null;
		Object[][] content = nurseTableModel.getContent();
		Object[] selectedRow = content[tableNurses.getSelectedRow()];
		String[] strings = new String[selectedRow.length];
		for(int i = 0; i< strings.length; i++){
			strings[i] = selectedRow[i].toString();
		}
		
		return strings;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon.png")));
		frmMain.setTitle("Main");
		frmMain.setBounds(100, 100, 2000, 2000);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Hello");

		JLabel lblUsername = new JLabel("Username");

		patientTableModel = new PatientTableModel();
		
		nurseTableModel = new NurseTableModel();

		JButton deletePatientButton = new JButton("Delete Patient");

		JTabbedPane patientsUsersNursesTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		final JRadioButton viewAllRadioButton = new JRadioButton("View All");

		GroupLayout groupLayout = new GroupLayout(frmMain.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addComponent(patientsUsersNursesTabbedPane, GroupLayout.PREFERRED_SIZE, 1337, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(patientsUsersNursesTabbedPane, GroupLayout.PREFERRED_SIZE, 708, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		JPanel panel = new JPanel();
		patientsUsersNursesTabbedPane.addTab("Patients", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{636, 110, 3, 94, 100, 102, 103, 99, 32, 17, 23, 0};
		gbl_panel.rowHeights = new int[]{23, 14, 14, 14, 38, 0, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 282, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
						
								final JRadioButton inHospitalRadioButton = new JRadioButton("In Hospital");
								inHospitalRadioButton.addActionListener( new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(((JRadioButton)e.getSource()).isSelected())
										{
											patientTableModel.fireTableDataChanged("SELECT * FROM patient WHERE (in_hospital = 'Y' OR in_hospital = '" + Encryptor.encode("Y") + "')");
										} else {
											patientTableModel.fireTableDataChanged();
										}
									}
								});
								inHospitalRadioButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										viewAllRadioButton.setSelected(!inHospitalRadioButton.isSelected());
									}
								});
								GridBagConstraints gbc_inHospitalRadioButton = new GridBagConstraints();
								gbc_inHospitalRadioButton.anchor = GridBagConstraints.NORTH;
								gbc_inHospitalRadioButton.fill = GridBagConstraints.HORIZONTAL;
								gbc_inHospitalRadioButton.insets = new Insets(0, 0, 5, 5);
								gbc_inHospitalRadioButton.gridwidth = 2;
								gbc_inHospitalRadioButton.gridx = 1;
								gbc_inHospitalRadioButton.gridy = 0;
								panel.add(inHospitalRadioButton, gbc_inHospitalRadioButton);		
				
						viewAllRadioButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								inHospitalRadioButton.setSelected(!viewAllRadioButton.isSelected());
								if(((JRadioButton)e.getSource()).isSelected())
								{
									patientTableModel.fireTableDataChanged("SELECT * FROM patient");
								} else {
									patientTableModel.fireTableDataChanged();
								}
							}
						});
						GridBagConstraints gbc_viewAllRadioButton = new GridBagConstraints();
						gbc_viewAllRadioButton.anchor = GridBagConstraints.NORTHWEST;
						gbc_viewAllRadioButton.insets = new Insets(0, 0, 5, 5);
						gbc_viewAllRadioButton.gridx = 3;
						gbc_viewAllRadioButton.gridy = 0;
						panel.add(viewAllRadioButton, gbc_viewAllRadioButton);
		tablePatients = new JTable(patientTableModel);
		tablePatients.setAutoCreateRowSorter(true);
		JScrollPane jsp = new JScrollPane(tablePatients);
		GridBagConstraints gbc_jsp = new GridBagConstraints();
		gbc_jsp.fill = GridBagConstraints.BOTH;
		gbc_jsp.insets = new Insets(0, 0, 5, 5);
		gbc_jsp.gridheight = 16;
		gbc_jsp.gridx = 0;
		gbc_jsp.gridy = 1;
		panel.add(jsp, gbc_jsp);
		
				tablePatients.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						
						if(tablePatients.getSelectedRowCount() == 1){
							Object[][] content = patientTableModel.getContent();
							Object[] selectedRow = content[tablePatients.getSelectedRow()];
							String[] strings = new String[content[tablePatients.getSelectedRow()].length];
							for(int i = 0; i< strings.length; i++){
								strings[i] = content[tablePatients.getSelectedRow()][i].toString();
							}
							
							int healthCareNumber = Integer.parseInt(strings[0]);
							Patient patient = null;
							
							try {
								patient = patientManager.GetPatient(healthCareNumber);
							}
							catch (SQLException ex) {}
							String roomId = Integer.toString(patient.room_id);
							String bedId = Integer.toString(patient.bed_id);
							
							address.setText(patient.address);
							phoneNumber.setText(patient.phone_number);
							email.setText(patient.email);
							wardNumber.setText(patientManager.getPatientSingleWardName(patient.ward_id));
							room.setText(roomId);
							bed.setText(bedId);
							medication.setText(patient.medications);
							specialCare.setText(patient.special_care);
							history.setText(patient.history);
							comments.setText(patient.comments);
							emergencyName.setText(patient.emerg_name);
							emergencyPhoneNumber.setText(patient.emerg_phone_number);
							emergencyEmail.setText(patient.emerg_email);
						}
					}
				});
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.anchor = GridBagConstraints.NORTH;
		gbc_lblPhoneNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber.gridx = 1;
		gbc_lblPhoneNumber.gridy = 1;
		panel.add(lblPhoneNumber, gbc_lblPhoneNumber);
		
		phoneNumber = new JLabel("PhoneNumber");
		GridBagConstraints gbc_phoneNumber = new GridBagConstraints();
		gbc_phoneNumber.anchor = GridBagConstraints.NORTH;
		gbc_phoneNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumber.gridwidth = 3;
		gbc_phoneNumber.gridx = 4;
		gbc_phoneNumber.gridy = 1;
		panel.add(phoneNumber, gbc_phoneNumber);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.NORTH;
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 2;
		panel.add(lblEmail, gbc_lblEmail);
		
		email = new JLabel("Email");
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.anchor = GridBagConstraints.NORTH;
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.gridwidth = 2;
		gbc_email.gridx = 4;
		gbc_email.gridy = 2;
		panel.add(email, gbc_email);
		
		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.NORTH;
		gbc_lblAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 3;
		panel.add(lblAddress, gbc_lblAddress);
		
		address = new JLabel("Address");
		GridBagConstraints gbc_address = new GridBagConstraints();
		gbc_address.anchor = GridBagConstraints.NORTH;
		gbc_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_address.insets = new Insets(0, 0, 5, 5);
		gbc_address.gridwidth = 2;
		gbc_address.gridx = 4;
		gbc_address.gridy = 3;
		panel.add(address, gbc_address);
		
		JLabel lblWard = new JLabel("Ward");
		GridBagConstraints gbc_lblWard = new GridBagConstraints();
		gbc_lblWard.anchor = GridBagConstraints.NORTH;
		gbc_lblWard.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWard.insets = new Insets(0, 0, 5, 5);
		gbc_lblWard.gridx = 1;
		gbc_lblWard.gridy = 6;
		panel.add(lblWard, gbc_lblWard);
		
		wardNumber = new JLabel("WardNumber");
		GridBagConstraints gbc_wardNumber = new GridBagConstraints();
		gbc_wardNumber.anchor = GridBagConstraints.NORTH;
		gbc_wardNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_wardNumber.insets = new Insets(0, 0, 5, 5);
		gbc_wardNumber.gridwidth = 3;
		gbc_wardNumber.gridx = 4;
		gbc_wardNumber.gridy = 6;
		panel.add(wardNumber, gbc_wardNumber);
		
		JLabel lblRoom = new JLabel("Room");
		GridBagConstraints gbc_lblRoom = new GridBagConstraints();
		gbc_lblRoom.anchor = GridBagConstraints.NORTH;
		gbc_lblRoom.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRoom.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoom.gridx = 1;
		gbc_lblRoom.gridy = 7;
		panel.add(lblRoom, gbc_lblRoom);
		
		room = new JLabel("room");
		GridBagConstraints gbc_room = new GridBagConstraints();
		gbc_room.anchor = GridBagConstraints.NORTH;
		gbc_room.fill = GridBagConstraints.HORIZONTAL;
		gbc_room.insets = new Insets(0, 0, 5, 5);
		gbc_room.gridwidth = 2;
		gbc_room.gridx = 4;
		gbc_room.gridy = 7;
		panel.add(room, gbc_room);
		
		JLabel lblBed = new JLabel("Bed");
		GridBagConstraints gbc_lblBed = new GridBagConstraints();
		gbc_lblBed.anchor = GridBagConstraints.NORTH;
		gbc_lblBed.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBed.insets = new Insets(0, 0, 5, 5);
		gbc_lblBed.gridx = 1;
		gbc_lblBed.gridy = 8;
		panel.add(lblBed, gbc_lblBed);
		
		bed = new JLabel("bed");
		GridBagConstraints gbc_bed = new GridBagConstraints();
		gbc_bed.anchor = GridBagConstraints.NORTH;
		gbc_bed.fill = GridBagConstraints.HORIZONTAL;
		gbc_bed.insets = new Insets(0, 0, 5, 5);
		gbc_bed.gridwidth = 2;
		gbc_bed.gridx = 4;
		gbc_bed.gridy = 8;
		panel.add(bed, gbc_bed);
		
		JLabel lblMedication = new JLabel("Medication");
		GridBagConstraints gbc_lblMedication = new GridBagConstraints();
		gbc_lblMedication.anchor = GridBagConstraints.NORTH;
		gbc_lblMedication.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMedication.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedication.gridx = 1;
		gbc_lblMedication.gridy = 9;
		panel.add(lblMedication, gbc_lblMedication);
		
		medication = new JLabel("Medication");
		GridBagConstraints gbc_medication = new GridBagConstraints();
		gbc_medication.anchor = GridBagConstraints.NORTH;
		gbc_medication.fill = GridBagConstraints.HORIZONTAL;
		gbc_medication.insets = new Insets(0, 0, 5, 5);
		gbc_medication.gridwidth = 3;
		gbc_medication.gridx = 4;
		gbc_medication.gridy = 9;
		panel.add(medication, gbc_medication);
		
		JLabel lblSpecialCare = new JLabel("Special Care");
		GridBagConstraints gbc_lblSpecialCare = new GridBagConstraints();
		gbc_lblSpecialCare.anchor = GridBagConstraints.NORTH;
		gbc_lblSpecialCare.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSpecialCare.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecialCare.gridx = 1;
		gbc_lblSpecialCare.gridy = 10;
		panel.add(lblSpecialCare, gbc_lblSpecialCare);
		
		specialCare = new JLabel("Special Care");
		GridBagConstraints gbc_specialCare = new GridBagConstraints();
		gbc_specialCare.anchor = GridBagConstraints.NORTH;
		gbc_specialCare.fill = GridBagConstraints.HORIZONTAL;
		gbc_specialCare.insets = new Insets(0, 0, 5, 5);
		gbc_specialCare.gridwidth = 3;
		gbc_specialCare.gridx = 4;
		gbc_specialCare.gridy = 10;
		panel.add(specialCare, gbc_specialCare);
		
		JLabel lblHistory = new JLabel("History");
		GridBagConstraints gbc_lblHistory = new GridBagConstraints();
		gbc_lblHistory.anchor = GridBagConstraints.NORTH;
		gbc_lblHistory.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHistory.insets = new Insets(0, 0, 5, 5);
		gbc_lblHistory.gridx = 1;
		gbc_lblHistory.gridy = 11;
		panel.add(lblHistory, gbc_lblHistory);
		
		history = new JLabel("History");
		GridBagConstraints gbc_history = new GridBagConstraints();
		gbc_history.anchor = GridBagConstraints.NORTH;
		gbc_history.fill = GridBagConstraints.HORIZONTAL;
		gbc_history.insets = new Insets(0, 0, 5, 5);
		gbc_history.gridwidth = 4;
		gbc_history.gridx = 4;
		gbc_history.gridy = 11;
		panel.add(history, gbc_history);
		
		JLabel lblComments = new JLabel("Comments");
		GridBagConstraints gbc_lblComments = new GridBagConstraints();
		gbc_lblComments.anchor = GridBagConstraints.NORTH;
		gbc_lblComments.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblComments.insets = new Insets(0, 0, 5, 5);
		gbc_lblComments.gridx = 1;
		gbc_lblComments.gridy = 12;
		panel.add(lblComments, gbc_lblComments);
		
		comments = new JLabel("Comments");
		GridBagConstraints gbc_comments = new GridBagConstraints();
		gbc_comments.anchor = GridBagConstraints.NORTH;
		gbc_comments.fill = GridBagConstraints.HORIZONTAL;
		gbc_comments.insets = new Insets(0, 0, 5, 5);
		gbc_comments.gridx = 4;
		gbc_comments.gridy = 12;
		panel.add(comments, gbc_comments);
		
		JLabel lblEmergencyContact = new JLabel("Emergency Contact");
		GridBagConstraints gbc_lblEmergencyContact = new GridBagConstraints();
		gbc_lblEmergencyContact.anchor = GridBagConstraints.NORTH;
		gbc_lblEmergencyContact.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmergencyContact.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmergencyContact.gridwidth = 4;
		gbc_lblEmergencyContact.gridx = 1;
		gbc_lblEmergencyContact.gridy = 13;
		panel.add(lblEmergencyContact, gbc_lblEmergencyContact);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.NORTH;
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 14;
		panel.add(lblName, gbc_lblName);
		
		emergencyName = new JLabel("Name");
		GridBagConstraints gbc_emergencyName = new GridBagConstraints();
		gbc_emergencyName.anchor = GridBagConstraints.NORTH;
		gbc_emergencyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_emergencyName.insets = new Insets(0, 0, 5, 5);
		gbc_emergencyName.gridwidth = 4;
		gbc_emergencyName.gridx = 2;
		gbc_emergencyName.gridy = 14;
		panel.add(emergencyName, gbc_emergencyName);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number");
		GridBagConstraints gbc_lblPhoneNumber_1 = new GridBagConstraints();
		gbc_lblPhoneNumber_1.anchor = GridBagConstraints.NORTH;
		gbc_lblPhoneNumber_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhoneNumber_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber_1.gridx = 1;
		gbc_lblPhoneNumber_1.gridy = 15;
		panel.add(lblPhoneNumber_1, gbc_lblPhoneNumber_1);
		
		emergencyPhoneNumber = new JLabel("EmergencyPhoneNumber");
		GridBagConstraints gbc_emergencyPhoneNumber = new GridBagConstraints();
		gbc_emergencyPhoneNumber.anchor = GridBagConstraints.NORTH;
		gbc_emergencyPhoneNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_emergencyPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_emergencyPhoneNumber.gridwidth = 4;
		gbc_emergencyPhoneNumber.gridx = 2;
		gbc_emergencyPhoneNumber.gridy = 15;
		panel.add(emergencyPhoneNumber, gbc_emergencyPhoneNumber);
		
		JLabel lblEmail_1 = new JLabel("Email");
		GridBagConstraints gbc_lblEmail_1 = new GridBagConstraints();
		gbc_lblEmail_1.anchor = GridBagConstraints.NORTH;
		gbc_lblEmail_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_1.gridx = 1;
		gbc_lblEmail_1.gridy = 16;
		panel.add(lblEmail_1, gbc_lblEmail_1);
				
				txtSearchBar = new JTextField("search");
				txtSearchBar.setForeground(Color.GRAY);
				txtSearchBar.addKeyListener(new KeyListener() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						//do nothing
					}
					@Override
					public void keyReleased(KeyEvent arg0) {
						//do nothing
					}
					@Override
					public void keyTyped(KeyEvent arg0) {
						btnSearch.setText("search");
					}
				});
				txtSearchBar.addFocusListener(new FocusListener () {

					@Override
					public void focusGained(FocusEvent arg0) {
						if(txtSearchBar.getText().equals("search"))
						{
							txtSearchBar.setForeground(Color.BLACK);
							txtSearchBar.setText("");
						}
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						if(txtSearchBar.getText().isEmpty())
						{
							txtSearchBar.setForeground(Color.GRAY);
							txtSearchBar.setText("search");
						}
					}
					
				});
				
				emergencyEmail = new JLabel("EmergencyEmail");
				GridBagConstraints gbc_emergencyEmail = new GridBagConstraints();
				gbc_emergencyEmail.anchor = GridBagConstraints.NORTH;
				gbc_emergencyEmail.fill = GridBagConstraints.HORIZONTAL;
				gbc_emergencyEmail.insets = new Insets(0, 0, 5, 5);
				gbc_emergencyEmail.gridwidth = 4;
				gbc_emergencyEmail.gridx = 2;
				gbc_emergencyEmail.gridy = 16;
				panel.add(emergencyEmail, gbc_emergencyEmail);
				GridBagConstraints gbc_txtSearchBar = new GridBagConstraints();
				gbc_txtSearchBar.anchor = GridBagConstraints.NORTH;
				gbc_txtSearchBar.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtSearchBar.insets = new Insets(0, 0, 0, 5);
				gbc_txtSearchBar.gridwidth = 2;
				gbc_txtSearchBar.gridx = 1;
				gbc_txtSearchBar.gridy = 17;
				panel.add(txtSearchBar, gbc_txtSearchBar);
						
								JButton btnCreatePatient = new JButton("Create Patient");
								btnCreatePatient.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										patientManager.CreatePatient(frmMain);
									}
								});
								
								btnSearch = new JButton("search");
								btnSearch.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										searchTerm = txtSearchBar.getText();
										if (btnSearch.getText().equals("clear")) {
											patientTableModel.fireTableDataChanged();
											btnSearch.setText("search");
											txtSearchBar.setForeground(Color.GRAY);
											txtSearchBar.setText("search");
										} else {
											patientTableModel.fireTableDataChanged("SELECT * FROM patient WHERE name = '" + Encryptor.encode(searchTerm) + "'");
											btnSearch.setText("clear");
										}
									}
								});
								GridBagConstraints gbc_btnSearch = new GridBagConstraints();
								gbc_btnSearch.anchor = GridBagConstraints.NORTH;
								gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
								gbc_btnSearch.gridx = 3;
								gbc_btnSearch.gridy = 17;
								panel.add(btnSearch, gbc_btnSearch);
								GridBagConstraints gbc_btnCreatePatient = new GridBagConstraints();
								gbc_btnCreatePatient.anchor = GridBagConstraints.NORTH;
								gbc_btnCreatePatient.insets = new Insets(0, 0, 0, 5);
								gbc_btnCreatePatient.gridx = 4;
								gbc_btnCreatePatient.gridy = 17;
								panel.add(btnCreatePatient, gbc_btnCreatePatient);
				
				
						JButton btnEditPatient = new JButton("Edit Patient");
						btnEditPatient.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if(tablePatients.getSelectedRowCount() == 1){
									Object[][] content = patientTableModel.getContent();
									Object[] selectedRow = content[tablePatients.getSelectedRow()];
									String[] strs = new String[content[tablePatients.getSelectedRow()].length];
									for(int i = 0; i< strs.length; i++){
										strs[i] = content[tablePatients.getSelectedRow()][i].toString();
									}
									patientManager.EditPatient(frmMain, strs);
								}
							}
						});
						GridBagConstraints gbc_btnEditPatient = new GridBagConstraints();
						gbc_btnEditPatient.anchor = GridBagConstraints.NORTH;
						gbc_btnEditPatient.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnEditPatient.insets = new Insets(0, 0, 0, 5);
						gbc_btnEditPatient.gridx = 5;
						gbc_btnEditPatient.gridy = 17;
						panel.add(btnEditPatient, gbc_btnEditPatient);
				
						JButton btnDeletePatient = new JButton("Delete Patient");
						btnDeletePatient.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(tablePatients.getSelectedRowCount() == 1){
									Object[][] content = patientTableModel.getContent();
									String healthcareNumber = content[tablePatients.getSelectedRow()][0].toString();
									patientManager.deletePatient(frmMain, healthcareNumber);
								}
							}
						});
						GridBagConstraints gbc_btnDeletePatient = new GridBagConstraints();
						gbc_btnDeletePatient.fill = GridBagConstraints.BOTH;
						gbc_btnDeletePatient.insets = new Insets(0, 0, 0, 5);
						gbc_btnDeletePatient.gridx = 6;
						gbc_btnDeletePatient.gridy = 17;
						panel.add(btnDeletePatient, gbc_btnDeletePatient);
						
								JButton btnRefresh = new JButton("Refresh");
								btnRefresh.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										patientTableModel.fireTableDataChanged();
									}
								});
								GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
								gbc_btnRefresh.insets = new Insets(0, 0, 0, 5);
								gbc_btnRefresh.anchor = GridBagConstraints.NORTH;
								gbc_btnRefresh.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnRefresh.gridx = 7;
								gbc_btnRefresh.gridy = 17;
								panel.add(btnRefresh, gbc_btnRefresh);

		JPanel usersPanel = new JPanel();
		if( ! isNurse) patientsUsersNursesTabbedPane.addTab("Users", null, usersPanel, null);

		JScrollPane usersScrollPane = new JScrollPane((Component) null);

		JButton buttonRefreshUsers = new JButton("Refresh");

		JButton btnDeleteUser = new JButton("Delete User");

		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userController.EditUser(frmMain);
			}
		});

		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userController.CreateUser(frmMain);
			}
		});
		
		GroupLayout gl_usersPanel = new GroupLayout(usersPanel);
		gl_usersPanel.setHorizontalGroup(
			gl_usersPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_usersPanel.createSequentialGroup()
					.addContainerGap(879, Short.MAX_VALUE)
					.addComponent(btnCreateUser, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnEditUser, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnDeleteUser, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(buttonRefreshUsers, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
				.addGroup(Alignment.LEADING, gl_usersPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(usersScrollPane, GroupLayout.PREFERRED_SIZE, 1303, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_usersPanel.setVerticalGroup(
			gl_usersPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_usersPanel.createSequentialGroup()
					.addContainerGap(14, Short.MAX_VALUE)
					.addComponent(usersScrollPane, GroupLayout.PREFERRED_SIZE, 626, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_usersPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCreateUser)
						.addComponent(btnEditUser)
						.addComponent(btnDeleteUser)
						.addComponent(buttonRefreshUsers))
					.addContainerGap())
		);
		usersPanel.setLayout(gl_usersPanel);

		JPanel panel_2 = new JPanel();
		if( !isNurse) patientsUsersNursesTabbedPane.addTab("Nurses", null, panel_2, null);
		tableNurses = new JTable(nurseTableModel);
		JScrollPane nursesScrollPane = new JScrollPane(tableNurses);

		JButton btnCreateNurse = new JButton("Create Nurse");
		btnCreateNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nurseController.CreateNurse();
			}
		});

		JButton btnEditNurse = new JButton("Edit Nurse");
		btnEditNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] strings = getSelectedNurse();
				nurseController.EditNurse(strings);
			}
		});

		JButton btnDeleteNurse = new JButton("Delete Nurse");
		btnDeleteNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] strings = getSelectedNurse();
				int idNumber = Integer.parseInt(strings[6]);
				
				nurseController.DeleteNurse(idNumber, nurseTableModel);
			}
		});

		JButton buttonRefreshNurses = new JButton("Refresh");
		buttonRefreshNurses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nurseTableModel.fireTableDataChanged();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(nursesScrollPane, GroupLayout.PREFERRED_SIZE, 1304, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(879, Short.MAX_VALUE)
					.addComponent(btnCreateNurse, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnEditNurse, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnDeleteNurse, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(buttonRefreshNurses, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(nursesScrollPane, GroupLayout.PREFERRED_SIZE, 625, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCreateNurse)
						.addComponent(btnEditNurse)
						.addComponent(btnDeleteNurse)
						.addComponent(buttonRefreshNurses))
					.addGap(21))
		);
		panel_2.setLayout(gl_panel_2);
		frmMain.getContentPane().setLayout(groupLayout);
	}
}