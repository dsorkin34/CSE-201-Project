import java.util.ArrayList;

public class LocalBookShelf implements BookShelf {

	private int shelfSize; 
	
	public LocalBookShelf() {
		ArrayList<Book> LocalBookShelf = new ArrayList<Book>();
		shelfSize = 0;
	}
	
	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Book> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> searchBook(String field, String keyword, SortingCriteria sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> searchBook(String field, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> searchBook(String keyword, SortingCriteria sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> searchBook(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sortByTitle(SortingCriteria sortBy) {
		// TODO Auto-generated method stub

	}

}
