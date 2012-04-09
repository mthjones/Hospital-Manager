package hms.models;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import hms.util.*;

public class UserTest {
	private User user;
	private String username;
	private String password;
	
	@Before
	public void setup() {
		this.username = "testuser";
		this.password = "testpass";
		this.user = new User(username, password);
	}
	
	@After
	public void teardown() {
		try {
			this.user.delete();
		} catch (SQLException sqle) { }
	}
	
	@Test 
	public void createUser() {
		try {
			ResultSet userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = " + username);
			userResults.next();
			int previousWithUsername = userResults.getInt(1);
			
			assertTrue(user.create());
			
			userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = " + username);
			userResults.next();
			assertEquals(previousWithUsername + 1, userResults.getInt(1));
			
			user.delete();
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void cannotCreateUserWithSameUsername() {
		try {
			user.create();
			
			ResultSet userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = " + username);
			userResults.next();
			int previousWithUsername = userResults.getInt(1);
			
			User user2 = new User("testuser", "testpass2");
			assertFalse(user2.create());
			
			userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = " + username);
			userResults.next();
			assertEquals(previousWithUsername, userResults.getInt(1));
			
			user.delete();
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void deleteUser() {
		try {
			user.create();
			
			ResultSet userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = " + username);
			userResults.next();
			int previousWithUsername = userResults.getInt(1);
			
			assertTrue(user.delete());
			
			userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user WHERE username = " + username);
			userResults.next();
			assertEquals(previousWithUsername - 1, userResults.getInt(1));
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void nonexistantUserDeletionHasNoEffect() {
		try {
			ResultSet userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user");
			userResults.next();
			int previousWithUsername = userResults.getInt(1);
			
			assertFalse(user.delete());
			
			userResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM user");
			userResults.next();
			assertEquals(previousWithUsername, userResults.getInt(1));
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void authenticateUser() {
		try {
			user.create();
			assertTrue("right user, right pass", User.authenticate("testuser", "testpass"));
			assertFalse("wrong user, right pass", User.authenticate("wronguser", "testpass"));
			assertFalse("right user, wrong pass", User.authenticate("testuser", "wrongpass"));
			assertFalse("wrong user, wrong pass", User.authenticate("wronguser", "wrongpass"));
			user.delete();
		} catch (SQLException sqle) {}
	}
}