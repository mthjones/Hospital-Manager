package HMSViews;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import HMSManagers.LoginManager;

public class LoginView {

	protected Shell shlLogin;
	private LoginManager loginManager;
	private Text usernameText;
	private Text passwordText;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		loginManager = new LoginManager();
		createContents();
		shlLogin.open();
		shlLogin.layout();
		while (!shlLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlLogin = new Shell();
		shlLogin.setImage(SWTResourceManager.getImage("C:\\work\\Hospital-Manager\\Hospital-Manager\\docs\\icon\\red-cross-icon.png"));
		shlLogin.setSize(450, 300);
		shlLogin.setText("Login");
		
		Button btnLogin = new Button(shlLogin, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loginManager.Login(shlLogin);
			}
		});
		btnLogin.setBounds(179, 169, 75, 25);
		btnLogin.setText("Login");
		
		Label lblUsername = new Label(shlLogin, SWT.NONE);
		lblUsername.setBounds(73, 89, 55, 15);
		lblUsername.setText("Username");
		
		Label lblPassword = new Label(shlLogin, SWT.NONE);
		lblPassword.setText("Password");
		lblPassword.setBounds(73, 121, 55, 15);
		
		usernameText = new Text(shlLogin, SWT.BORDER);
		usernameText.setToolTipText("Enter username");
		usernameText.setBounds(134, 86, 165, 21);
		
		passwordText = new Text(shlLogin, SWT.BORDER);
		passwordText.setToolTipText("Enter password");
		passwordText.setBounds(134, 118, 165, 21);

	}
}
