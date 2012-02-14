import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.graphics.Point;


public class MainPageUI {

	protected Shell shlHome;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainPageUI window = new MainPageUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlHome.open();
		shlHome.layout();
		while (!shlHome.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlHome = new Shell();
		shlHome.setMinimumSize(new Point(500, 500));
		shlHome.setSize(450, 300);
		shlHome.setText("Main");
		
		Group optionsGroup = new Group(shlHome, SWT.NONE);
		optionsGroup.setText("Options");
		
		Group notificationsGroup = new Group(shlHome, SWT.NONE);
		notificationsGroup.setText("Notifications");
		GroupLayout gl_shlHome = new GroupLayout(shlHome);
		gl_shlHome.setHorizontalGroup(
			gl_shlHome.createParallelGroup(GroupLayout.LEADING)
				.add(gl_shlHome.createSequentialGroup()
					.addContainerGap()
					.add(gl_shlHome.createParallelGroup(GroupLayout.LEADING)
						.add(optionsGroup, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.add(notificationsGroup, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(343, Short.MAX_VALUE))
		);
		gl_shlHome.setVerticalGroup(
			gl_shlHome.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_shlHome.createSequentialGroup()
					.addContainerGap()
					.add(optionsGroup, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(notificationsGroup, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		shlHome.setLayout(gl_shlHome);

	}
}
