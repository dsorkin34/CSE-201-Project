import java.util.Comparator;
import java.util.HashMap;

/*
 * Author: Nuo XU
 * the date: 09/26/2019
 * This is the Book class for a single Book
 */
public class Book {
	// Static Comparator to sort by different criteria
	public static final Comparator<Book> BY_TITLE = new SortByTitle();
	public static final Comparator<Book> BY_AUTHOR = new SortByAuthor();
	public static final Comparator<Book> BY_GENRE = new SortByGenre();
	// ======================= Property
	private String ISBN; // The ISBN of a Book to identify tself
	private String title; // The title of the book
	private String fName; // The first name of the author
	private String lName; // The last name of the author
	private String description; // The description of the book, must be paragraph
	private String genre; // The genre of the book
	private double rating; // The average rating score based on a 0 to 5 scale
	private int numOfRating; // The number of ratings
	private HashMap<String, String> comments; // HashMap of <userName, comment> pairs

	// ======================= Constructor
	/**
	 * The Main Constructor
	 * 
	 * @param ISBN        The ISBN of the book
	 * @param title       The title of the book
	 * @param name        The author's name, first name and last name only,
	 *                    seperated by a space
	 * @param description The description of the book, must be paragraph (no return
	 *                    or line feed character)
	 * @param genre       The genre of the book
	 */
	public Book(String ISBN, String title, String name, String description, String genre) {
		this.ISBN = ISBN;
		this.title = title;
		this.fName = name.split("\\s")[0];
		this.lName = name.split("\\s")[1];
		this.description = description;
		this.genre = genre;
		this.rating = 0;
		this.numOfRating = 0;
		this.comments = new HashMap<>();
	} // end constructor

	// ======================= Method
	/**
	 * Override toString method
	 * 
	 * @return All attributes of a Book object
	 */
	@Override
	public String toString() {
		return	"Book\n" + 
				"ISBN        = " + ISBN        + "\n"+
				"title       = " + title       + "\n"+
				"name        = " + getAuthor() + "\n"+
				"description = " + description + "\n"+
				"genre       = " + genre       + "\n"+
				"Rating      = " + rating      + "\n"+
				"Rating Count= " + numOfRating + "\n"+
				"Comments    = " + comments    + "\n";
	} // end toString

	/**
	 * Override the clone method
	 * 
	 * @return A deep copy of Book
	 */
	@Override
	public Book clone() {
		return new Book(ISBN, title, getAuthor(), description, genre);
	} // end clone

	/**
	 * Override equals method
	 * 
	 * @return True if ISBN of two Book is identical, else false
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Book))
			return false;
		Book that = (Book) o;
		return this.ISBN.equals(that.ISBN);
	} // end equals

	/**
	 * Private static class implements Comparator interface to sort by title
	 */
	private static class SortByTitle implements Comparator<Book> {
		/**
		 * Implement the compare method from Comparator interface to compare Book
		 * objects by title
		 * 
		 * @param o1 the first Book
		 * @param o2 the second Book
		 * @return The result of compare 2 Book's title
		 */
		@Override
		public int compare(Book o1, Book o2) {
			return o1.title.compareTo(o2.title);
		} // end compare
	} // end class SortByTitle

	/**
	 * Private static class implements Comparator interface to sort by author's name
	 */
	private static class SortByAuthor implements Comparator<Book> {
		/**
		 * Implement the compare method from Comparator interface to compare Book
		 * objects by author's name, first by the last name, then by the first name
		 * 
		 * @param o1 the first Book
		 * @param o2 the second Book
		 * @return The result of compare 2 Book's author's name
		 */
		@Override
		public int compare(Book o1, Book o2) {
			int compareResult = o1.lName.compareTo(o2.lName);
			if (compareResult != 0) {
				return compareResult;
			} else {
				return o1.fName.compareTo(o2.fName);
			} // end if
		} // end compare
	} // end class SortByAuthor

	/**
	 * Private static class implements Comparator interface to sort by genre
	 */
	private static class SortByGenre implements Comparator<Book> {
		/**
		 * Implement the compare method from Comparator interface to compare Book
		 * objects by genre
		 * 
		 * @param o1 the first Book
		 * @param o2 the second Book
		 * @return The result of compare 2 Book's genre
		 */
		@Override
		public int compare(Book o1, Book o2) {
			return o1.genre.compareTo(o2.genre);
		} // end compare 
	} // end class SortByGenre

	// ======================= Getter & Setter
	/**
	 * Get the ISBN of a Book object
	 * 
	 * @return The ISBN of a Book
	 */
	public String getISBN() {
		return ISBN;
	} // end getISBN

	/**
	 * Get the title of a Book object
	 * 
	 * @return The tile of a Book
	 */
	public String getTitle() {
		return title;
	} // end getTitle

	/**
	 * Get the author's name of a Book object
	 * 
	 * @return The author's first name and last name seperated by a space
	 */
	public String getAuthor() {
		return fName + " " + lName;
	} // end getAuthor

	/**
	 * Get the author's first name of a Book object
	 * 
	 * @return The author's first name
	 */
	public String getfName() {
		return fName;
	} // end getfName

	/**
	 * Get the author's last name of a Book object
	 * 
	 * @return The author's last name
	 */
	public String getlName() {
		return lName;
	} // end getlName

	/**
	 * Get the description of a Book object
	 * 
	 * @return The one-paragraph description of a Book
	 */
	public String getDescription() {
		return description;
	} // end getDescription

	/**
	 * Get the genre of a Book object
	 * 
	 * @return The genre of a Book
	 */
	public String getGenre() {
		return genre;
	} // end getGenre

		/**
	 * Get the rating of a Book object
	 * 
	 * @return The rating of a Book
	 */
	public double getRating() {
		return rating;
	} // end getRating
	
	/**
	 * Add a new rating to the overall rating
	 * DO NOT USE THIS IN SQL BOOKSHELF
	 * 
	 * @param newRating the new user rating of a Book
	 * @return True if successful, else if
	 */
	public boolean addRating(double newRating) {
		rating = (rating * numOfRating + newRating) / (numOfRating + 1);
		numOfRating++;
		return true;
	} // end addRating

	/**
	 * Set the overall rating
	 * 
	 * @param score the average score of a Book
	 */
	public void setRating(double score){
		this.rating = score;
	}

	/**
	 * Get the copy of HashMap of comments of a Book object
	 * DO NOT USE THIS IN SQL BOOKSHELF
	 * 
	 * @return The HashMap of comments
	 */
	public HashMap<String, String> getComments() {
		@SuppressWarnings("unchecked")
		HashMap<String, String> ret = (HashMap<String, String>)comments.clone();
		return ret;
	} // end getComments
	
	/**
	 * Add a new comment to the HashMao of comments
	 * DO NOT USE THIS IN SQL BOOKSHELF
	 * 
	 * @param userName userName of the commenter
	 * @param content the content of the comment
	 * @return True if successful, else false
	 */
	public boolean addComment(String userName, String content) {
		comments.put(userName, content);
		return true;
	} // end addComment

} // end Book class
