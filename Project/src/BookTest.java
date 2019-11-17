
import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class BookTest {

	Book b1 = new Book("92883", "title1", "name here", "descriptions here", "genreListed");
	Book b2 = new Book("92729", "bookName", "testing name", "cool description", "theGenre");

	@Test
	public void testToString() {
		b1.addRating(3.0);
		b2.addRating(2.0);
		b2.addRating(1.0);
		b1.addComment("userName", "content");
		b2.addComment("userName", "content");
		
		assertEquals(b1.toString(),
		"Book\n" + 
		"ISBN        = " + "92883"                 + "\n"+
		"title       = " + "title1"                + "\n"+
		"name        = " + "name here"             + "\n"+
		"description = " + "descriptions here"     + "\n"+
		"genre       = " + "genreListed"           + "\n"+
		"Rating      = " + "3.0"                   + "\n"+
		"Rating Count= " + "1"                     + "\n"+
		"Comments    = " + "{userName=content}"    + "\n");
		
		assertEquals(b2.toString(),
		"Book\n" + 
		"ISBN        = " + "92729"                 + "\n"+
		"title       = " + "bookName"              + "\n"+
		"name        = " + "testing name"          + "\n"+
		"description = " + "cool description"      + "\n"+
		"genre       = " + "theGenre"              + "\n"+
		"Rating      = " + "1.5"                   + "\n"+
		"Rating Count= " + "2"                     + "\n"+
		"Comments    = " + "{userName=content}"    + "\n");
		
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
	
	@Test
	public void testGetRating() {
		b1.addRating(3.0);
		b2.addRating(2.0);
		b2.addRating(1.0);
		assertEquals(b1.getRating(), 3.0, 0);
		assertEquals(b2.getRating(), 1.5, 0);
	}
	
	@Test
	public void testAddRating() {
		b1.addRating(3.0);
		b1.addRating(1.0);
		b2.addRating(2.0);
		b2.addRating(2.0);
		b2.addRating(5.0);
		assertEquals(b1.getRating(), 2.0, 0);
		assertEquals(b2.getRating(), 3.0, 0);
	}
	
	@Test
	public void testGetComments() {
		assertEquals(b1.getComments().toString(), "{}");
		assertEquals(b2.getComments().toString(), "{}");
		b1.addComment("userName", "content");
		b2.addComment("userName2", "content2");
		assertEquals(b1.getComments().toString(), "{userName=content}");
		assertEquals(b2.getComments().toString(), "{userName2=content2}");
		b1.addComment("userName3", "content3");
		assertEquals(b1.getComments().toString(), "{userName=content, userName3=content3}");
		assertNotEquals(b2.getComments().toString(), "{userName=content, userName3=content3}");
	}
	
	@Test
	public void testAddComments() {
		assertTrue(b1.addComment("userName", "content"));
		assertTrue(b2.addComment("userName2", "content2"));
	}
}
