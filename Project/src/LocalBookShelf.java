import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LocalBookShelf implements BookShelf {
	// ======================= Property
	private final ArrayList<Book> bookShelf;
	private int size;

	// ======================= Constructor
	// The Main Constructor
	public LocalBookShelf() {
		bookShelf = new ArrayList<Book>();
		size = 0;
	} // end constructor

	// Constructorc using a CSV file
	public LocalBookShelf(File inputFile) {
		this();
		addFromCSV(inputFile);
	} // end constructor

	// ======================= Method
	@Override
	public boolean addBook(Book book) {
		boolean isAdded = false;
		if (!bookShelf.contains(book)) {
			isAdded = bookShelf.add(book);
			size++;
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
				currentAttributes = readIn.nextLine().split(",");
				assert currentAttributes.length == 5;
				currentBook = new Book(currentAttributes[0], currentAttributes[1], currentAttributes[2],
						currentAttributes[3], currentAttributes[4]);
				addBook(currentBook);
				size++;
			}
			readIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	} // end addFromCSV
	
	public boolean createCSVFile(String outputFile) {
		try {
			FileWriter csvWriter = new FileWriter(outputFile+".csv");
			for(Book input: bookShelf) {
				csvWriter.append(input.getISBN());
				csvWriter.append(",");
				csvWriter.append(input.getTitle());
				csvWriter.append(",");
				csvWriter.append(input.getAuthor());
				csvWriter.append(",");
				csvWriter.append(input.getDescription());
				csvWriter.append(",");
				csvWriter.append(input.getGenre());
				csvWriter.append("\n");
				csvWriter.flush();
				csvWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	} // end createCSVFile
	
	@Override
	public Book removeBook(String key) throws Exception {
		Book removedBook = getBook(key);
		bookShelf.remove(removedBook);
		return removedBook;
	} // end removeBook
	
	public Book getBooks(int index) {
		return bookShelf.get(index);
	}// end getBooks
	
	@Override
	public Book getBook(String key) throws Exception {
		ArrayList<Book> possibleBooks = searchBook(SearchingField.TITLE, key, SortingCriteria.AUTHOR);
		if (possibleBooks.size() == 0) {
			System.out.println("Book not found");
			return null;
		} else if (possibleBooks.size() == 1) {
			Book targetBook = possibleBooks.get(0);
			return targetBook;
		} else {
			throw new Exception("Duplicate Books exist.");
		}
	} // end getBook

	@Override
	public ArrayList<Book> searchBook(SearchingField field, String keyword, SortingCriteria sortBy) {
		ArrayList<Book> ret = new ArrayList<>();
		if (keyword.length() == 0) {
			for (Book b : bookShelf) {
				ret.add(b.clone());
			} //end for
			sortBy(ret, sortBy);
			return ret;
		} // end if 

		if (field.equals(SearchingField.ALL)) {
			for (Book b : bookShelf) {
				if(b.getISBN().contains(keyword) || 
					b.getTitle().contains(keyword) || 	
					b.getAuthor().contains(keyword) || 
					b.getDescription().contains(keyword) || 
					b.getGenre().contains(keyword) 
				)
					ret.add(b.clone());
			} // end for
			sortBy(ret, sortBy);
			return ret;
		} // end if

		if (field.equals(SearchingField.ISBN)) {
			for (Book b : bookShelf) {
				if (b.getISBN().contains(keyword))
					ret.add(b.clone());
			} // end for
		} else if (field.equals(SearchingField.TITLE)) {
			for (Book b : bookShelf) {
				if (b.getTitle().contains(keyword))
					ret.add(b.clone());
			} // end for
		} else if (field.equals(SearchingField.AUTHOR)) {
			for (Book b : bookShelf) {
				if (b.getAuthor().contains(keyword))
					ret.add(b.clone());
			} // end for
		} else if (field.equals(SearchingField.DISCRIPTION)) {
			for (Book b : bookShelf) {
				if (b.getDescription().contains(keyword))
					ret.add(b.clone());
			} // end for
		} else if (field.equals(SearchingField.GENRE)) {
			for (Book b : bookShelf) {
				if (b.getGenre().contains(keyword))
					ret.add(b.clone());
			} // end for
		} else {
		} // end if
		sortBy(ret, sortBy);
		return ret;
	} // end search

	@Override
	public int size() {
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
