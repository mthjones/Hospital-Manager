package hms.models;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

import hms.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {
	private Patient patient;
	private String patientID;
	
	@Before
	public void setup() {
		this.patientID = "2187312798";
		this.patient = new Patient(patientID, 
								   "John Q Public", 
								   "0123456789", 
								   "john@example.org", 
								   "M", 
								   "Test Treatment", 
								   "123 Abc Street", 
								   new Date(), 
								   "", 
								   "", 
								   "", 
								   "", 
								   "", 
								   "", 
								   "",
								   true,
								   Ward.find(1),
								   Room.find(3),
								   Bed.find(12),
								   Priority.HIGH);
	}
	
	@After
	public void teardown() {
		// Just in case a patient.delete() statement was forgotten
		try {
			this.patient.delete();
		} catch (SQLException sqle) { }
	}
	
	@Test
	public void createPatient() {
		try {
			ResultSet patientResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient WHERE healthcare_number = " + patientID);
			patientResults.next();
			int previousWithHealthNum = patientResults.getInt(1);
			
			assertTrue(patient.create());
			
			patientResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient WHERE healthcare_number = " + patientID);
			patientResults.next();
			assertEquals(previousWithHealthNum + 1, patientResults.getInt(1));
			
			patient.delete();
		} catch (SQLException sqle) { }
	}
	
	@Test
	public void deletePatient() {
		try {
			patient.create();
			
			ResultSet patientResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient WHERE healthcare_number = " + patientID);
			patientResults.next();
			int previousWithHealthNum = patientResults.getInt(1);
			
			assertTrue(patient.delete());
			
			patientResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient WHERE healthcare_number = " + patientID);
			patientResults.next();
			assertEquals(previousWithHealthNum - 1, patientResults.getInt(1));
		} catch (SQLException sqle) { }
	}
	
	@Test
	public void patientBedAvailabilityChangedOnCreationAndDeletion() {
		try {
			assertFalse(patient.getBed().getOccupied());
			
			patient.create();
			
			assertTrue(patient.getBed().getOccupied());
			
			patient.delete();
			
			assertFalse(patient.getBed().getOccupied());
		} catch (SQLException sqle) { }
	}
	
	@Test
	public void nonexistantPatientDeletionHasNoEffect() {
		try {
			ResultSet patientResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient");
			patientResults.next();
			int rowCount = patientResults.getInt(1);
			
			assertFalse(patient.delete());
			
			patientResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient");
			patientResults.next();
			assertEquals(rowCount, patientResults.getInt(1));
		} catch (SQLException sqle) { }
	}
	
	@Test
	public void findPatient() {
		try {
			assertNull(Patient.find(patientID));
			patient.create();
			assertNotNull(Patient.find(patientID));
			patient.delete();
		} catch (SQLException sqle) { }
	}
}
