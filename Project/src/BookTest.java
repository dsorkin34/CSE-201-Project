
import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	Book b1 = new Book("92883", "title1", "name here", "descriptions here", "genreListed");
	Book b2 = new Book("92729", "bookName", "testing name", "cool description", "theGenre");

	@Test
	public void testToString() {
		assertEquals(b1.toString(),"Book\nISBN   = 92883\ntitle       = title1\nname        = name here\ndescription = descriptions here\ngenre       = genreListed\n");
		assertEquals(b2.toString(), "Book\nISBN   = 92729\ntitle       = bookName\nname        = testing name\ndescription = cool description\ngenre       = theGenre\n");
	}

	@Test
	public void testClone() {
		assertEquals(b1.clone(), b1);
		assertEquals(b2.clone(), b2);
		assertFalse(b1.clone() == b1);
	}

	@Test
	public void testEquals() {
		Book b3 = b2;
		assertFalse(b2.equals(b1));
		assertTrue(b3.equals(b2));

	}

	@Test
	public void testGetISBN() {
		assertEquals(b2.getISBN(), "92729");
		assertEquals(b1.getISBN(), "92883");
		assertFalse(b2.getISBN() == "92883");
	}

	@Test
	public void testGetTitle() {
		assertEquals(b1.getTitle(), "title1");
		assertEquals(b2.getTitle(), "bookName");
	}

	@Test
	public void testGetAuthor() {
		assertEquals(b1.getAuthor(), "name here");
		assertEquals(b2.getAuthor(), "testing name");

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

