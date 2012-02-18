package HMSViews;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import swing2swt.layout.FlowLayout;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;


public class MainPageView {

	protected Shell shlHome;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlHome.setMaximized(true);
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlHome = new Shell();
		shlHome.setImage(SWTResourceManager.getImage("C:\\work\\Hospital-Manager\\Hospital-Manager\\docs\\icon\\red-cross-icon.png"));
		shlHome.setMinimumSize(new Point(1000, 650));
		shlHome.setSize(644, 500);
		shlHome.setText("Main Page");
		
		Group optionsGroup = new Group(shlHome, SWT.NONE);
		optionsGroup.setText("Options");
		
		CoolBar coolBar = new CoolBar(optionsGroup, SWT.FLAT);
		
		Group notificationsGroup = new Group(shlHome, SWT.NONE);
		notificationsGroup.setText("Notifications");
		GroupLayout gl_shlHome = new GroupLayout(shlHome);
		gl_shlHome.setHorizontalGroup(
			gl_shlHome.createParallelGroup(GroupLayout.LEADING)
				.add(gl_shlHome.createSequentialGroup()
					.add(5)
					.add(gl_shlHome.createParallelGroup(GroupLayout.LEADING, false)
						.add(notificationsGroup, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.add(optionsGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(799))
		);
		gl_shlHome.setVerticalGroup(
			gl_shlHome.createParallelGroup(GroupLayout.LEADING)
				.add(gl_shlHome.createSequentialGroup()
					.add(5)
					.add(optionsGroup, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(notificationsGroup, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addContainerGap())
		);
		GroupLayout gl_notificationsGroup = new GroupLayout(notificationsGroup);
		gl_notificationsGroup.setHorizontalGroup(
			gl_notificationsGroup.createParallelGroup(GroupLayout.LEADING)
				.add(0, 92, Short.MAX_VALUE)
		);
		gl_notificationsGroup.setVerticalGroup(
			gl_notificationsGroup.createParallelGroup(GroupLayout.LEADING)
				.add(0, 226, Short.MAX_VALUE)
		);
		notificationsGroup.setLayout(gl_notificationsGroup);
		GroupLayout gl_optionsGroup = new GroupLayout(optionsGroup);
		gl_optionsGroup.setHorizontalGroup(
			gl_optionsGroup.createParallelGroup(GroupLayout.LEADING)
				.add(gl_optionsGroup.createSequentialGroup()
					.add(coolBar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_optionsGroup.setVerticalGroup(
			gl_optionsGroup.createParallelGroup(GroupLayout.LEADING)
				.add(gl_optionsGroup.createSequentialGroup()
					.add(32)
					.add(coolBar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		optionsGroup.setLayout(gl_optionsGroup);
		shlHome.setLayout(gl_shlHome);

	}
}
