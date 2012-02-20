package hms.Managers;

import hms.Views.MainView;

import javax.swing.JFrame;

public class LoginManager {
	public void Login(JFrame loginFrame)
	{
		loginFrame.dispose();
		MainView mainPageView = new MainView();
		mainPageView.frmMain.setVisible(true);
	}
}
