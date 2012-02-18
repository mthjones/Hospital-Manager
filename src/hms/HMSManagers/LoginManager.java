package HMSManagers;

import org.eclipse.swt.widgets.Shell;
import HMSViews.MainPageView;

public class LoginManager {
	public void Login(Shell shell)
	{
		shell.dispose();
		MainPageView mainPageView = new MainPageView();
		mainPageView.open();
	}
}
