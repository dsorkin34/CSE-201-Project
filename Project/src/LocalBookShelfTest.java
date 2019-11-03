import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;



class LocalBookShelfTest {

	LocalBookShelf bookShelf1 = new LocalBookShelf();
	LocalBookShelf bookShelf2 = new LocalBookShelf();
	
	// Test the add book method to see if book was added successfully 
	// Need to test for duplicates in the arryList of books 
	// Testing to see if number of books was incremented by one every time book is added
	
	@Test
	void testAddBook() {
		
		// Creating book object
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		
		// Test to see if books are added to the shelf
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		
		// Test to see if it does not allow duplicate books in a shelf 
		bookShelf2.addBook(b1);
		bookShelf2.addBook(b1);
		
		assertEquals(0, bookShelf2.size());

			/**
			 * Add a count or size variable to know how many books are in the array list
			 * Makes it easier to test the addBook method
			 */
	}

	@Test
	void testAddFromCSV() {
		bookShelf2.addFromCSV(null);

	}
	
	@Test
	void testRemoveBook() {
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("88322","The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("98236","New Book", "New Person", "A great book", "Fiction");

		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		assertEquals(2, bookShelf1.size());
		
		// remove a book and test to see size decreases by 1 
		bookShelf1.removeBook("key");
		assertEquals(1, bookShelf1.size());
		assertEquals(2, bookShelf1.size());
	}
	
	@Test 
	void testGetBook() {
		Book b1 = new Book("16783","Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("84735","Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		
		// Test get book from bookshelf
		assertEquals(bookShelf1.getBook("key"), b1);
		
		// Test to fail
		assertEquals(bookShelf1.getBook("Wrong Key"), b2);

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
				
				// Show in arrayList
				// bookShelf1.toArrayList();
				
				// Mix up order and see if it still shows up 
				bookShelf2.addBook(b2);
				bookShelf2.addBook(b3);
				bookShelf2.addBook(b1);
				bookShelf2.addBook(b4);
				
				// Show the arrayList
				// bookShelf2.toArrayList();
	}

	@Test
	void testSearchBook() {
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
		
		// Search for a book
		bookShelf1.searchBook(SearchingField.TITLE, "Book", SortingCriteria.TITLE);
		
		bookShelf1.searchBook(SearchingField.AUTHOR, "Person", SortingCriteria.RATING);
		
		assertEquals(bookShelf1.searchBook(SearchingField.ISBN, "16783", SortingCriteria.AUTHOR), b1);
		assertEquals(bookShelf1.searchBook(SearchingField.ISBN, "84735", SortingCriteria.AUTHOR), b1);
		
		
		
		// Test that fail
		bookShelf1.searchBook(SearchingField.AUTHOR, "Little", SortingCriteria.RATING);
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
