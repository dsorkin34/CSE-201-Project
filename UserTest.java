import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	// create a user object 
	User user1 = new User("username1", "12345678");
	User user2 = new User("username2", "87654321");
	User user3 = new User("username3", "1234567890");
	
	
	@Test
	void testGetName() {
	
		assertEquals("username1", user1.getName());
		assertEquals("username2", user2.getName());
		assertEquals("username3", user3.getName());
		
	}

	@Test
	void testGetPassword() {
		
		assertEquals("12345678", user1.getPassword());
		assertEquals("87654321", user2.getPassword());
		assertEquals("1234567890", user3.getPassword());
		
	}

}
