
public class Book {
	private String title;
	private String fName;
	private String lName;
	private String description;
	private String genre;
	
	public Book() {
	}

	public Book(String title, String name, String description, String genre) {
		this.title = title;
		this.fName = name.split("\\s")[0];
		this.lName = name.split("\\s")[1];
		this.description = description;
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", Name=" + getName() + ", description=" + description + ", genre=" + genre
				+ "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return fName + " " + lName;
	}

	public void setName(String Name) {
		this.fName = Name.split("\\s")[0];
		this.lName = Name.split("\\s")[1];
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
} // end Book class
