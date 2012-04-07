package hms.views;

import javax.swing.*;
import java.awt.*;

import hms.controllers.LoginController;

public class ApplicationFrame extends JFrame {
	private static final String TITLE = "Hospital Manager";
	private static final Image ICON = new ImageIcon("/icon.png").getImage();
	
	public ApplicationFrame() {
		super(TITLE);
		initUI();
		configureWindow();
		final LoginController loginController = new LoginController();
		final LoginView loginView = new LoginView(this, loginController);
		loginView.show();
		this.setVisible(true);
	}
	
	/**
	 * Initialize the components for the application frame.
	 */
	private void initUI() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Patients", new PatientPanel());
		tabbedPane.addTab("Nurses", new NursePanel());
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