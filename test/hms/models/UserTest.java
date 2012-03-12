package hms.models;

import java.sql.SQLException;
import java.util.Arrays;

import hms.models.User;

import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {
	@Test 
	public void test_createUser() throws SQLException {
		User user = new User("testuser", "testpass");
		user.toString();
		assertTrue(user.create());
		user.delete();
	}
	
	@Test
	public void test_cannotCreateUserWithSameUsername() throws SQLException {
		User user = new User("testuser", "testpass");
		user.create();
		User user2 = new User("testuser", "testpass2");
		assertFalse(user2.create());
		user.delete();
	}
	
	@Test
	public void test_deleteUser() throws SQLException {
		User user = new User("testuser", "testpass");
		user.create();
		assertTrue(user.delete());
	}
	
	@Test
	public void test_cannotDeleteNonexistantUser() throws SQLException {
		User user = new User("testuser", "testpass");
		assertFalse(user.delete());
	}
	
	@Test
	public void test_authenticateUser() throws SQLException {
		User user = new User("testuser2", "testpass");
		user.create();
		assertTrue("right user, right pass", User.authenticate("testuser2", "testpass"));
		assertFalse("wrong user, right pass", User.authenticate("wronguser", "testpass"));
		assertFalse("right user, wrong pass", User.authenticate("testuser2", "wrongpass"));
		assertFalse("wrong user, wrong pass", User.authenticate("wronguser", "wrongpass"));
		user.delete();
	}
}