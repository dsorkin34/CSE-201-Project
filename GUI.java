import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.awt.Color;

import javax.swing.BorderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import swing2swt.layout.FlowLayout;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;
import javax.swing.*;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.ScrolledComposite;
public class GUI {

	protected Shell shell;
	private Text txtSearch;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUI window = new GUI();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(1059, 636);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("Login/Register");
		
		Menu menu_2 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_2);
		
		MenuItem mntmLogin = new MenuItem(menu_2, SWT.NONE);
		mntmLogin.setText("Login");
		
		MenuItem mntmRegister = new MenuItem(menu_2, SWT.NONE);
		mntmRegister.setText("Register");
		
		Label lblSahara = new Label(shell, SWT.NONE);
		lblSahara.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSahara.setBounds(5, 5, 158, 37);
		lblSahara.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblSahara.setText("PagebyPage");
		
		Label lblPagebypage = new Label(shell, SWT.NONE);
		lblPagebypage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPagebypage.setBounds(5, 47, 158, 21);
		lblPagebypage.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblPagebypage.setText("By Sahara");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(251, 74, 138, 30);
		btnNewButton_1.setText("Request an Upload");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(5, 74, 153, 30);
		btnNewButton.setSelection(true);
		btnNewButton.setTouchEnabled(true);
		btnNewButton.setText("Meet Our Developers");
		
		Button btnContactUs = new Button(shell, SWT.NONE);
		btnContactUs.setBounds(162, 74, 83, 30);
		btnContactUs.setText("Contact Us");
		
		txtSearch = new Text(shell, SWT.BORDER);
		
		txtSearch.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		txtSearch.setBounds(395, 76, 117, 26);
		
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println(txtSearch.getText());
			}
		});
		btnSearch.setBounds(518, 74, 90, 30);
		btnSearch.setText("Search");
		
		Group group = new Group(shell, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		group.setBounds(5, 110, 153, 296);
		
		Label lblSort = new Label(group, SWT.NONE);
		lblSort.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblSort.setBounds(10, 22, 70, 20);
		lblSort.setText("Sort by:");
		
		Button btnAuthor = new Button(group, SWT.RADIO);
		btnAuthor.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnAuthor.setBounds(10, 48, 111, 20);
		btnAuthor.setText("Author");
		
		Button btnGenre = new Button(group, SWT.RADIO);
		btnGenre.setBounds(10, 74, 111, 20);
		btnGenre.setText("Genre");
		
		Button btnTitle = new Button(group, SWT.RADIO);
		btnTitle.setBounds(10, 100, 111, 20);
		btnTitle.setText("Title");
		
		Button btnRating = new Button(group, SWT.RADIO);
		btnRating.setBounds(10, 126, 111, 20);
		btnRating.setText("Rating");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(10, 178, 133, 26);
		
		Label lblFilterBy = new Label(group, SWT.NONE);
		lblFilterBy.setBounds(10, 152, 70, 20);
		lblFilterBy.setText("Filter By");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(172, 110, 830, 444);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
	}
}
