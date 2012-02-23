package hms.models;

import java.sql.SQLException;
import java.util.Date;

import hms.models.Patient;

import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {
	@Test
	public void test_createPatient() throws SQLException {
		Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "");
		assertTrue(patient.create());
		patient.delete();
	}
	
	@Test
	public void test_cannotCreatePatientWithSameHealthcareNumber() throws SQLException {
		Patient patient = new Patient("123456789", "John Q Public", "0123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "");
		assertTrue(patient.create());
		Patient patient2 = new Patient("123456789", "Mary Sue", "9876543210", "mary@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "");
		assertFalse(patient2.create());
		patient.delete();
	}
	
	@Test
	public void test_deletePatient() throws SQLException {
		Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "");
		patient.create();
		assertTrue(patient.delete());
	}
	
	@Test
	public void test_cannotDeleteNonexistantPatient() throws SQLException {
		Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "");
		assertFalse(patient.delete());
	}
	
	@Test
	public void test_findPatient() throws SQLException {
		Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "");
		patient.create();
		assertNotNull(Patient.find("123456789"));
		patient.delete();
	}
}