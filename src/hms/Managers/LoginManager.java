package hms.Managers;

import hms.Views.MainView;
import hms.models.User;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.SQLException;

public class LoginManager {
	public void Login(JFrame loginFrame, JLabel lblInvalidLogin, String username, char[] password)
	{
		try {
			if (User.authenticate(username, new String(password))) {
				lblInvalidLogin.setVisible(false);
				loginFrame.dispose();
				MainView mainPageView = new MainView();
				mainPageView.frmMain.setVisible(true);
			} else {
				lblInvalidLogin.setVisible(true);
			}
		} catch (SQLException sqle) {
			loginFrame.dispose();
		}
	}
}
