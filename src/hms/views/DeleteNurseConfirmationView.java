package hms.views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import hms.models.NurseTableModel;
import hms.models.Nurse;
import hms.controllers.NurseController;

public class DeleteNurseConfirmationView {
	public JFrame frmConfirmDeleteNurse;
	private JLabel lblMessage;
	private JButton btnConfirm;
	private JButton btnCancel;
	private int idNumber;
	private NurseTableModel mainViewTableModel;
	
	/**
	 * Create the view
	 * @param idNumber the id number of the nurse to be deleted
	 * @param mainViewTableModel 
	 */
	public DeleteNurseConfirmationView(int idNumber, NurseTableModel mainViewTableModel) {
		this.mainViewTableModel = mainViewTableModel;
		
		initialize();
		this.idNumber = idNumber;
		
		centreWindow(frmConfirmDeleteNurse);
	}
	
	public static void centreWindow(JFrame frmConfirmDeleteNurse) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frmConfirmDeleteNurse.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frmConfirmDeleteNurse.getHeight()) / 2);
	    frmConfirmDeleteNurse.setLocation(x, y);
	}
	
	/**
	 * initialize the window
	 */
	public void initialize() {
		frmConfirmDeleteNurse = new JFrame();
		frmConfirmDeleteNurse.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon.png")));
		frmConfirmDeleteNurse.setTitle("Confirm Nurse Deletion");
		frmConfirmDeleteNurse.setBounds(100, 100, 450, 200);
		frmConfirmDeleteNurse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		lblMessage = new JLabel("Are you sure you wish to delete this nurse?");
		
		btnConfirm = new JButton("OK");
		btnCancel = new JButton("Cancel");
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NurseController.doDeleteNurse(idNumber);
				frmConfirmDeleteNurse.dispose();
				mainViewTableModel.fireTableDataChanged();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConfirmDeleteNurse.dispose();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmConfirmDeleteNurse.getContentPane());
		
		groupLayout.setAutoCreateContainerGaps(true);
		
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(lblMessage)
					.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(172, Short.MAX_VALUE)
							.addComponent(btnConfirm)
							.addComponent(btnCancel)
							.addContainerGap(172, Short.MAX_VALUE))
		);
		
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblMessage)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(btnConfirm)
							.addComponent(btnCancel))
		);
		
		frmConfirmDeleteNurse.getContentPane().setLayout(groupLayout);
	}
	
	
}
