package hms.models;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NurseTest {

	/*@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}*/

	@Test
	public void test_createNurse() throws SQLException{
		Nurse nurse = new Nurse("nurse1", "555-555-5555", "123-456-7890", "Jane@nurses.com", "123, 4th avenue", "123-456-789", 1234, "f", 50000, 0,"");
		assertTrue("Nurse should be created", nurse.create());
		nurse.delete();
	}
	
	@Test
	public void test_cannotCreateNurseWithSameIDNumber() throws SQLException{
		Nurse nurse1 = new Nurse("nurse2", "555-555-5555", "123-456-7890", "Jane@nurses.com", "123, 4th avenue", "123-456-789", 1234, "f", 50000, 0,"");
		assertTrue("Nurse should be created", nurse1.create());
		Nurse nurse2 = new Nurse("nurse3", "555-555-5555", "123-456-7890", "John@nurses.com", "123, 4th avenue", "123-456-789", 1234, "m", 50000, 0,"");
		assertFalse("Duplicate nurse should not be created", nurse2.create());
		nurse1.delete();
	}
	
	@Test
	public void test_deleteNurse() {
		try {
			Nurse nurse = new Nurse("nurse1", "555-555-5555", "123-456-7890", "Jane@nurses.com", "123, 4th avenue", "123-456-789", 1111, "f", 50000, 0,"");
			nurse.create();
			assertTrue(nurse.delete());
		} catch(SQLException e) {
			fail("SQLException occurred.");
		}
	}
	
	@Test
	public void test_cannotDeleteNonexistantNurse(){
		try{
			Nurse nurse = new Nurse("Jane Doe", "123-456-7890", "555-5555", "test@test.com", "123, 4th avenue", "123-456-7890", 2222, "f", 100000, 0,"");
			assertFalse(nurse.delete());
		} catch(SQLException e) {
			fail("SQLException occured.");
		}
	}
	
	@Test
	public void test_findNurse() {
		try{
			Nurse nurse = new Nurse("Jane Doe", "123-456-7890", "555-5555", "test@test.com", "123, 4th avenue", "123-456-7890", 2222, "f", 100000, 0,"");
			nurse.create();
			assertNotNull(Nurse.find(2222));
			nurse.delete();
		} catch(SQLException e) {
			fail("SQLException occurred.");
		}
	}

}
