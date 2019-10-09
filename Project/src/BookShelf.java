import java.util.ArrayList;

public interface BookShelf {
	/**
	 * Add a Book to the BookShelf
	 * @param book The book to be added
	 */
	public void addBook(Book book);
	
	/**
	 * Get all Book in BookShelf in an ArrayList
	 * @return An ArrayList of Book
	 */
	public ArrayList<Book> toArrayList();

	/**
	 * Search for Book with the keyword in a selected field(Title, Author, Genre,
	 * etc.) and sort the result based on a selected field
	 * 
	 * @param field The field limits the search
	 * @param keyword The search keyword
	 * @param sortBy The sorting criteria 
	 * @return An ArrayList of Book based on the searching and sorting criteria
	 */
	public ArrayList<Book> searchBook(String field, String keyword, SortingCriteria sortBy);
	
	/**
	 * Search for Book with the keyword in a selected field(Title, Author, Genre,
	 * etc.) and sort the result based on that field
	 * 
	 * @param field The field limits the search
	 * @param keyword The search keyword
	 * @return An ArrayList of Book based on the searching and sorting criteria
	 */
	public ArrayList<Book> searchBook(String field, String keyword);
	
	/**
	 * Search for Book with the keyword in a all fields(Title and Author and Genre,
	 * etc.) and sort the result based on a selected field
	 * 
	 * @param keyword The search keyword
	 * @param sortBy The sorting criteria
	 * @return An ArrayList of Book based on the searching and sorting criteria
	 */
	public ArrayList<Book> searchBook(String keyword, SortingCriteria sortBy);
	
	/**
	 * Search for Book with the keyword in a all fields(Title and Author and Genre,
	 * etc.) and sort the result based on Title
	 * 
	 * @param keyword The search keyword
	 * @return An ArrayList of Book based on the searching and sorting criteria
	 */
	public ArrayList<Book> searchBook(String keyword);
	
	/**
	 * Sort the BookShelf based on the sorting criteria
	 * @param sortBy The sorting criteria
	 */
	public void sortByTitle(SortingCriteria sortBy);
}
