/*
 * Author: Nuo XU
 * the date: 09/26/2019
 * This is the Book class for a single Book
 */
public class Book {
	// ======================= Property
	private String title;
	private String fName; // The first name of the author
	private String lName; // The last name of the author
	private String description; // The description of the book, must be paragraph
	private String genre; // The genre of the book

	// ======================= Constructor
	// ..Null Constructor
	public Book() {
	}

	/**
	 * The Main Constructor
	 * 
	 * @param title       The title of the book
	 * @param name        The author's name, first name and last name only,
	 *                    seperated by a space
	 * @param description The description of the book, must be paragraph (no return
	 *                    or line feed character)
	 * @param genre       The genre of the book
	 */
	public Book(String title, String name, String description, String genre) {
		this.title = title;
		this.fName = name.split("\\s")[0];
		this.lName = name.split("\\s")[1];
		this.description = description;
		this.genre = genre;
	}

	// ======================= Method
	/**
	 * Override the stock toString method
	 * 
	 * @return All attributes of a Book object
	 */
	@Override
	public String toString() {
		return "Book [title=" + title + ", Name=" + getName() + ", description=" + description + ", genre=" + genre
				+ "]";
	}

	// ======================= Getter & Setter
	/**
	 * Get the title of a Book object
	 * 
	 * @return The tile of a Book
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the author's name of a Book object
	 * 
	 * @return The author's first name and last name seperated by a space
	 */
	public String getName() {
		return fName + " " + lName;
	}

	public void setName(String Name) {
		this.fName = Name.split("\\s")[0];
		this.lName = Name.split("\\s")[1];
	}

	/**
	 * Get the author's first name of a Book object
	 * 
	 * @return The author's first name
	 */
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * Get the author's last name of a Book object
	 * 
	 * @return The author's last name
	 */
	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * Get the description of a Book object
	 * 
	 * @return The one-paragraph description of a Book
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the genre of a Book object
	 * 
	 * @return The genre of a Book
	 */
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
} // end Book class
