
import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	Book b1 = new Book("title1", "name here", "descriptions here", "genreListed");
	Book b2 = new Book("bookName", "testing name", "cool description", "theGenre");
	
	@Test
	public void testBook() {
		// String title, String name, String description, String genre
		assertEquals(b1.toString(), "Book [title=title1, Name = name here, description = descriptions here, genre = genreListed]");
		assertEquals(b2.toString(), "Book [title=bookName, Name = testing name, description = cool description, genre = theGenre]");
	}
	
	@Test
	public void testGetTitle() {
		assertEquals(b1.getTitle(), "title1");
		assertEquals(b2.getTitle(), "bookName");
	}
	
	@Test
	public void testGetName() {
		assertEquals(b1.getName(), "name here");
		assertEquals(b2.getName(), "testing name");
	}
	
	@Test
	public void testGetfName() {
		assertEquals(b1.getfName(), "name");
		assertEquals(b2.getfName(), "testing");
	}
	
	@Test
	public void testGetlName() {
		assertEquals(b1.getlName(), "here");
		assertEquals(b2.getlName(), "name");
	}
	
	@Test
	public void testGetDescription() {
		assertEquals(b1.getDescription(), "descriptions here");
		assertEquals(b2.getDescription(), "cool description");
	}
	
	@Test
	public void testGetGenre() {
		assertEquals(b1.getGenre(), "genreListed");
		assertEquals(b2.getGenre(), "theGenre");
	}

}

