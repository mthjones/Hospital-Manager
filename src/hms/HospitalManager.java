package hms;

import javax.swing.*;
import java.sql.SQLException;

import hms.util.Database;
import hms.views.ApplicationFrame;

class HospitalManager implements Runnable
{
	public void run() {
		try {
			final ApplicationFrame appFrame = new ApplicationFrame();
		} catch (IndexOutOfBoundsException e) {}
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
		
		// Instantiate our database connection now so we don't have to when the user 
		// tries to log in
		try {
			Database.getInstance();
		} catch (SQLException sqle) { }
		
		HospitalManager hm = new HospitalManager();
		SwingUtilities.invokeLater(hm);
	}
}