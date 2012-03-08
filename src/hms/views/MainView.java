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
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

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
        
        JButton btnDeletePatient = new JButton("DeletePatient");
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablePatients.getSelectedRowCount() == 1){
					Object[][] content = tableModel.getContent();
					String healthcareNumber = content[tablePatients.getSelectedRow()][0].toString();
					patientManager.deletePatient(frmMain, healthcareNumber);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblOptions = new JLabel("Options");
		
		GroupLayout groupLayout = new GroupLayout(frmMain.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 1051, Short.MAX_VALUE)
							.addComponent(lblAssignedTo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWardNumber))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
								.addComponent(lblOptions, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(lblPatients, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(inHospitalRadioButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(viewAllRadioButton))
								.addComponent(jsp, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1175, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnCreatePatient)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditPatient, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnDeletePatient, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(viewAllRadioButton)
						.addComponent(inHospitalRadioButton)
						.addComponent(lblPatients)
						.addComponent(lblOptions))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefresh)
						.addComponent(btnEditPatient)
						.addComponent(btnCreatePatient)
						.addComponent(btnDeletePatient))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Users");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addContainerGap(109, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addContainerGap(616, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmMain.getContentPane().setLayout(groupLayout);
	}
}
