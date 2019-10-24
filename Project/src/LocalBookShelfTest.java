import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class LocalBookShelfTest {

	
	LocalBookShelf bookShelf1 = new LocalBookShelf();
	LocalBookShelf bookShelf2 = new LocalBookShelf();
	
	// Test the add book method to see if book was added successfully 
	// Need to test for duplicates in the arryList of books 
	// Testing to see if number of books was incremented by one everytime book is added
	
	@Test
	void testAddBook() {
		
		// Creating book object
		Book b1 = new Book("Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		
		// Test to see if books are added to the shelf
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		
		bookShelf1.toArrayList();
		
		// Test to see if it does not allow duplicate books in a shelf 
		bookShelf2.addBook(b1);
		bookShelf2.addBook(b1);
		
		bookShelf2.toArrayList();
		//assertEquals(0, bookShelf2.getCurrentSize);
		
		
		 
			/**
			 * Add a count or size variable to know how many books are in the array list
			 * Makes it easier to test the addBook method
			 */
			

		 /**
		  * Possibly adding an isEmpty method for some extreme case we don't have any books in the database
		  */
		    // assertTrue(BookShelf.isEmpty());
	}

	
	// 
	
	@Test
	void testToArrayList() {
		
		// Creating book object
				Book b1 = new Book("Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
				Book b2 = new Book("Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
				Book b3 = new Book("The Book", "A Person", "The best book", "Si-Fi");
				Book b4 = new Book("New Book", "New Person", "A great book", "Fiction");
				
				// Add Books to Shelf 
				bookShelf1.addBook(b1);
				bookShelf1.addBook(b2);
				bookShelf1.addBook(b3);
				bookShelf1.addBook(b4);
				
				// Show in arrayList
				bookShelf1.toArrayList();
				
				// Mix up order and see if it still shows up 
				bookShelf2.addBook(b2);
				bookShelf2.addBook(b3);
				bookShelf2.addBook(b1);
				bookShelf2.addBook(b4);
				
				// Show the arrayList
				bookShelf2.toArrayList();
		
	}

	
	@Test
	void testSearchBookStringStringSortingCriteria() {
		// Creating book object
		Book b1 = new Book("Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("New Book", "New Person", "A great book", "Fiction");
		
		// Add Books to Shelf 
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		bookShelf1.addBook(b3);
		bookShelf1.addBook(b4);	
	}

	@Test
	void testSearchBookStringString() {
		// Creating book object
		Book b1 = new Book("Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("New Book", "New Person", "A great book", "Fiction");
		
		// Add Books to Shelf 
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		bookShelf1.addBook(b3);
		bookShelf1.addBook(b4);
	}

	@Test
	void testSearchBookStringSortingCriteria() {
		// Creating book object
		Book b1 = new Book("Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("New Book", "New Person", "A great book", "Fiction");
		
		// Add Books to Shelf 
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		bookShelf1.addBook(b3);
		bookShelf1.addBook(b4);
	}

	@Test
	void testSearchBookString() {
		// Creating book object
		Book b1 = new Book("Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("New Book", "New Person", "A great book", "Fiction");
		
		// Add Books to Shelf 
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		bookShelf1.addBook(b3);
		bookShelf1.addBook(b4);
	}

	
	
	// Testing the sorting by title method to see if when choosing to sort by title, will arrayList of books be in sorted order
	// Test to see if lower case and upper case effect the sort 
	// 
	
	@Test
	void testSortByTitle() {
		// Creating book object
		Book b1 = new Book("Cliff's Book", "Cliff Wrighter", "A great book by the one and only", "Fiction");
		Book b2 = new Book("Great Book", "Clifford Wrighter", "The best book", "Non-Fiction");
		Book b3 = new Book("The Book", "A Person", "The best book", "Si-Fi");
		Book b4 = new Book("New Book", "New Person", "A great book", "Fiction");
		
		// Add Books to Shelf 
		bookShelf1.addBook(b1);
		bookShelf1.addBook(b2);
		bookShelf1.addBook(b3);
		bookShelf1.addBook(b4);
	}

}
