package hms.views;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
	private static final String TITLE = "Hospital Manager";
	private static final Dimension INITIAL_SIZE = new Dimension(800, 600);
	private static final Image ICON = new ImageIcon("/icon.png").getImage();
	
	public ApplicationFrame() {
		super(TITLE);
		setIconImage(ICON);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(INITIAL_SIZE);		
		getContentPane().add(new MainView(false).frmMain.getContentPane());
		pack();
		setLocationRelativeTo(null);
	}
}