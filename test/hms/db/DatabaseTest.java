package hms.db;

import hms.db.Database;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;

public class DatabaseTest {
	@Test
	public void test_onlyOneInstance() throws SQLException {
		assertSame(Database.getInstance(), Database.getInstance());
	}
}