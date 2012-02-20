package hms.Managers;

import hms.Views.MainPageView;

import javax.swing.JFrame;

public class LoginManager {
	public void Login(JFrame loginFrame)
	{
		loginFrame.dispose();
		MainPageView mainPageView = new MainPageView();
		mainPageView.frmMainPage.setVisible(true);
	}
}
