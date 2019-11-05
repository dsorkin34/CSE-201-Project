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
		
		JMenu mnLogIn = new JMenu("LogIn");
		menuBar.add(mnLogIn);
		
		JLabel lblUsername = new JLabel("Username");
		mnLogIn.add(lblUsername);
		
		JTextField logIntextField = new JTextField();
		mnLogIn.add(logIntextField);
		logIntextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		mnLogIn.add(lblPassword);
		
		JPasswordField passwordTextField = new JPasswordField();
		mnLogIn.add(passwordTextField);
		passwordTextField.setColumns(10);
		passwordTextField.setEchoChar('*');
		
		JButton btnSubmit = new JButton("Submit");
		mnLogIn.add(btnSubmit);
		
		
		JMenu mnRegister = new JMenu("Register");
		menuBar.add(mnRegister);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
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
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtSearch, 57, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtSearch, 405, SpringLayout.WEST, contentPane);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSearch, 576, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSearch, -340, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtSearch, -6, SpringLayout.WEST, btnSearch);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, btnMeetOurDevelopers);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSearch, 81, SpringLayout.NORTH, contentPane);
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
		JPanel sortFilterPanel = addFilterSortSection();
		sl_contentPane.putConstraint(SpringLayout.NORTH, sortFilterPanel, 19, SpringLayout.SOUTH, btnMeetOurDevelopers);
		sl_contentPane.putConstraint(SpringLayout.WEST, sortFilterPanel, 0, SpringLayout.WEST, btnMeetOurDevelopers);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, sortFilterPanel, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, sortFilterPanel, -830, SpringLayout.EAST, contentPane);
		sortFilterPanel.setBackground(Color.WHITE);
		sortFilterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(sortFilterPanel);
		JScrollPane scrollPane = new JScrollPane(createBookViewSection());
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 19, SpringLayout.SOUTH, btnContactUs);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.EAST, sortFilterPanel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -26, SpringLayout.EAST, contentPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
	}
	private JPanel addFilterSortSection() {
		JPanel sortFilterPanel = new JPanel();
		SpringLayout sl_panel = new SpringLayout();
		sortFilterPanel.setLayout(sl_panel);
		
		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		sl_panel.putConstraint(SpringLayout.NORTH, lblSortBy, 6, SpringLayout.NORTH, sortFilterPanel);
		sl_panel.putConstraint(SpringLayout.WEST, lblSortBy, 10, SpringLayout.WEST, sortFilterPanel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblSortBy, 28, SpringLayout.NORTH, sortFilterPanel);
		sl_panel.putConstraint(SpringLayout.EAST, lblSortBy, 70, SpringLayout.WEST, sortFilterPanel);
		sortFilterPanel.add(lblSortBy);
		ButtonGroup sort = new ButtonGroup();
		JRadioButton author = new JRadioButton("Author");
		author.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, author, 6, SpringLayout.SOUTH, lblSortBy);
		sl_panel.putConstraint(SpringLayout.WEST, author, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(author);
		
		JRadioButton title = new JRadioButton("Title");
		title.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, title, 6, SpringLayout.SOUTH, author);
		sl_panel.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(title);
		
		JRadioButton rating = new JRadioButton("Rating");
		rating.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, rating, 6, SpringLayout.SOUTH, title);
		sl_panel.putConstraint(SpringLayout.WEST, rating, 0, SpringLayout.WEST, lblSortBy);
		sl_panel.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(rating);
		sort.add(author);
		sort.add(title);
		sort.add(rating);
		
		JLabel lblFilter = new JLabel("Filter");
		sl_panel.putConstraint(SpringLayout.NORTH, lblFilter, 8, SpringLayout.SOUTH, rating);
		sl_panel.putConstraint(SpringLayout.WEST, lblFilter, 12, SpringLayout.WEST, sortFilterPanel);
		sl_panel.putConstraint(SpringLayout.EAST, lblFilter, 0, SpringLayout.EAST, lblSortBy);
		lblFilter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		sortFilterPanel.add(lblFilter);
		
		JLabel lblRating = new JLabel("Rating");
		sl_panel.putConstraint(SpringLayout.NORTH, lblRating, 9, SpringLayout.SOUTH, lblFilter);
		sl_panel.putConstraint(SpringLayout.WEST, lblRating, 0, SpringLayout.WEST, lblSortBy);
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 14));
		sortFilterPanel.add(lblRating);
		
		JCheckBox chckbxOneStar = new JCheckBox("One Star *");
		chckbxOneStar.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, chckbxOneStar, 6, SpringLayout.SOUTH, lblRating);
		sl_panel.putConstraint(SpringLayout.WEST, chckbxOneStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxOneStar);
		
		JCheckBox chckbxTwoStar = new JCheckBox("Two Star **");
		chckbxTwoStar.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, chckbxTwoStar, 6, SpringLayout.SOUTH, chckbxOneStar);
		sl_panel.putConstraint(SpringLayout.WEST, chckbxTwoStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxTwoStar);
		
		JCheckBox chckbxThreeStar = new JCheckBox("Three Star ***");
		chckbxThreeStar.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, chckbxThreeStar, 6, SpringLayout.SOUTH, chckbxTwoStar);
		sl_panel.putConstraint(SpringLayout.WEST, chckbxThreeStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxThreeStar);
		
		JCheckBox chckbxFourStar = new JCheckBox("Four Star ****");
		chckbxFourStar.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, chckbxFourStar, 6, SpringLayout.SOUTH, chckbxThreeStar);
		sl_panel.putConstraint(SpringLayout.WEST, chckbxFourStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxFourStar);
		
		JCheckBox chckbxFiveStar = new JCheckBox("Five Star *****");
		chckbxFiveStar.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, chckbxFiveStar, 6, SpringLayout.SOUTH, chckbxFourStar);
		sl_panel.putConstraint(SpringLayout.WEST, chckbxFiveStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxFiveStar);
		return sortFilterPanel;
		
	}
	private JPanel createBookViewSection() {
		
		Book test = new Book("The Hungry Catepillar","Greg, Crawford","It's a good book, that talks about a lot of differne things. blahblahblahblahblahblahblahblahblah","Horror");
		
		System.out.println(test.toString());
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setLayout(new GridLayout(10,3));
		JPanel example = createBookPanel(test);
		JPanel example1 = createBookPanel(test);
		JPanel example2 = createBookPanel(test);
		menu.add(example);
		menu.add(example1);
		menu.add(example2);
		return menu;
	}
	private static JPanel createBookPanel(Book newBook) {
		JPanel bookPanel = new JPanel(new GridLayout(3,1));
		JLabel bookLabel = new JLabel(newBook.getTitle());
		JLabel bookAuthor = new JLabel("Author: "+newBook.getfName()+" "+newBook.getlName());
		JLabel bookDescription = new JLabel("Description: \n "+newBook.getDescription());
		bookPanel.add(bookLabel);
		bookPanel.add(bookAuthor);
		bookPanel.add(bookDescription);
		bookPanel.setBackground(Color.white);
		bookPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		return bookPanel;
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
