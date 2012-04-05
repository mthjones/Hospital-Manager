package hms.views;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
	private static final String TITLE = "Hospital Manager";
	private static final Dimension INITIAL_SIZE = new Dimension(800, 600);
	private static final Image ICON = new ImageIcon("/icon.png").getImage();
	
	public ApplicationFrame() {
		super(TITLE);
		initUI();
		configureWindow();
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
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
	}
}