import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class LocalBookShelf implements BookShelf {
	// ======================= Property
	private final ArrayList<Book> bookShelf;

	// ======================= Constructor
	// The Main Constructor
	public LocalBookShelf() {
		bookShelf = new ArrayList<Book>();
	} // end constructor

	// ======================= Method
	@Override
	public boolean addBook(Book book) {
		if (!bookShelf.contains(book))
			return bookShelf.add(book);
		return false;
	} // end addBook

	@Override
	public void importCSV(File inputFile) {
		// Not implemented, need dissucssion
		// TODO Auto-generated method stub
	} // end importCSV

	@Override
	public Book removeBook(String key) {
		// Not implemented, need dissucssion
		// TODO Auto-generated method stub
		return null;
	} // end removeBook

	@Override
	public Book getBook(String key) {
		// Not implemented, need dissucssion
		// TODO Auto-generated method stub
		return null;
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
		return bookShelf.size();
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

}
