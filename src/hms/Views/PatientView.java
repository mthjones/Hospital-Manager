package hms.Views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;

public class PatientView {

	public JFrame frmPatient;
	private JTextField textFieldPatientTelephoneNumber;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientEmail;
	private JTextField textFieldPatientHealthCareNumber;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public PatientView() {
		initialize();

		centreWindow(frmPatient);
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
		frmPatient = new JFrame();
		frmPatient.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\work\\Hospital-Manager\\Hospital-Manager\\docs\\icon\\hms_icon.png"));
		frmPatient.setAlwaysOnTop(true);
		frmPatient.setTitle("Patient");
		frmPatient.setBounds(100, 100, 698, 534);
		frmPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblName = new JLabel("Name");

		JLabel lblTelephoneNumber = new JLabel("Telephone Number");

		textFieldPatientTelephoneNumber = new JTextField();
		textFieldPatientTelephoneNumber.setColumns(10);

		JButton btnSaveAndClose = new JButton("Save and Close");
		btnSaveAndClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPatient.dispose();
			}
		});
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		textFieldPatientEmail = new JTextField();
		textFieldPatientEmail.setColumns(10);
		
		final JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		final JRadioButton rdbtnFemale = new JRadioButton("Female");
		
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
		
		JLabel lblAddress = new JLabel("Address");
		
		JLabel lblHealthCareNumber = new JLabel("Health Care Number");
		
		textFieldPatientHealthCareNumber = new JTextField();
		textFieldPatientHealthCareNumber.setColumns(10);
		
		JPanel panelSpecialCareInformation = new JPanel();
		panelSpecialCareInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JTextPane textPanePatientAddress = new JTextPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label = new JLabel("Emergency Contact");
		
		JLabel label_1 = new JLabel("Telephone Number");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("Name");
		
		JLabel label_3 = new JLabel("Email");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_3)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPatient.dispose();
			}
		});
		
		JLabel lblBirthdate = new JLabel("Birthdate");
		MaskFormatter birthdateMaskFormatter = null;
		try {
			birthdateMaskFormatter = new MaskFormatter("##.##.####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JFormattedTextField formattedTextFieldBirthdate = new JFormattedTextField(birthdateMaskFormatter);
		
		JLabel lblExDdmmyyyy = new JLabel("ex: dd.MM.YYYY");
		GroupLayout groupLayout = new GroupLayout(frmPatient.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSaveAndClose)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textFieldPatientName, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblAddress)
												.addComponent(lblHealthCareNumber, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addComponent(lblTelephoneNumber, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(textPanePatientAddress, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textFieldPatientHealthCareNumber, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
												.addComponent(textFieldPatientTelephoneNumber)))))
								.addGap(21)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblBirthdate, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldPatientEmail, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(rdbtnMale)
											.addGap(18)
											.addComponent(rdbtnFemale, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
										.addComponent(formattedTextFieldBirthdate))
									.addComponent(lblExDdmmyyyy, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addGap(106))
							.addComponent(panelSpecialCareInformation, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPatientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnMale)
						.addComponent(rdbtnFemale)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHealthCareNumber)
						.addComponent(textFieldPatientHealthCareNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBirthdate)
						.addComponent(formattedTextFieldBirthdate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPanePatientAddress, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddress)
						.addComponent(lblExDdmmyyyy))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldPatientTelephoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTelephoneNumber))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEmail)
							.addComponent(textFieldPatientEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSpecialCareInformation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveAndClose)
						.addComponent(btnClose))
					.addContainerGap())
		);
		
		JLabel lblMedications = new JLabel("Medications");
		
		JLabel lblMedicalInformation = new JLabel("Medical Information");
		
		JTextPane textPaneMedications = new JTextPane();
		
		JTextPane textPane_1 = new JTextPane();
		
		JLabel lblSpecialCareInformation = new JLabel("Special Care Information");
		
		JTextPane textPaneHistory = new JTextPane();
		
		JTextPane textPaneComments = new JTextPane();
		
		JLabel lblHistory = new JLabel("History");
		
		JLabel lblComments = new JLabel("Comments");
		GroupLayout gl_panelSpecialCareInformation = new GroupLayout(panelSpecialCareInformation);
		gl_panelSpecialCareInformation.setHorizontalGroup(
			gl_panelSpecialCareInformation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSpecialCareInformation.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSpecialCareInformation.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMedicalInformation)
						.addGroup(gl_panelSpecialCareInformation.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_panelSpecialCareInformation.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblMedications)
								.addComponent(lblHistory))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelSpecialCareInformation.createParallelGroup(Alignment.LEADING)
								.addComponent(textPaneMedications, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
								.addComponent(textPaneHistory, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
							.addGroup(gl_panelSpecialCareInformation.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSpecialCareInformation, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblComments))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelSpecialCareInformation.createParallelGroup(Alignment.LEADING)
								.addComponent(textPaneComments, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
								.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelSpecialCareInformation.setVerticalGroup(
			gl_panelSpecialCareInformation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSpecialCareInformation.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMedicalInformation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelSpecialCareInformation.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMedications)
						.addComponent(textPaneMedications, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSpecialCareInformation)
						.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelSpecialCareInformation.createParallelGroup(Alignment.LEADING)
						.addComponent(textPaneComments, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPaneHistory, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHistory)
						.addComponent(lblComments))
					.addGap(22))
		);
		panelSpecialCareInformation.setLayout(gl_panelSpecialCareInformation);
		frmPatient.getContentPane().setLayout(groupLayout);
	}
}