package hms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import hms.util.Database;
import hms.views.LoginView;
import hms.controllers.LoginController;
import hms.views.MainView;

class HospitalManager implements Runnable
{
	public void run() {
		final LoginController loginController = new LoginController();
		final LoginView loginView = new LoginView(loginController);
		loginController.setView(loginView);
		
		final JFrame mainFrame = new JFrame("Hospital Manager");
		mainFrame.setIconImage(new ImageIcon("/icon.png").getImage());
		//mainFrame.getContentPane().add();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		
		final JDialog loginDialog = new JDialog(mainFrame, "Login", Dialog.ModalityType.APPLICATION_MODAL);
		loginDialog.getContentPane().add(loginView);
		loginDialog.getRootPane().setDefaultButton(loginView.getLoginButton());
		loginDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				loginDialog.dispose();
				System.exit(0);
			}
		});
		loginDialog.setResizable(false);
		loginDialog.pack();
		loginDialog.setLocationRelativeTo(mainFrame);
		
		loginDialog.setVisible(true);
		// mainFrame.setVisible(true);
		MainView mv = new MainView();
		mv.frmMain.setVisible(true);
	}
	
	public static void main(String[] args) {
		// Shutdown hook to close the database connection when the application exits
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					Database.getInstance().closeConnection();
				} catch (SQLException sqle) { }
			}
		});
		
		// Instantiate our database connection now so we don't have to when the user tries to log in
		try {
			Database.getInstance();
		} catch (SQLException sqle) { }
		
		HospitalManager hm = new HospitalManager();
		SwingUtilities.invokeLater(hm);
	}
}