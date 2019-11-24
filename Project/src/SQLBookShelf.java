import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.*; // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package

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
	String jdbcUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s" + "&useSSL=false", IP_of_instance,
			databaseName, instanceConnectionName);
	Connection connection;
	// private final ArrayList<Book> bookShelf;
	// private int size;

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
			ResultSet resultSet = statement
					.executeQuery(String.format("SELECT count(*) FROM book WHERE ISBN = \"%s\";", book.getISBN()));
			resultSet.next();
			if (resultSet.getInt(1) == 0) {
				statement.executeUpdate(String.format(
						"INSERT INTO book " + "  (ISBN, title, fName, lName, description, genre) " + "VALUES "
								+ "  (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\");",
						book.getISBN(), book.getTitle(), book.getfName(), book.getlName(), book.getDescription(),
						book.getGenre()));
				isAdded = true;
			} else {
				throw new Exception("Book exists, ISBN: \"" + book.getISBN() + "\"");
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
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
			} // end while
			readIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // end try-catch
	} // end addFromCSV

	@Override
	public Book removeBook(String key) throws Exception {
		Book removedBook = getBook(key);
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(String.format("DELETE FROM book WHERE ISBN = \"%s\";", key));
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return removedBook;
	} // end removeBook

	@Override
	public Book getBook(String key) {
		Book ret = null;
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM book WHERE ISBN = \"%s\";", key));
			if (!resultSet.next()) {
				throw new Exception("Book not found, ISBN: \"" + key + "\"");
			} else {
				ret = new Book(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3) + " " + resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6));
				ret.setRating(searchBook(SearchingField.ISBN, key, SortingCriteria.AUTHOR).get(0).getRating());
				ret.setComment(getAllComment(resultSet.getString(1)));
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return ret;
	} // end getBook

	@Override
	public ArrayList<Book> searchBook(SearchingField field, String keyword, SortingCriteria sortBy) {
		ArrayList<Book> ret = new ArrayList<>();
		String queryStatement = "";

		if (field.equals(SearchingField.ALL)) {
			queryStatement = String.format(
					" SELECT book.ISBN, title, fName, lName, description, genre, ifnull( avg(score),0) as avg_score "
							+ "FROM book " + "LEFT JOIN rating " + "ON book.ISBN = rating.ISBN " + "WHERE ( "
							+ "UPPER(book.ISBN)   LIKE UPPER(\"%%%s%%\") OR "
							+ "UPPER(title)       LIKE UPPER(\"%%%s%%\") OR "
							+ "UPPER(fName)       LIKE UPPER(\"%%%s%%\") OR "
							+ "UPPER(lName)       LIKE UPPER(\"%%%s%%\") OR "
							+ "UPPER(description) LIKE UPPER(\"%%%s%%\") OR "
							+ "UPPER(genre)       LIKE UPPER(\"%%%s%%\") )" + "GROUP BY book.ISBN " + "ORDER BY %s;",
					keyword, keyword, keyword, keyword, keyword, keyword, sortBy.sortingCriteria());
		} else {
			queryStatement = String.format(
					" SELECT book.ISBN, title, fName, lName, description, genre, ifnull( avg(score),0) as avg_score "
							+ "FROM book " + "LEFT JOIN rating " + "ON book.ISBN = rating.ISBN " + "WHERE ( "
							+ "UPPER(book.%s)       LIKE UPPER(\"%%%s%%\") )" + "GROUP BY book.ISBN " + "ORDER BY %s;",
					field.searchingfield(), keyword, sortBy.sortingCriteria());
		} // end if

		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(queryStatement);
			while (resultSet.next()) {
				Book b = new Book(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3) + " " + resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6));
				b.setRating(Double.parseDouble(resultSet.getString(7)));
				b.setComment(getAllComment(resultSet.getString(1)));
				ret.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return ret;
	} // end search

	@Override
	public int size() {
		int size = -1;
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT count(*) FROM book;"));
			resultSet.next();
			size = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			return size;
		}
		return size;
	} // end size

	/***
	 * Add a rating score to the book
	 * 
	 * @param ISBN        the ISBN of the rated book
	 * @param UID         the UID of the user
	 * @param ratingScore the rating score, any rational number between 0 and
	 *                    5(inclusive).
	 * @return True if succeed, else false
	 */
	public boolean addRating(String ISBN, String UID, double ratingScore) {
		checkUID(UID);
		boolean isAdded = false;
		if (ratingScore < 0 || ratingScore > 5) {
			try {
					 
				throw new Exception("Rating score should be any rational number between 0 and 5(inclusive). Current: "+ ratingScore);
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		} // end try-catch

		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT count(*) FROM rating WHERE ISBN = \"%s\" AND UID = \"%s\";", ISBN, UID));
			resultSet.next();
			if (resultSet.getInt(1)==0){
				statement.executeUpdate( String.format( "INSERT INTO rating "+
														"  (ISBN, UID, score) "+
														"VALUES "+
														"  (\"%s\", \"%s\", %f);", 
														ISBN,
														UID,
														ratingScore));
				isAdded = true;
			} else{
				throw new Exception("This user has rated this Book, ISBN: \"" + ISBN + "\" ID: \"" + UID + "\"");
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return isAdded;
	} // end addRating


	/***
	 * Remove a rating score to the book
	 * 
	 * @param ISBN        the ISBN of the rated book
	 * @param UID         the UID of the user
	 * @return True if succeed, else false
	 */
	public boolean removeRating(String ISBN, String UID) {
		getRating(ISBN, UID);
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(String.format("DELETE FROM rating WHERE ISBN = \"%s\" AND UID = \"%s\";", ISBN, UID));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} // end try-catch
		return true;
	} // end removeRating

	/***
	 * Get a rating score of the user to the book
	 * 
	 * @param ISBN        the ISBN of the rated book
	 * @param UID         the UID of the user
	 * @return The rating score
	 */
	public double getRating(String ISBN, String UID) {
		double ret = -1;
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM rating WHERE ISBN = \"%s\" AND UID = \"%s\";", ISBN, UID));
			if (!resultSet.next()) {
				throw new Exception("Record not found, ISBN: \"" + ISBN + "\" ID: \"" + UID + "\"");
			} else {
				ret = Double.parseDouble(resultSet.getString(3));
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return ret;
	} // end getRating

	/***
	 * Add a comment score to the book
	 * 
	 * @param ISBN the ISBN of the rated book
	 * @param UID the UID of the user
	 * @param content the content of the comment, one paragroh, up to 1000 characters.
	 * @return True if succeed, else false
	 */
	public boolean addComment(String ISBN, String UID, String content){
		checkUID(UID);
		boolean isAdded = false;
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT count(*) FROM comments WHERE ISBN = \"%s\" AND UID = \"%s\";", ISBN, UID));
			resultSet.next();
			if (resultSet.getInt(1)==0){
				statement.executeUpdate( String.format( "INSERT INTO comments "+
														"  (ISBN, UID, content) "+
														"VALUES "+
														"  (\"%s\", \"%s\", \"%s\");", 
														ISBN,
														UID,
														content));
				isAdded = true;
			} else{
				throw new Exception("This user has commented this Book, ISBN: \"" + ISBN + "\" ID: \"" + UID + "\"");
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return isAdded;
	} // end addComment

	/***
	 * Remove a comment of a user to the book
	 * 
	 * @param ISBN        the ISBN of the book
	 * @param UID         the UID of the user
	 * @return True if succeed, else false
	 */
	public boolean removeComment(String ISBN, String UID) {
		getRating(ISBN, UID);
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(String.format("DELETE FROM comments WHERE ISBN = \"%s\" AND UID = \"%s\";", ISBN, UID));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} // end try-catch
		return true;
	} // end removeRating

	/***
	 * Get a comment of the user to the book
	 * 
	 * @param ISBN        the ISBN of the rated book
	 * @param UID         the UID of the user
	 * @return The content of a comment
	 */
	public String getComment(String ISBN, String UID) {
		String ret = "";
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM comments WHERE ISBN = \"%s\" AND UID = \"%s\";", ISBN, UID));
			if (!resultSet.next()) {
				throw new Exception("Record not found, ISBN: \"" + ISBN + "\" ID: \"" + UID + "\"");
			} else {
				ret = resultSet.getString(3);
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return ret;
	} // end getRating

	
	/***
	 * Get all comment of a book
	 * 
	 * @param ISBN        the ISBN of the rated book
	 * @return The hashtable of a UID-comment pair, where UID is the key
	 */
	public HashMap<String, String> getAllComment(String ISBN) {
		HashMap<String, String> ret = new Hashtable<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM comments WHERE ISBN = \"%s\";", ISBN));
			if (!resultSet.next()) {
				throw new Exception("Record not found, ISBN: \"" + ISBN + "\"");
			} else {
				do{
					ret.put(resultSet.getString(2), resultSet.getString(3));
				} while(resultSet.next());
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return ret;
	} // end getRating

	/***
	 * Check the UID, if not in the database then add it
	 * 
	 * @param UID         the UID of the user
	 * @return True if is in the database, else false
	 */
	private boolean checkUID(String UID) {
		boolean isUIDexist = false;
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(String.format("SELECT count(*) FROM userGroup WHERE UID = \"%s\";", UID));
			resultSet.next();
			if (resultSet.getInt(1)==0){
				statement.executeUpdate( String.format( "INSERT INTO userGroup "+
														"  (UID) "+
														"VALUES "+
														"  (\"%s\");", 
														UID));
				isUIDexist = false;
			} else{
				isUIDexist = true;
				//throw new Exception("This user is in the database, ID: \"" + UID + "\"");
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch
		return isUIDexist;
	} // end addRating
} // end LocalBookShelf
