package hms.models;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import hms.util.*;

public class NurseTest {
	private Nurse nurse;
	private int nurseID;
	
	@Before
	public void setup() {
		this.nurseID = Nurse.generateIDNumber();
		this.nurse = new Nurse("nurse1", "555-555-5555", "123-456-7890", "Jane@nurses.com", "123, 4th avenue", "123-456-789", nurseID, "F", 50000, 0, "");
	}
	
	@After
	public void teardown() {
		// Just in case a nurse.delete() statement was forgotten
		try {
			this.nurse.delete();
		} catch (SQLException sqle) { }
	}

	@Test
	public void createNurse() {
		try {
			ResultSet nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse WHERE id_number = " + nurseID);
			nurseResults.next();
			int previousWithID = nurseResults.getInt(1);
			
			assertTrue("Nurse should be created", nurse.create());
			
			nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse WHERE id_number = " + nurseID);
			nurseResults.next();
			assertEquals(previousWithID + 1, nurseResults.getInt(1));
			
			nurse.delete();
		} catch (SQLException sqle) { }
	}
	
	@Test
	public void cannotCreateNurseWithSameIDNumber() {
		try {
			assertTrue("Nurse should be created", nurse.create());
			
			ResultSet nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse WHERE id_number = " + nurseID);
			nurseResults.next();
			int previousWithID = nurseResults.getInt(1);
			
			Nurse nurse2 = new Nurse("nurse3", "555-555-5555", "123-456-7890", "John@nurses.com", "123, 4th avenue", "123-456-789", nurseID, "M", 50000, 0,"");
			assertFalse("Duplicate nurse should not be created", nurse2.create());
			
			nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse WHERE id_number = " + nurseID);
			nurseResults.next();
			assertEquals(previousWithID, nurseResults.getInt(1));
			
			nurse.delete();
		} catch (SQLException sqle) { }
	}
	
	@Test
	public void deleteNurse() {
		try {
			nurse.create();
			
			ResultSet nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse WHERE id_number = " + nurseID);
			nurseResults.next();
			int previousWithID = nurseResults.getInt(1);
			
			assertTrue(nurse.delete());
			
			nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse WHERE id_number = " + nurseID);
			nurseResults.next();
			assertEquals(previousWithID - 1, nurseResults.getInt(1));
		} catch(SQLException e) {
			fail("SQLException occurred.");
		}
	}
	
	@Test
	public void nonexistantNurseDeletionHasNoEffect() {
		try{
			ResultSet nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse");
			nurseResults.next();
			int previousWithID = nurseResults.getInt(1);
			
			assertFalse(nurse.delete());
			
			nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse");
			nurseResults.next();
			assertEquals(previousWithID, nurseResults.getInt(1));
		} catch(SQLException e) {
			fail("SQLException occured.");
		}
	}
	
	@Test
	public void findNurse() {
		try{
			assertNull(Nurse.find(nurseID));
			nurse.create();
			assertNotNull(Nurse.find(nurseID));
			nurse.delete();
		} catch(SQLException e) {
			fail("SQLException occurred.");
		}
	}
	
	@Test
	public void idGeneratorProducesUniqueResult() {
		int new_id = Nurse.generateIDNumber();
		try {
			ResultSet nurseResults = Database.getInstance().executeQuery("SELECT id_number FROM nurse");
			while (nurseResults.next()) {
				assertFalse(new_id == nurseResults.getInt("id_number"));
			}
		} catch (SQLException sqle) {}
	}
}
