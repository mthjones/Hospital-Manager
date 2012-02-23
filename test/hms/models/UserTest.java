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
		assertTrue(user.save());
		user.delete();
	}
	
	@Test
	public void test_deleteUser() throws SQLException {
		User user = new User("testuser", "testpass");
		user.save();
		assertTrue(user.delete());
	}
	
	@Test
	public void test_authenticateUser() throws SQLException {
		User user = new User("testuser2", "testpass");
		user.save();
		assertTrue("right user, right pass", User.authenticate("testuser2", "testpass"));
		assertFalse("wrong user, right pass", User.authenticate("wronguser", "testpass"));
		assertFalse("right user, wrong pass", User.authenticate("testuser2", "wrongpass"));
		assertFalse("wrong user, wrong pass", User.authenticate("wronguser", "wrongpass"));
		user.delete();
	}
}