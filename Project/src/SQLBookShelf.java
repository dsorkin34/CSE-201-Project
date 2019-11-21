	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Scanner;
	import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
	
	//
	// ─── IMPORTANT ──────────────────────────────────────────────────────────────────
	//
	// Download the MySQL connector for Java from https://dev.mysql.com/downloads/connector/j/
	// * Choose "Platform Independent"
	// Unzip the archive and add the JAR into the classpath
	// ─────────────────────────────────────────────────────────────────



	public class SQLBookShelf implements BookShelf {
		// ======================= Property
		String instanceConnectionName = "glossy-champion-258016:us-central1:cse385";
		String databaseName = "PageByPage";
		String IP_of_instance = "35.192.176.17";
		String username = "root";
		String password = "15201906221";
		String jdbcUrl = String.format(
				"jdbc:mysql://%s/%s?cloudSqlInstance=%s"
						+ "&useSSL=false",
				IP_of_instance, databaseName, instanceConnectionName);
		Connection connection;
		//private final ArrayList<Book> bookShelf;
		//private int size;

		// ======================= Constructor
		// The Main Constructor
		public SQLBookShelf() {
			try {
				connection = DriverManager.getConnection(jdbcUrl, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end constructor

		// Constructorc using a CSV file
		public SQLBookShelf(File inputFile) {
			this();
			addFromCSV(inputFile);
		} // end constructor

		// ======================= Method
		@Override
		public boolean addBook(Book book) {
			boolean isAdded = false;

			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(String.format("SELECT count(*) FROM book WHERE ISBN = \"%s\";", book.getISBN()));
				resultSet.next();
				if (resultSet.getInt(1)==0){
					statement.executeUpdate(String.format("INSERT INTO book "+
																	"(ISBN, title, fName, lName, description, genre) "+
																	"VALUES "+
																	"(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\");", 
																	book.getISBN(), book.getTitle(), book.getfName(), book.getlName(), book.getDescription(), book.getGenre()));
					isAdded = true;
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			return isAdded;
		} // end addBook

		@Override
		public void addFromCSV(File inputFile) {
			Scanner readIn;
			String[] currentAttributes = {};
			Book currentBook;
			try {
				readIn = new Scanner(inputFile);
				while (readIn.hasNextLine()) {
					currentAttributes = readIn.nextLine().split(";");
					assert currentAttributes.length == 5;
					currentBook = new Book(currentAttributes[0], currentAttributes[1], currentAttributes[2],
							currentAttributes[3], currentAttributes[4]);
					addBook(currentBook);
				}
				readIn.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} // end addFromCSV

		@Override
		public Book removeBook(String key) throws Exception {
			Book removedBook = getBook(key);
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(String.format("DELETE FROM book WHERE ISBN = \"%s\";", key));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return removedBook;
		} // end removeBook

		@Override
		public Book getBook(String key) {
			Book ret = null;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM book WHERE ISBN = \"%s\";", key));
				if (!resultSet.next()){
					System.out.println("Book not found");
				} else{
					ret = new Book(resultSet.getString(1), resultSet.getString(2), 
					resultSet.getString(3) + " " +resultSet.getString(4), 
					resultSet.getString(5), resultSet.getString(6));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				
			}
			return ret;
		} // end getBook

		@Override
		public ArrayList<Book> searchBook(SearchingField field, String keyword, SortingCriteria sortBy) {
			ArrayList<Book> ret = new ArrayList<>();
			if (keyword.length() == 0) {
				try (Statement statement = connection.createStatement()) {
					ResultSet resultSet = statement.executeQuery(String.format("SELECT book.ISBN, title, fName, lName, description, genre, ifnull( avg(score),0) as avg_score "+ 
																				"FROM book LEFT JOIN  rating "+
																				"ON book.ISBN = rating.ISBN "+
																				"GROUP BY books.ISBN " +
																				"ORDER BY %s;",sortBy.sortingCriteria()));
					while(resultSet.next()){
						Book b = new Book(resultSet.getString(1), resultSet.getString(2), 
						resultSet.getString(3) + " " +resultSet.getString(4), 
						resultSet.getString(5), resultSet.getString(6));
						ret.add(b);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ret;
			} // end if 

			if (field.equals(SearchingField.ALL)) {
				try (Statement statement = connection.createStatement()) {
					ResultSet resultSet = statement.executeQuery(String.format("SELECT book.ISBN, title, fName, lName, description, genre, ifnull( avg(score),0) as avg_score "+ 
																				"FROM book LEFT JOIN  rating "+
																				"ON book.ISBN = rating.ISBN WHERE ( "+
																				"UPPER(book.ISBN)   LIKE UPPER(\"%% %s %%\") OR " +
																				"UPPER(title)       LIKE UPPER(\"%% %s %%\") OR " +
																				"UPPER(fName)       LIKE UPPER(\"%% %s %%\") OR " +
																				"UPPER(lName)       LIKE UPPER(\"%% %s %%\") OR " +
																				"UPPER(description) LIKE UPPER(\"%% %s %%\") OR " +
																				"UPPER(genre)       LIKE UPPER(\"%% %s %%\") )" +
																				"GROUP BY book.ISBN " +
																				"ORDER BY %s;",keyword, keyword, keyword,
																				keyword, keyword, keyword, sortBy.sortingCriteria()));
					while(resultSet.next()){
						Book b = new Book(resultSet.getString(1), resultSet.getString(2), 
						resultSet.getString(3) + " " +resultSet.getString(4), 
						resultSet.getString(5), resultSet.getString(6));
						ret.add(b);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ret;
			} // end if
			
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(String.format("SELECT book.ISBN, title, fName, lName, description, genre, ifnull( avg(score),0) as avg_score "+ 
																			"FROM book LEFT JOIN  rating "+
																			"ON book.ISBN = rating.ISBN WHERE ( "+
																			"UPPER(book.%s)       LIKE UPPER(\"%% %s %%\") )" +
																			"GROUP BY book.ISBN " +
																			"ORDER BY %s;",field.searchingfield() ,keyword,sortBy.sortingCriteria()));
				while(resultSet.next()){
					Book b = new Book(resultSet.getString(1), resultSet.getString(2), 
					resultSet.getString(3) + " " +resultSet.getString(4), 
					resultSet.getString(5), resultSet.getString(6));
					ret.add(b);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ret;
		} // end search

		@Override
		public int size() {
			int size = -1;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(String.format("SELECT count(*) FROM book;"));
				resultSet.next();
				size =  resultSet.getInt(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return size;
		} // end size

		/**
		 * Sort the ArrayList of Book based on the sorting criteria
		 * 
		 * @param books  The ArrayList of Book
		 * @param sortBy The sorting criteria
		 */
		private void sortBy(ArrayList<Book> books, SortingCriteria sortBy) {
			if        (sortBy.equals(SortingCriteria.TITLE         )) {	Collections.sort(books, Book.BY_TITLE);
			} else if (sortBy.equals(SortingCriteria.AUTHOR        )) { Collections.sort(books, Book.BY_AUTHOR);
			} else if (sortBy.equals(SortingCriteria.GENRE         )) {	Collections.sort(books, Book.BY_GENRE);
			} else if (sortBy.equals(SortingCriteria.TITLE_REVERSE )) {	Collections.sort(books, Collections.reverseOrder(Book.BY_TITLE));
			} else if (sortBy.equals(SortingCriteria.AUTHOR_REVERSE)) {	Collections.sort(books, Collections.reverseOrder(Book.BY_AUTHOR));
			} else if (sortBy.equals(SortingCriteria.GENRE_REVERSE )) {	Collections.sort(books, Collections.reverseOrder(Book.BY_GENRE));
			} // end if
		} // end sortBy

	} // end LocalBookShelf
