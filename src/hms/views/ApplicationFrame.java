package hms.views;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
	private static final String TITLE = "Hospital Manager";
	private static final Dimension INITIAL_SIZE = new Dimension(800, 600);
	private static final Image ICON = new ImageIcon("/icon.png").getImage();
	
	public ApplicationFrame() {
		super(TITLE);
		initComponents();
		configureWindow();
	}
	
	private void initComponents() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Patients", new PatientPanel());
		tabbedPane.addTab("Nurses", null);//new MainView(false).frmMain.getContentPane());
		getContentPane().add(tabbedPane);
	}
	
	private void configureWindow() {
		setIconImage(ICON);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
	}
}