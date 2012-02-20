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

public class PatientView {

	public JFrame frmPatient;
	private JTextField textFieldTelephoneNumber;

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
		frmPatient.setAlwaysOnTop(true);
		frmPatient.setTitle("Patient");
		frmPatient.setBounds(100, 100, 696, 425);
		frmPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Number");
		
		textFieldTelephoneNumber = new JTextField();
		textFieldTelephoneNumber.setColumns(10);
		
		JButton btnSaveAndClose = new JButton("Save and Close");
		btnSaveAndClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPatient.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmPatient.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(88)
							.addComponent(lblTelephoneNumber, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textFieldTelephoneNumber, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(82)
							.addComponent(lblName)))
					.addContainerGap(155, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(408, Short.MAX_VALUE)
					.addComponent(btnSaveAndClose)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblName)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldTelephoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelephoneNumber))
					.addPreferredGap(ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
					.addComponent(btnSaveAndClose)
					.addContainerGap())
		);
		frmPatient.getContentPane().setLayout(groupLayout);
	}
}
