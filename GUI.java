package org.eclipse.wb.swt;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This is the visual GUI that is used for the Application PageByPage.
 * It creates a Single Page Application, and takes in User Input to help
 * catalog Books, submitted by the User
 * @author Spencer Penrod
 *
 */
public class GUI extends JFrame {

	private JPanel contentPane;
	private static LocalBookShelf shelf;
	private static LocalBookShelf queueShelf;
	private static HashMap<String, String> community; 
	private static boolean loggedIn = false;
	private static boolean isAdmin = false;
	private JPanel mainPane;
	private static JScrollPane descriptionScrollPanel;
	private static JScrollPane scrollPane;
	private static JScrollPane approvePane;
	private SpringLayout sl_Homepanel;
	private CardLayout cl_mainPane;
	/**
	 * Initiates the GUI
	 */
	public static void main(String[] args) {
		shelf = new LocalBookShelf();
		queueShelf = new LocalBookShelf();
		community = new HashMap<String, String>();
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
	 * Creates the frame, and puts the elements that will be displayed into the frame
	 */
	public GUI() {
		setBackground(Color.WHITE);
		setTitle("PageByPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 658);
		
		//Creates the Menu Bar that holds the LogIn/Register Button
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Creates a JButton that creates the JFrame that is used for logging in/Registering
		JButton btnLoginRegister = new JButton("LogIn/Register");
		btnLoginRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createLoginFrame(true);
			}
		});
		btnLoginRegister.setBackground(Color.BLACK);
		btnLoginRegister.setForeground(Color.WHITE);
		menuBar.add(btnLoginRegister);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		//Creates a JButton that is used by users to return to the Default Home Panel of the page
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createBookViewSection(shelf.searchBook(SearchingField.TITLE, "", SortingCriteria.TITLE));
				cl_mainPane.first(mainPane);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnHome, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnHome, 0, SpringLayout.WEST, contentPane);
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setBackground(new Color(0, 0, 0));
		contentPane.add(btnHome);
		
		//Creates a JButton that is used by the User to access the Contact Information of the Developers
		JButton btnContactUs = new JButton("Contact Us");
		btnContactUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_mainPane.show(mainPane, "contactUsPanel");
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnContactUs, 0, SpringLayout.NORTH, btnHome);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnContactUs, 6, SpringLayout.EAST, btnHome);
		btnContactUs.setForeground(new Color(255, 255, 255));
		btnContactUs.setBackground(new Color(0, 0, 0));
		contentPane.add(btnContactUs);
		
		//Creates a JButton that is used by a Logged In User to access the Upload Panel
		JButton btnRequestAnUpload = new JButton("Request an Upload");
		btnRequestAnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(loggedIn) {
				cl_mainPane.show(mainPane, "upload");
				}else {
					createLoginFrame(true);
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnRequestAnUpload, 0, SpringLayout.NORTH, btnHome);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnRequestAnUpload, 6, SpringLayout.EAST, btnContactUs);
		btnRequestAnUpload.setForeground(new Color(255, 255, 255));
		btnRequestAnUpload.setBackground(new Color(0, 0, 0));
		contentPane.add(btnRequestAnUpload);
		
		//Creates the TextField that will be used by the user, to look up specific books
		JTextField txtSearch = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtSearch, 1, SpringLayout.NORTH, btnHome);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtSearch, 6, SpringLayout.EAST, btnRequestAnUpload);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtSearch, -427, SpringLayout.EAST, contentPane);
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtSearch.setText("");
			}
		});
		txtSearch.setText("Search Books");
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		//Creates the Button that takes the Input on the Search Text Field and returns an array of the books with these keywords
		JButton btnSearch = new JButton("Search");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, btnHome);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSearch, 12, SpringLayout.EAST, txtSearch);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSearch, -336, SpringLayout.EAST, contentPane);
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 0, 0));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!(txtSearch.getText().equals("")||txtSearch.getText().equals("Search Books"))){
				cl_mainPane.show(mainPane, "homePanel");
				
				createBookViewSection(shelf.searchBook(SearchingField.TITLE, txtSearch.getText(), SortingCriteria.TITLE));
				}
				txtSearch.setText("Search Books");
			}
		});
		contentPane.add(btnSearch);
		
		//Creates the JButton that will be used by the Admin to access the Approval Panel
		JButton btnBookRequests = new JButton("Book Requests");
		btnBookRequests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(isAdmin) {
				cl_mainPane.show(mainPane, "approvalPanel");
				createBookApproveSection(queueShelf.searchBook(SearchingField.TITLE, "", SortingCriteria.TITLE));
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnBookRequests, 0, SpringLayout.NORTH, btnHome);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnBookRequests, 6, SpringLayout.EAST, btnSearch);
		btnBookRequests.setBackground(Color.BLACK);
		btnBookRequests.setForeground(Color.WHITE);
		contentPane.add(btnBookRequests);
		btnBookRequests.setVisible(true);
		
		//Creates the Main Pane that will hold all functions of the other JPanels, Simulating a Single Page Application
		mainPane = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, mainPane, -5, SpringLayout.SOUTH, contentPane);
		mainPane.setBackground(Color.WHITE);
		sl_contentPane.putConstraint(SpringLayout.NORTH, mainPane, 9, SpringLayout.SOUTH, btnHome);
		sl_contentPane.putConstraint(SpringLayout.WEST, mainPane, 10, SpringLayout.WEST, btnHome);
		sl_contentPane.putConstraint(SpringLayout.EAST, mainPane, -39, SpringLayout.EAST, contentPane);
		contentPane.add(mainPane);
		cl_mainPane = new CardLayout(0,0);
		mainPane.setLayout(cl_mainPane);
		
		//Creates the starting Pane that will be used to display books in a list that can be sorted
		JPanel homePanel = new JPanel();
		homePanel.setBackground(Color.WHITE);
		mainPane.add(homePanel, "homePanel");
		sl_Homepanel = new SpringLayout();
		homePanel.setLayout(sl_Homepanel);
		
		//Creates the Panel that will be in the homePanel that will be holding each of the books for the use to view
		descriptionScrollPanel = new JScrollPane();
		descriptionScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descriptionScrollPanel.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		descriptionScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descriptionScrollPanel.getViewport().setBackground(Color.WHITE);
		mainPane.add(descriptionScrollPanel, "descriptionScrollPanel");
		
		//Creates the Pane that will be in the homePanel. This will hold the sorting radio buttons, and filter checkboxes
		JPanel sortFilterPanel = addFilterSortSection();
		sl_Homepanel.putConstraint(SpringLayout.NORTH, sortFilterPanel, 10, SpringLayout.NORTH, homePanel);
		sl_Homepanel.putConstraint(SpringLayout.WEST, sortFilterPanel, 8, SpringLayout.WEST, homePanel);
		sl_Homepanel.putConstraint(SpringLayout.SOUTH, sortFilterPanel, 376, SpringLayout.NORTH, homePanel);
		sl_Homepanel.putConstraint(SpringLayout.EAST, sortFilterPanel, 182, SpringLayout.WEST, homePanel);
		sortFilterPanel.setBackground(Color.WHITE);
		sortFilterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		homePanel.add(sortFilterPanel);
		
		//Creates the ScrollPane that will display a panel showing the description of a Book with a rating and comments
		scrollPane = new JScrollPane();
		sl_Homepanel.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, homePanel);
		sl_Homepanel.putConstraint(SpringLayout.WEST, scrollPane, -752, SpringLayout.EAST, homePanel);
		sl_Homepanel.putConstraint(SpringLayout.SOUTH, scrollPane, -45, SpringLayout.SOUTH, homePanel);
		sl_Homepanel.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, homePanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 19, SpringLayout.SOUTH, btnContactUs);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.EAST, sortFilterPanel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -26, SpringLayout.EAST, contentPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		createBookViewSection(shelf.searchBook(SearchingField.TITLE, "", SortingCriteria.TITLE));
		homePanel.add(scrollPane, "name_1458073958000");
		
		//Creates the panel for users to request uploads
		JPanel uploadPanel = createRequestPanel();
		mainPane.add(uploadPanel, "upload");
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBackground(Color.WHITE);
		SpringLayout sl_descriptionPanel = new SpringLayout();
		descriptionPanel.setLayout(sl_descriptionPanel);
		
		//Creates the panel used for Admins to approve or deny books
		JPanel approvalPanel = new JPanel();
		approvalPanel.setBackground(Color.WHITE);
		mainPane.add(approvalPanel, "approvalPanel");
		SpringLayout sl_approvalPanel = new SpringLayout();
		approvalPanel.setLayout(sl_approvalPanel);
		
		//Creates the ScrollPane that will be used to display the pending books
		approvePane = new JScrollPane();
		sl_approvalPanel.putConstraint(SpringLayout.EAST, approvePane, 936, SpringLayout.WEST, approvalPanel);
		approvePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sl_approvalPanel.putConstraint(SpringLayout.NORTH, approvePane, 10, SpringLayout.NORTH, approvalPanel);
		sl_approvalPanel.putConstraint(SpringLayout.WEST, approvePane, 10, SpringLayout.WEST, approvalPanel);
		sl_approvalPanel.putConstraint(SpringLayout.SOUTH, approvePane, 515, SpringLayout.NORTH, approvalPanel);
		createBookApproveSection(queueShelf.searchBook(SearchingField.TITLE, "", SortingCriteria.TITLE));
		approvalPanel.add(approvePane);
		
		//Creates the Panel showinf each of the developers contact info
		JPanel contactUsPanel = createContactUsPanel();
		mainPane.add(contactUsPanel, "contactUsPanel");
	}//End Constructor
	
	/**
	 * Creates a JPanel of the Filter Panel
	 * @return sortFilterPanel this is the Panel that is used to display the sorting functions
	 */
	private JPanel addFilterSortSection() {
		JPanel sortFilterPanel = new JPanel();
		SpringLayout sl_sortFilterPanel = new SpringLayout();
		sortFilterPanel.setLayout(sl_sortFilterPanel);
		
		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, lblSortBy, 6, SpringLayout.NORTH, sortFilterPanel);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, lblSortBy, 10, SpringLayout.WEST, sortFilterPanel);
		sl_sortFilterPanel.putConstraint(SpringLayout.SOUTH, lblSortBy, 28, SpringLayout.NORTH, sortFilterPanel);
		sl_sortFilterPanel.putConstraint(SpringLayout.EAST, lblSortBy, 70, SpringLayout.WEST, sortFilterPanel);
		sortFilterPanel.add(lblSortBy);
		
		ButtonGroup sort = new ButtonGroup();
		JRadioButton author = new JRadioButton("Author");
		author.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createBookViewSection(shelf.searchBook(SearchingField.AUTHOR, "", SortingCriteria.AUTHOR));
			}
		});
		author.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, author, 6, SpringLayout.SOUTH, lblSortBy);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, author, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(author);
		
		JRadioButton title = new JRadioButton("Title");
		title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createBookViewSection(shelf.searchBook(SearchingField.TITLE, "", SortingCriteria.TITLE));
			}
		});
		title.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, title, 6, SpringLayout.SOUTH, author);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(title);
		
		JRadioButton genre = new JRadioButton("Genre");
		genre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createBookViewSection(shelf.searchBook(SearchingField.GENRE, "", SortingCriteria.GENRE));
			}
		});
		genre.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, genre, 6, SpringLayout.SOUTH, title);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, genre, 0, SpringLayout.WEST, lblSortBy);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(genre);
		sort.add(author);
		sort.add(title);
		sort.add(genre);
		
		JLabel lblFilter = new JLabel("Filter");
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, lblFilter, 8, SpringLayout.SOUTH, genre);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, lblFilter, 12, SpringLayout.WEST, sortFilterPanel);
		sl_sortFilterPanel.putConstraint(SpringLayout.EAST, lblFilter, 0, SpringLayout.EAST, lblSortBy);
		lblFilter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		sortFilterPanel.add(lblFilter);
		
		JLabel lblRating = new JLabel("Rating");
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, lblRating, 9, SpringLayout.SOUTH, lblFilter);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, lblRating, 0, SpringLayout.WEST, lblSortBy);
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 14));
		sortFilterPanel.add(lblRating);
		
		JCheckBox chckbxOneStar = new JCheckBox("One Star *");
		chckbxOneStar.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, chckbxOneStar, 6, SpringLayout.SOUTH, lblRating);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, chckbxOneStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxOneStar);
		
		JCheckBox chckbxTwoStar = new JCheckBox("Two Star **");
		chckbxTwoStar.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, chckbxTwoStar, 6, SpringLayout.SOUTH, chckbxOneStar);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, chckbxTwoStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxTwoStar);
		
		JCheckBox chckbxThreeStar = new JCheckBox("Three Star ***");
		chckbxThreeStar.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, chckbxThreeStar, 6, SpringLayout.SOUTH, chckbxTwoStar);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, chckbxThreeStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxThreeStar);
		
		JCheckBox chckbxFourStar = new JCheckBox("Four Star ****");
		chckbxFourStar.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, chckbxFourStar, 6, SpringLayout.SOUTH, chckbxThreeStar);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, chckbxFourStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxFourStar);
		
		JCheckBox chckbxFiveStar = new JCheckBox("Five Star *****");
		chckbxFiveStar.setBackground(Color.WHITE);
		sl_sortFilterPanel.putConstraint(SpringLayout.NORTH, chckbxFiveStar, 6, SpringLayout.SOUTH, chckbxFourStar);
		sl_sortFilterPanel.putConstraint(SpringLayout.WEST, chckbxFiveStar, 0, SpringLayout.WEST, lblSortBy);
		sortFilterPanel.add(chckbxFiveStar);
		return sortFilterPanel;
		
	}//End FilterPanel
	
	/**
	 * Creates a Panel that shows the list of Books in the ArrayList, and puts them into the 
	 * ScrollPane that is inside of the GUI constructor
	 */
	private void createBookViewSection(ArrayList<Book> books) {
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setLayout(new GridLayout(10,2));
		for(int i=0;i<books.size();i++) {
		JPanel description = createBookPanel(books.get(i));
		Book tempBook = books.get(i);
		description.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createDescriptionPanel(tempBook);
				cl_mainPane.show(mainPane, "descriptionScrollPanel");
			}
		});
		menu.add(description);
		}
		scrollPane.setViewportView(menu);
	}//End createBookViewSection
	/**
	 * Creates the Panel that displays the Books waiting to be approved by the Admin 
	 * @param books is an ArrayList of Book that need to be approved by the Admin
	 */
	private void createBookApproveSection(ArrayList<Book> books) {
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setLayout(new GridLayout(10,2));
		for(int i=0;i<books.size();i++) {
		JPanel description = createBookPanel(books.get(i));
		JButton approve = new JButton("Approve");
		Book tempBook = books.get(i);
		approve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shelf.addBook(tempBook);
				try {
					queueShelf.removeBook(tempBook.getISBN());
				} catch (Exception e) {
					e.printStackTrace();
				}
				createBookApproveSection(queueShelf.searchBook(SearchingField.TITLE, "", SortingCriteria.TITLE));
			}
		});
		JButton deny = new JButton("Deny");
		deny.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					queueShelf.removeBook(tempBook.getISBN());
				} catch (Exception e) {
					e.printStackTrace();
				}
				createBookApproveSection(queueShelf.searchBook(SearchingField.TITLE, "", SortingCriteria.TITLE));
			}
		});
		description.add(approve);
		description.add(deny);
		
		menu.add(description);
		}
		approvePane.setViewportView(menu);
	}//End createBookViewSection
	/**
	 * Creates a formatted book to be displayed within the HomePanel in the ScrollPane
	 * @return the formatted book inside of a JPanel
	 */
	private static JPanel createBookPanel(Book newBook) {
		JPanel bookPanel = new JPanel(new GridLayout(4,1));
		JLabel bookLabel = new JLabel(newBook.getTitle());
		JLabel bookAuthor = new JLabel("Author: "+newBook.getfName()+" "+newBook.getlName());
		JLabel bookDescription = new JLabel("Genre:  "+newBook.getGenre());
		JLabel bookRating = new JLabel("Rating: "+newBook.getRating()+"Stars");
		bookPanel.add(bookLabel);
		bookPanel.add(bookAuthor);
		bookPanel.add(bookDescription);
		bookPanel.add(bookRating);
		bookPanel.setBackground(Color.white);
		bookPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		return bookPanel;
	}//End createBookPanel
	
	/**
	 * Creates a Panel displays the Title, Author, Rating, and Comments from a Book depending on the
	 * parameter
	 * @param book is a Book object that will be used to create the Description Panel
	 */
	private static void createDescriptionPanel(Book book) {
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBackground(Color.WHITE);
		SpringLayout sl_descriptionPanel = new SpringLayout();
		descriptionPanel.setLayout(sl_descriptionPanel);
		
		JPanel bookInfoPanel = new JPanel();
		sl_descriptionPanel.putConstraint(SpringLayout.NORTH, bookInfoPanel, 10, SpringLayout.NORTH, descriptionPanel);
		sl_descriptionPanel.putConstraint(SpringLayout.WEST, bookInfoPanel, 10, SpringLayout.WEST, descriptionPanel);
		sl_descriptionPanel.putConstraint(SpringLayout.EAST, bookInfoPanel, -10, SpringLayout.EAST, descriptionPanel);
		bookInfoPanel.setBackground(Color.WHITE);
		SpringLayout sl_panel_1 = new SpringLayout();
		bookInfoPanel.setLayout(sl_panel_1);
		descriptionPanel.add(bookInfoPanel);
		
		JLabel lblTitle = new JLabel(book.getTitle());
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblTitle, 10, SpringLayout.NORTH, bookInfoPanel);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblTitle, 10, SpringLayout.WEST, bookInfoPanel);
		bookInfoPanel.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author: "+book.getAuthor());
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAuthor, 6, SpringLayout.SOUTH, lblTitle);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAuthor, 0, SpringLayout.WEST, lblTitle);
		bookInfoPanel.add(lblAuthor);
		
		JLabel lblRating_1 = new JLabel("Rating: "+book.getRating()+" Stars");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblRating_1, 6, SpringLayout.SOUTH, lblAuthor);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblRating_1, 0, SpringLayout.WEST, lblTitle);
		bookInfoPanel.add(lblRating_1);
		
		JLabel lblDescription = new JLabel("Description: "+book.getDescription());
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblDescription, 30, SpringLayout.SOUTH, lblAuthor);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblDescription, 10, SpringLayout.WEST, bookInfoPanel);
		bookInfoPanel.add(lblDescription);
		descriptionScrollPanel.setViewportView(descriptionPanel);
		
		JPanel commentsPanel = new JPanel();
		sl_descriptionPanel.putConstraint(SpringLayout.SOUTH, commentsPanel, 400, SpringLayout.SOUTH, descriptionPanel);
		sl_descriptionPanel.putConstraint(SpringLayout.SOUTH, bookInfoPanel, -8, SpringLayout.NORTH, commentsPanel);
		sl_descriptionPanel.putConstraint(SpringLayout.WEST, commentsPanel, 10, SpringLayout.WEST, descriptionPanel);
		sl_descriptionPanel.putConstraint(SpringLayout.EAST, commentsPanel, -10, SpringLayout.EAST, descriptionPanel);
		sl_descriptionPanel.putConstraint(SpringLayout.NORTH, commentsPanel, 330, SpringLayout.NORTH, descriptionPanel);
		commentsPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_commentPanel = new GridBagConstraints();
		gbc_commentPanel.fill = GridBagConstraints.BOTH;
		gbc_commentPanel.gridx = 0;
		gbc_commentPanel.gridy = 0;
		
		JTextPane textPane = new JTextPane();
		textPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textPane.getText().equals("Add a Comment")) {
					textPane.setText("");
				}
			}
		});
		sl_panel_1.putConstraint(SpringLayout.NORTH, textPane, -80, SpringLayout.SOUTH, bookInfoPanel);
		sl_panel_1.putConstraint(SpringLayout.WEST, textPane, 10, SpringLayout.WEST, bookInfoPanel);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textPane, -10, SpringLayout.SOUTH, bookInfoPanel);
		sl_panel_1.putConstraint(SpringLayout.EAST, textPane, 743, SpringLayout.WEST, bookInfoPanel);
		textPane.setText("Add a Comment");
		textPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textPane.setBackground(Color.WHITE);
		bookInfoPanel.add(textPane);
		
		JButton button = new JButton("Submit Comment");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(loggedIn) {
				if(!(textPane.getText().equals("Add a Comment")||textPane.getText().equals(""))) {
				book.addComment(textPane.getText());
				commentsPanel.add(createCommentPanel(textPane.getText()));
				textPane.setText("Add a Comment");
				}
				}else {
					createLoginFrame(true);
				}
			}
		});
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		sl_panel_1.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, textPane);
		sl_panel_1.putConstraint(SpringLayout.WEST, button, 5, SpringLayout.EAST, textPane);
		
		JComboBox<String> ratingcomboBox = new JComboBox<String>();
		sl_panel_1.putConstraint(SpringLayout.NORTH, ratingcomboBox, 129, SpringLayout.SOUTH, lblDescription);
		sl_panel_1.putConstraint(SpringLayout.WEST, ratingcomboBox, 0, SpringLayout.WEST, lblTitle);
		ratingcomboBox.setBackground(Color.WHITE);
		ratingcomboBox.addItem("Rating");
		ratingcomboBox.addItem("1 Star");
		ratingcomboBox.addItem("2 Star");
		ratingcomboBox.addItem("3 Star");
		ratingcomboBox.addItem("4 Star");
		ratingcomboBox.addItem("5 Star");
		bookInfoPanel.add(ratingcomboBox);
		
		JButton btnSubmitRating = new JButton("Submit Rating");
		btnSubmitRating.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(loggedIn) {
					if(ratingcomboBox.getSelectedIndex()!=0) {
					System.out.println(ratingcomboBox.getSelectedIndex());
					book.addRating(ratingcomboBox.getSelectedIndex());
					createDescriptionPanel(book);
					}
				}else {
					createLoginFrame(true);
				}
			}
		});
		sl_panel_1.putConstraint(SpringLayout.WEST, textPane, 6, SpringLayout.EAST, btnSubmitRating);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnSubmitRating, 0, SpringLayout.NORTH, button);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnSubmitRating, 6, SpringLayout.EAST, ratingcomboBox);
		btnSubmitRating.setForeground(Color.WHITE);
		btnSubmitRating.setBackground(Color.BLACK);
		bookInfoPanel.add(btnSubmitRating);
		bookInfoPanel.add(button);
		descriptionPanel.add(commentsPanel);
		commentsPanel.setLayout(new GridLayout(10, 0, 0, 0));
	}//End createDescriptionPanel
	
	/**
	 * 
	 * @param Comment is the Comment that will be Used to create the Panel displaying the Comment
	 * @return the parameter Comment as a JPanel
	 */
	private static JPanel createCommentPanel(String Comment) {
		JPanel commentPanel = new JPanel();
		commentPanel.setBackground(Color.WHITE);
		commentPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblUserName = new JLabel("Anonymous");
		lblUserName.setBackground(Color.WHITE);
		commentPanel.add(lblUserName);
		
		JTextPane txtpnComment = new JTextPane();
		txtpnComment.setEditable(false);
		txtpnComment.setText(Comment);
		commentPanel.add(txtpnComment);
		return commentPanel;
	}//End createDescriptionPanel
	
	/**
	 * Creates a JFrame that has text fields where a user can create a Log In or enter their Log In
	 * to gain function to certain operations on the software
	 * @param right is the parameter used to determine if the Frame to be displayed
	 * is due to the User inputting a wrong field, or display the regular Login Frame
	 * @return logInFrame is the Frame that has the text fields and buttons that will be used by the User
	 */
	private static JFrame createLoginFrame(boolean right) {
		JFrame logInFrame = new JFrame();
		logInFrame.setBackground(Color.WHITE);
		logInFrame.setTitle("Login/Register");
		logInFrame.setBounds(100, 100, 300, 150);
		JPanel logInInfo = new JPanel();
		JTextField userName = new JTextField();
		JTextField password = new JTextField();
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBackground(Color.WHITE);
		JLabel userNameLabel = new JLabel("UserName");
		userNameLabel.setBackground(Color.WHITE);
		JButton submit = new JButton("LogIn");
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					
					if(userName.getText().equals("Admin")&&password.getText().equals("AdminPassword")) {
						loggedIn = true;
						isAdmin = true;
						logInFrame.dispose();
					}else if(community.containsKey(userName.getText())&&community.containsValue(password.getText())) {
						loggedIn = true;
						logInFrame.dispose();
					}else {
						logInFrame.dispose();
						createLoginFrame(false);
					}
				}
		});
		
		JButton register = new JButton("Register");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					if(community.containsKey(userName.getText())){
						logInFrame.dispose();
						createLoginFrame(false);
					}else {
					if(!(userName.getText().equals("")&&password.getText().equals(""))) {
						User newUser = new User(userName.getText(),password.getText());
						community.put(newUser.getName(), newUser.getPassword());
						loggedIn = true;
						logInFrame.dispose();
						
					}else {
						logInFrame.dispose();
						createLoginFrame(false);
					}
					}
				}
		});
		if(right) {
		logInInfo.setLayout(new GridLayout(3,2));
		logInInfo.add(userNameLabel);
		logInInfo.add(userName);
		logInInfo.add(passwordLabel);
		logInInfo.add(password);
		logInInfo.add(submit);
		logInInfo.add(register);
		}else {
			logInInfo.setLayout(new GridLayout(3,3));
			logInInfo.add(userNameLabel);
			logInInfo.add(userName);
			logInInfo.add(new JLabel("Invalid Input"));
			logInInfo.add(passwordLabel);
			logInInfo.add(password);
			logInInfo.add(new JLabel("Invalid Input"));
			logInInfo.add(submit);
			logInInfo.add(register);
		}
		logInFrame.getContentPane().add(logInInfo);
		logInFrame.setVisible(true);
		return logInFrame;
	}
	
	/**
	 * Creates a Panel that will have the Text Fields that a User will use to create a Book Object
	 * @return uploadPanel is the finished Upload Panel for the User
	 */
	private static JPanel createRequestPanel() {
		JPanel uploadPanel = new JPanel();
		uploadPanel.setBackground(Color.WHITE);
		SpringLayout sl_panel = new SpringLayout();
		uploadPanel.setLayout(sl_panel);
		JTextField textFieldTitle = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textFieldTitle, 10, SpringLayout.WEST, uploadPanel);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldTitle, -766, SpringLayout.EAST, uploadPanel);
		uploadPanel.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JTextField textFieldAuthor = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textFieldAuthor, 0, SpringLayout.WEST, textFieldTitle);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldAuthor, -766, SpringLayout.EAST, uploadPanel);
		uploadPanel.add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		JComboBox comboBoxGenre = new JComboBox();
		sl_panel.putConstraint(SpringLayout.WEST, comboBoxGenre, 0, SpringLayout.WEST, textFieldTitle);
		sl_panel.putConstraint(SpringLayout.EAST, comboBoxGenre, 0, SpringLayout.EAST, textFieldTitle);
		comboBoxGenre.setBackground(Color.WHITE);
		comboBoxGenre.addItem("Genre's");
		comboBoxGenre.addItem("Horror");
		comboBoxGenre.addItem("Children's");
		comboBoxGenre.addItem("Romance");
		uploadPanel.add(comboBoxGenre);
		
		JTextArea textAreaDescription = new JTextArea();
		sl_panel.putConstraint(SpringLayout.WEST, textAreaDescription, 0, SpringLayout.WEST, textFieldTitle);
		sl_panel.putConstraint(SpringLayout.EAST, textAreaDescription, -582, SpringLayout.EAST, uploadPanel);
		textAreaDescription.setColumns(10);
		textAreaDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		uploadPanel.add(textAreaDescription);
		
		JLabel lblUpload = new JLabel("Request An Upload");
		sl_panel.putConstraint(SpringLayout.WEST, lblUpload, 10, SpringLayout.WEST, uploadPanel);
		lblUpload.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUpload.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, lblUpload, 10, SpringLayout.NORTH, uploadPanel);
		uploadPanel.add(lblUpload);
		
		JLabel lblTitle_1 = new JLabel("Title");
		lblTitle_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sl_panel.putConstraint(SpringLayout.WEST, lblTitle_1, 10, SpringLayout.WEST, uploadPanel);
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldTitle, 6, SpringLayout.SOUTH, lblTitle_1);
		lblTitle_1.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, lblTitle_1, 6, SpringLayout.SOUTH, lblUpload);
		uploadPanel.add(lblTitle_1);
		
		JLabel lblAuthor_1 = new JLabel("Author");
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldAuthor, 6, SpringLayout.SOUTH, lblAuthor_1);
		lblAuthor_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAuthor_1.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, lblAuthor_1, 6, SpringLayout.SOUTH, textFieldTitle);
		sl_panel.putConstraint(SpringLayout.WEST, lblAuthor_1, 10, SpringLayout.WEST, uploadPanel);
		uploadPanel.add(lblAuthor_1);
		
		JLabel lblGenre = new JLabel("Genre");
		sl_panel.putConstraint(SpringLayout.NORTH, comboBoxGenre, 4, SpringLayout.SOUTH, lblGenre);
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGenre.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, lblGenre, 6, SpringLayout.SOUTH, textFieldAuthor);
		sl_panel.putConstraint(SpringLayout.WEST, lblGenre, 0, SpringLayout.WEST, textFieldTitle);
		uploadPanel.add(lblGenre);
		
		JLabel lblDescriptionInput = new JLabel("Description");
		sl_panel.putConstraint(SpringLayout.NORTH, textAreaDescription, 9, SpringLayout.SOUTH, lblDescriptionInput);
		sl_panel.putConstraint(SpringLayout.NORTH, lblDescriptionInput, 34, SpringLayout.SOUTH, lblGenre);
		sl_panel.putConstraint(SpringLayout.WEST, lblDescriptionInput, 0, SpringLayout.WEST, textFieldTitle);
		uploadPanel.add(lblDescriptionInput);
		
		JButton btnSubmitRequest = new JButton("Submit Request");
		btnSubmitRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!(comboBoxGenre.getSelectedItem().equals("Genre's")||textFieldTitle.getText().equals("")||textFieldAuthor.getText().equals("")||textAreaDescription.getText().equals(""))) {
					Book upload = new Book("12345",textFieldTitle.getText(),textFieldAuthor.getText(),textAreaDescription.getText(),(String) comboBoxGenre.getSelectedItem());
					queueShelf.addBook(upload);
					comboBoxGenre.setSelectedIndex(0);
					textFieldTitle.setText("");
					textFieldAuthor.setText("");
					textAreaDescription.setText("");
				}else {
					comboBoxGenre.setSelectedIndex(0);
					textFieldTitle.setText("Invalid Entry Enter Again");
					textFieldAuthor.setText("Invalid Entry Enter Again");
					textAreaDescription.setText("Invalid Entry Enter Again");
				}
	}});
		sl_panel.putConstraint(SpringLayout.SOUTH, textAreaDescription, -6, SpringLayout.NORTH, btnSubmitRequest);
		sl_panel.putConstraint(SpringLayout.NORTH, btnSubmitRequest, 364, SpringLayout.NORTH, uploadPanel);
		btnSubmitRequest.setForeground(Color.WHITE);
		btnSubmitRequest.setBackground(Color.BLACK);
		sl_panel.putConstraint(SpringLayout.WEST, btnSubmitRequest, 10, SpringLayout.WEST, uploadPanel);
		uploadPanel.add(btnSubmitRequest);
		return uploadPanel;
	}//End createRequestPanel()
	
	/**
	 * Creates a Text Panel showing each of the Developers, their roles, and also the email address of each of the team members
	 * @return the JPanel used to create the contact Panel
	 */	
	private JPanel createContactUsPanel() {
		JPanel contactUsPanel = new JPanel();
		SpringLayout sl_contactUsPanel = new SpringLayout();
		contactUsPanel.setLayout(sl_contactUsPanel);
		
		JLabel lblSpencerPenrod = new JLabel("Spencer Penrod");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblSpencerPenrod, 10, SpringLayout.NORTH, contactUsPanel);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblSpencerPenrod, 10, SpringLayout.WEST, contactUsPanel);
		contactUsPanel.add(lblSpencerPenrod);
		
		JLabel lblManager = new JLabel("Project Manager");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblManager, 6, SpringLayout.SOUTH, lblSpencerPenrod);
		sl_contactUsPanel.putConstraint(SpringLayout.EAST, lblManager, -10, SpringLayout.EAST, lblSpencerPenrod);
		lblManager.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contactUsPanel.add(lblManager);
		
		JLabel lblAshleyBey = new JLabel("Ashley Bey");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblAshleyBey, 46, SpringLayout.SOUTH, lblManager);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblAshleyBey, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblAshleyBey);
		
		JLabel lblTechnicalManager = new JLabel("Technical Manager");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblTechnicalManager, 6, SpringLayout.SOUTH, lblAshleyBey);
		sl_contactUsPanel.putConstraint(SpringLayout.EAST, lblTechnicalManager, 0, SpringLayout.EAST, lblSpencerPenrod);
		lblTechnicalManager.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contactUsPanel.add(lblTechnicalManager);
		
		JLabel lblCliffWrighter = new JLabel("Cliff Wrighter");
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblCliffWrighter, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblCliffWrighter);
		
		JLabel lblNuoXu = new JLabel("Nuo Xu");
		sl_contactUsPanel.putConstraint(SpringLayout.SOUTH, lblNuoXu, -264, SpringLayout.SOUTH, contactUsPanel);
		sl_contactUsPanel.putConstraint(SpringLayout.SOUTH, lblCliffWrighter, -61, SpringLayout.NORTH, lblNuoXu);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblNuoXu, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblNuoXu);
		
		JLabel lblDavidSorkin = new JLabel("David Sorkin");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblDavidSorkin, 66, SpringLayout.SOUTH, lblNuoXu);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblDavidSorkin, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblDavidSorkin);
		contactUsPanel.setBackground(Color.WHITE);
		
		JLabel lblTester = new JLabel("Tester");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblTester, 6, SpringLayout.SOUTH, lblCliffWrighter);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblTester, 0, SpringLayout.WEST, lblManager);
		lblTester.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contactUsPanel.add(lblTester);
		
		JLabel lblDeveloper = new JLabel("Developer");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblDeveloper, 6, SpringLayout.SOUTH, lblNuoXu);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblDeveloper, 0, SpringLayout.WEST, lblManager);
		lblDeveloper.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contactUsPanel.add(lblDeveloper);
		
		JLabel lblDataLayer = new JLabel("Data Layer");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblDataLayer, 6, SpringLayout.SOUTH, lblDavidSorkin);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblDataLayer, 0, SpringLayout.WEST, lblManager);
		lblDataLayer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contactUsPanel.add(lblDataLayer);
		
		JLabel lblEmailPenrodswmiamiohedu = new JLabel("Email: penrodsw@miamioh.edu");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblEmailPenrodswmiamiohedu, 6, SpringLayout.SOUTH, lblManager);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblEmailPenrodswmiamiohedu, 10, SpringLayout.WEST, contactUsPanel);
		contactUsPanel.add(lblEmailPenrodswmiamiohedu);
		
		JLabel lblEmailBeyapmiamiohedu = new JLabel("Email: beyap@miamioh.edu");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblEmailBeyapmiamiohedu, 6, SpringLayout.SOUTH, lblTechnicalManager);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblEmailBeyapmiamiohedu, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblEmailBeyapmiamiohedu);
		
		JLabel lblEmailWrighmiamiohedu = new JLabel("Email: wrigh123@miamioh.edu");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblEmailWrighmiamiohedu, 6, SpringLayout.SOUTH, lblTester);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblEmailWrighmiamiohedu, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblEmailWrighmiamiohedu);
		
		JLabel lblEmailXunmiamiohedu = new JLabel("Email: xun5@miamioh.edu");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblEmailXunmiamiohedu, 6, SpringLayout.SOUTH, lblDeveloper);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblEmailXunmiamiohedu, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblEmailXunmiamiohedu);
		
		JLabel lblEmailSorkindmiamiohedu = new JLabel("Email: sorkind@miamioh.edu");
		sl_contactUsPanel.putConstraint(SpringLayout.NORTH, lblEmailSorkindmiamiohedu, 6, SpringLayout.SOUTH, lblDataLayer);
		sl_contactUsPanel.putConstraint(SpringLayout.WEST, lblEmailSorkindmiamiohedu, 0, SpringLayout.WEST, lblSpencerPenrod);
		contactUsPanel.add(lblEmailSorkindmiamiohedu);
		
		return contactUsPanel;
	}//End createContactUsPanel
}

