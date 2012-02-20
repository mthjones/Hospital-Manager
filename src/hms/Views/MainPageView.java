package hms.Views;

import hms.Managers.PatientManager;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPageView {

	public JFrame frmMainPage;
	private JTable tablePatients;
	private PatientManager patientManager;

	/**
	 * Create the application.
	 */
	public MainPageView() {
		initialize();
		
		maximizeWindow();
		patientManager = new PatientManager();
	}

	private void maximizeWindow() {
		frmMainPage.setExtendedState(frmMainPage.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainPage = new JFrame();
		frmMainPage.setTitle("Main Page");
		frmMainPage.setBounds(100, 100, 2000, 2000);
		frmMainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Hello");
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblWardNumber = new JLabel("Ward #");
		
		JLabel lblAssignedTo = new JLabel("Assigned to");
		
		JSeparator separator = new JSeparator();
		
		tablePatients = new JTable();
		tablePatients.setModel(new DefaultTableModel(
			new Object[][] {
				{"Name", "Number"},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Name", "Health Number"
			}
		));
		tablePatients.getColumnModel().getColumn(1).setPreferredWidth(96);
		
		JButton btnEditPatient = new JButton("Edit Patient");
		btnEditPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				patientManager.EditPatient();
			}
		});
		
		JLabel lblPatients = new JLabel("Patients");
		
		JButton btnRefresh = new JButton("Refresh");
		
		JButton btnCreatePatient = new JButton("Create Patient");
		btnCreatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientManager.CreatePatient();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmMainPage.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tablePatients, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 1072, Short.MAX_VALUE)
							.addComponent(lblAssignedTo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWardNumber))
						.addComponent(lblPatients, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCreatePatient)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditPatient, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWardNumber)
						.addComponent(lblAssignedTo)
						.addComponent(lblNewLabel)
						.addComponent(lblUsername))
					.addGap(8)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPatients)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tablePatients, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEditPatient)
							.addComponent(btnCreatePatient))
						.addComponent(btnRefresh))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frmMainPage.getContentPane().setLayout(groupLayout);
	}
}
