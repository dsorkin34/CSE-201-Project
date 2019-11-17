
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class LocalBookShelfTest {

	LocalBookShelf bookShelf1 = new LocalBookShelf();
	LocalBookShelf bookShelf2 = new LocalBookShelf();
	LocalBookShelf bookShelf3 = new LocalBookShelf();
	LocalBookShelf bookShelf4 = new LocalBookShelf();
	LocalBookShelf bookShelf5 = new LocalBookShelf();
	LocalBookShelf bookShelfCSV = new LocalBookShelf();
	
	
	// Test the add book method to see if book was added successfully 
	// Need to test for duplicates in the arryList of books 
	// Testing to see if number of books was incremented by one every time book is added
	
	@Test
	void testAddBook() {
		
		// Creating book object
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Spencer's Book", "Spencer Penrod", "The best book", "Non-Fiction");
		
		// Test to see if books are added to the shelf
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		
		assertEquals(2, bookShelf1.size());
		
		// Test to see if it does not allow duplicate books in a shelf 
		bookShelf2.addBook(b1);
		
		
		assertEquals(1, bookShelf2.size());
	}

	@Test
	void testAddFromCSV() {
		bookShelfCSV.addFromCSV(null);
		// assertEquals(1, bookShelfCSV.size());
		
		bookShelfCSV.addFromCSV(null);
		// assertEquals(2, bookShelfCSV.size());

	}
	
	@Test
	void testRemoveBook() {
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Spencer's Book", "Spencer Penrod", "The best book", "Non-Fiction");
		Book b3 = new Book("88322","Ashley's Book", "Ashley Bey", "The best book", "Si-Fi");
		Book b4 = new Book("98236","Nuo's Book", "Nuo Xu", "A great book", "Fiction");

		bookShelf3.addBook(b1);
		bookShelf3.addBook(b2);
		assertEquals(2, bookShelf3.size());
		
		// remove a book and test to see size decreases by 1 
		try {
			bookShelf3.removeBook("Spencer's Book");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//assertEquals(1, bookShelf3.size());
		assertEquals(1, bookShelf3.size());
	}
	
	@Test 
	void testGetBook() {
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		
		bookShelf4.addBook(b1);
		bookShelf4.addBook(b2);
		
		// Test get book from bookshelf
		try {
			assertEquals(b1, bookShelf4.getBook("Cliff"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertEquals(b2, bookShelf4.getBook("Great"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testToArrayList() {
		
		// Creating book object
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("88322","The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("98236","New Book", "New Person", "A great book", "Fiction");
				
				// Add Books to Shelf 
				bookShelf1.addBook(b1);
				bookShelf1.addBook(b2);
				bookShelf1.addBook(b3);
				bookShelf1.addBook(b4);
				
				// bookShelf1.toArrayList();
				
				// Mix up order and see if it still shows up 
				bookShelf2.addBook(b2);
				bookShelf2.addBook(b3);
				bookShelf2.addBook(b1);
				bookShelf2.addBook(b4);
				
				// Show the arrayList
				// bookShelf2.toArrayList();
				
		/*
		 * No longer need toArrayList test 
		 * Method is not in LocalBookShelf Class 
		 */
	}

	@Test
	void testSearchBook() {
		
		ArrayList<Book> searchShelf = new ArrayList<>();
		// Creating book object
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Spencer's Book", "Spencer Penrod", "The best book", "Non-Fiction");
		Book b3 = new Book("88322","Ashely's Book", "Ashley Bey", "The best book", "Si-Fi");
		Book b4 = new Book("98236","Nuo's Book", "Nuo Xu", "A great book", "Fiction");

		// Add Books to Shelf 
		bookShelf5.addBook(b1);
		bookShelf5.addBook(b2);
		bookShelf5.addBook(b3);
		bookShelf5.addBook(b4);
		
		// Search for a book
		bookShelf5.searchBook(SearchingField.TITLE, "Book", SortingCriteria.TITLE);
		
		bookShelf5.searchBook(SearchingField.AUTHOR, "Person", SortingCriteria.RATING);
		
		assertEquals(bookShelf5.searchBook(SearchingField.ISBN, "16783", SortingCriteria.AUTHOR), b1);
		assertEquals(bookShelf5.searchBook(SearchingField.ISBN, "84735", SortingCriteria.AUTHOR), b2);
	}
	
	
	@Test
	void testSortBy() {
		// Creating book object
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("88322","The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("98236","New Book", "New Person", "A great book", "Fiction");

		
		// Add Books to Shelf 
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		bookShelf1.addBook(b3);
		bookShelf1.addBook(b4);
		
		// test sorting books
		// since method is private can't test it
	}

}

