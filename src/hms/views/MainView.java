package hms.views;

import hms.controllers.PatientManager;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.util.Vector;
import hms.models.*;

import hms.models.PatientTableModel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Component;

public class MainView {

	public JFrame frmMain;
	private JTable tablePatients;
	private PatientManager patientManager;
	private PatientTableModel tableModel;

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
		
		maximizeWindow();
		patientManager = new PatientManager(tableModel);
	}

	private void maximizeWindow() {
		frmMain.setExtendedState(frmMain.getExtendedState() | JFrame.MAXIMIZED_BOTH);
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
		
		JLabel lblWardNumber = new JLabel("Ward #");
		
		JLabel lblAssignedTo = new JLabel("Assigned to");
		
		JSeparator separator = new JSeparator();
		
		tableModel = new PatientTableModel();
		
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tablePatients.getSelectedRowCount() == 1){
					Object[][] content = tableModel.getContent();
					Object[] selectedRow = content[tablePatients.getSelectedRow()];
					String[] strs = new String[content[tablePatients.getSelectedRow()].length];
					for(int i = 0; i< strs.length; i++){
						strs[i] = content[tablePatients.getSelectedRow()][i].toString();
					}
					patientManager.EditPatient(frmMain, strs);
				}
			}
		});
        
        JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablePatients.getSelectedRowCount() == 1){
					Object[][] content = tableModel.getContent();
					String healthcareNumber = content[tablePatients.getSelectedRow()][0].toString();
					patientManager.deletePatient(frmMain, healthcareNumber);
				}
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.fireTableDataChanged();
			}
		});
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientManager.CreatePatient(frmMain);
			}
		});
		
		JButton deletePatientButton = new JButton("Delete Patient");
		
		final JRadioButton viewAllRadioButton = new JRadioButton("View All");
		
		final JRadioButton inHospitalRadioButton = new JRadioButton("In Hospital");
		viewAllRadioButton.setSelected(true);

		inHospitalRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAllRadioButton.setSelected(!inHospitalRadioButton.isSelected());
			}
		});

		viewAllRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inHospitalRadioButton.setSelected(!viewAllRadioButton.isSelected());
			}
		});
		
		JTabbedPane patientsUsersNursesTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		GroupLayout groupLayout = new GroupLayout(frmMain.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addComponent(patientsUsersNursesTabbedPane, GroupLayout.PREFERRED_SIZE, 1345, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(inHospitalRadioButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(viewAllRadioButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addGap(18)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAssignedTo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblWardNumber))
							.addComponent(separator, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1337, GroupLayout.PREFERRED_SIZE)))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(viewAllRadioButton)
						.addComponent(inHospitalRadioButton))
					.addGap(1)
					.addComponent(patientsUsersNursesTabbedPane, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefresh)
						.addComponent(btnDelete)
						.addComponent(btnEdit)
						.addComponent(btnCreate))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		tablePatients = new JTable(tableModel);
		JScrollPane jsp = new JScrollPane(tablePatients);
		patientsUsersNursesTabbedPane.addTab("Patients", null, jsp, null);
		
		JScrollPane usersScrollPane = new JScrollPane((Component) null);
		patientsUsersNursesTabbedPane.addTab("Users", null, usersScrollPane, null);
		
		JScrollPane nursesScrollPane = new JScrollPane((Component) null);
		patientsUsersNursesTabbedPane.addTab("Nurses", null, nursesScrollPane, null);
		frmMain.getContentPane().setLayout(groupLayout);
	}
}
