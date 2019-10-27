import java.io.File;
import java.util.ArrayList;

public interface BookShelf {
	/**
	 * Add a Book to the BookShelf, books with the same ISBN is not allowed
	 * 
	 * @param book The book to be added
	 * @return True if the book is sucessfully added, false is if a book with the
	 *         same ISBN exists, or other exception occurses
	 */
	public boolean addBook(Book book);

	/**
	 * Add multiple Books from a CSV file to the BookShelf, books with the same ISBN
	 * is not allowed. One book per row The first row will be ignored as a header
	 * The format: [ISBN, title, author's name(first name and last name seperated by
	 * a space), description, genre] Planning to add auto-header-detect feature
	 * Coloumns after 5 will be igored Rows has the same ISBN with previous rows
	 * will be ignored
	 * 
	 * @param inputFile The input CSV file
	 */
	public void importCSV(File inputFile);

	/**
	 * Remove a Book from the BookShelf base on its ISBN
	 * 
	 * @param key The ISBN of the book
	 * @return the removed book, return null if the book is not exsit
	 */
	public Book removeBook(String key);

	/**
	 * Get a Book from the BookShelf base on its ISBN
	 * 
	 * @param key The ISBN of the book
	 * @return the book, return null if the book is not exsit
	 */
	public Book getBook(String key);

	/**
	 * Search for Book with the keyword in a selected field(Title, Author, Genre,
	 * etc.) and sort the result based on a selected field
	 * 
	 * @param field   The field limits the search
	 * @param keyword The search keyword, use an empty string to include all books
	 * @param sortBy  The sorting criteria
	 * @return A deep copy of ArrayList of of Book based on the searching and
	 *         sorting criteria
	 */
	public ArrayList<Book> searchBook(SearchingField field, String keyword, SortingCriteria sortBy);

	/**
	 * Get the number of Books in the BookShelf
	 * 
	 * @return The number of Books
	 */
	public int size();

}
