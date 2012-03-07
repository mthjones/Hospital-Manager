package hms.views;

import hms.controllers.PatientManager;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
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
		
		tableModel = new PatientTableModel();
		tablePatients = new JTable(tableModel);
		JScrollPane jsp = new JScrollPane(tablePatients);
		
		
		JButton btnEditPatient = new JButton("Edit Patient");
		btnEditPatient.addActionListener(new ActionListener() {
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
		
		JLabel lblPatients = new JLabel("Patients");
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.fireTableDataChanged();
			}
		});
		
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
						.addComponent(jsp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
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
					.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
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
