package hms.views;

import javax.swing.*;
import java.awt.*;

import hms.controllers.LoginController;

public class ApplicationFrame extends JFrame {
	private static final String TITLE = "Hospital Manager";
	private static final Image ICON = new ImageIcon("/icon.png").getImage();
	private boolean adminAuthLevel;
	
	public ApplicationFrame() {
		super(TITLE);
		final LoginController loginController = new LoginController();
		final LoginView loginView = new LoginView(this, loginController);
		loginView.show();
		adminAuthLevel = loginController.getIsAdminAuthorized();
		initUI();
		configureWindow();
		this.setVisible(true);
	}
	
	/**
	 * Initialize the components for the application frame.
	 */
	private void initUI() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Patients", new PatientPanel());
		if (adminAuthLevel) {
			tabbedPane.addTab("Nurses", new NursePanel());
		}
		getContentPane().add(tabbedPane);
	}
	
	/**
	 * Configure the application frame itself.
	 */
	private void configureWindow() {
		setIconImage(ICON);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setMinimumSize(new Dimension(getSize().width, 600));
		setSize(new Dimension(getMinimumSize().width, Math.min(getSize().height, Toolkit.getDefaultToolkit().getScreenSize().height)));
		setLocationRelativeTo(null);
	}
}