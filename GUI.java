package org.eclipse.wb.swt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("PageByPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 658);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnLogin = new JButton("Login");
		menuBar.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		menuBar.add(btnRegister);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblPagebypage = new JLabel("PageByPage");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPagebypage, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPagebypage, 26, SpringLayout.WEST, contentPane);
		lblPagebypage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblPagebypage);
		
		JLabel lblSahara = new JLabel("By Sahara");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSahara, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSahara, 52, SpringLayout.WEST, contentPane);
		contentPane.add(lblSahara);
		
		JButton btnMeetOurDevelopers = new JButton("Meet Our Developers");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnMeetOurDevelopers, 56, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnMeetOurDevelopers, 5, SpringLayout.WEST, contentPane);
		btnMeetOurDevelopers.setForeground(new Color(255, 255, 255));
		btnMeetOurDevelopers.setBackground(new Color(0, 0, 0));
		contentPane.add(btnMeetOurDevelopers);
		
		JButton btnContactUs = new JButton("Contact Us");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnContactUs, 56, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnContactUs, 163, SpringLayout.WEST, contentPane);
		btnContactUs.setForeground(new Color(255, 255, 255));
		btnContactUs.setBackground(new Color(0, 0, 0));
		contentPane.add(btnContactUs);
		
		JButton btnRequestAnUpload = new JButton("Request an Upload");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnRequestAnUpload, 56, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnRequestAnUpload, 261, SpringLayout.WEST, contentPane);
		btnRequestAnUpload.setForeground(new Color(255, 255, 255));
		btnRequestAnUpload.setBackground(new Color(0, 0, 0));
		contentPane.add(btnRequestAnUpload);
		
		txtSearch = new JTextField();
		txtSearch.setText("Search Books");
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtSearch, 57, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtSearch, 405, SpringLayout.WEST, contentPane);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSearch, 56, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSearch, 526, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSearch, 81, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSearch, 605, SpringLayout.WEST, contentPane);
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 0, 0));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(txtSearch.getText());
				txtSearch.setText("");
			}
		});
		contentPane.add(btnSearch);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, -73, SpringLayout.EAST, btnContactUs);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 26, SpringLayout.SOUTH, btnMeetOurDevelopers);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 345, SpringLayout.SOUTH, btnMeetOurDevelopers);
		contentPane.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setFont(new Font("Tahoma", Font.BOLD, 15));
		sl_panel.putConstraint(SpringLayout.NORTH, lblSortBy, 6, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblSortBy, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblSortBy, 28, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblSortBy, 70, SpringLayout.WEST, panel);
		panel.add(lblSortBy);
		ButtonGroup sort = new ButtonGroup();
		JRadioButton author = new JRadioButton("Author");
		sl_panel.putConstraint(SpringLayout.NORTH, author, 6, SpringLayout.SOUTH, lblSortBy);
		sl_panel.putConstraint(SpringLayout.WEST, author, 0, SpringLayout.WEST, lblSortBy);
		panel.add(author);
		
		JRadioButton title = new JRadioButton("Title");
		sl_panel.putConstraint(SpringLayout.NORTH, title, 6, SpringLayout.SOUTH, author);
		sl_panel.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, lblSortBy);
		panel.add(title);
		
		JRadioButton rating = new JRadioButton("Rating");
		sl_panel.putConstraint(SpringLayout.NORTH, rating, 6, SpringLayout.SOUTH, title);
		sl_panel.putConstraint(SpringLayout.WEST, rating, 0, SpringLayout.WEST, lblSortBy);
		sl_panel.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, lblSortBy);
		panel.add(rating);
		sort.add(author);
		sort.add(title);
		sort.add(rating);
		
		JLabel lblFilter = new JLabel("Filter");
		sl_panel.putConstraint(SpringLayout.WEST, lblFilter, 12, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblFilter, 0, SpringLayout.EAST, lblSortBy);
		lblFilter.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblFilter);
		
		JLabel lblRating = new JLabel("Rating");
		sl_panel.putConstraint(SpringLayout.NORTH, lblRating, 9, SpringLayout.SOUTH, lblFilter);
		sl_panel.putConstraint(SpringLayout.WEST, lblRating, 0, SpringLayout.WEST, lblSortBy);
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblRating);
		
		JComboBox<String> ratingComboBox = new JComboBox<String>();
		sl_panel.putConstraint(SpringLayout.SOUTH, lblFilter, -5, SpringLayout.NORTH, ratingComboBox);
		sl_panel.putConstraint(SpringLayout.NORTH, ratingComboBox, 32, SpringLayout.SOUTH, rating);
		sl_panel.putConstraint(SpringLayout.WEST, ratingComboBox, 55, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, ratingComboBox, -8, SpringLayout.EAST, panel);
		ratingComboBox.addItem("");
		ratingComboBox.addItem("* - One Star");
		ratingComboBox.addItem("** - Two Star");
		ratingComboBox.addItem("*** - Three Star");
		ratingComboBox.addItem("**** - Four Star");
		ratingComboBox.addItem("***** -
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
