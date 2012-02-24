package hms.Views;

import hms.Managers.PatientManager;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.util.Vector;
import hms.models.*;

public class MainView {

	public JFrame frmMain;
	private JTable tablePatients;
	private PatientManager patientManager;

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
		
		maximizeWindow();
		patientManager = new PatientManager();
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
		
		tablePatients = new JTable();
		String[] patientsTableHeaders = new String[] {
				"Health Care Number", "Name", "Gender", "Address", "Telephone Number", "Email"
			};
		//TODO Refractor into a function so we can call it later
		Vector<Patient> patients = null;
		try{
		patients = Patient.findAllPatients();
		}catch(Exception e){
			
		}
		Object[][] rows = new Object[patients.size()][6];
		for(int i = 0; i<patients.size(); i++){
			rows[i] = new Object[]{patients.get(i).healthcare_number, patients.get(i).name, patients.get(i).address, patients.get(i).phone_number, patients.get(i).email };
		}
		tablePatients.setModel(new DefaultTableModel((rows) , patientsTableHeaders));
//		tablePatients.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null, null},
//			},
//			patientsTableHeaders
//		));
		tablePatients.getColumnModel().getColumn(0).setPreferredWidth(117);
		tablePatients.getColumnModel().getColumn(4).setPreferredWidth(106);
		tablePatients.getColumnModel().getColumn(5).setPreferredWidth(122);
		TableColumn TC = new TableColumn();
		
		JButton btnEditPatient = new JButton("Edit Patient");
		btnEditPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				patientManager.EditPatient(frmMain);
			}
		});
		
		JLabel lblPatients = new JLabel("Patients");
		
		JButton btnRefresh = new JButton("Refresh");
		
		JButton btnCreatePatient = new JButton("Create Patient");
		btnCreatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientManager.CreatePatient(frmMain);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmMain.getContentPane());
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
							.addComponent(lblAssignedTo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
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
		frmMain.getContentPane().setLayout(groupLayout);
	}
}
