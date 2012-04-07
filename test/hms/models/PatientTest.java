/*package hms.models;

import java.sql.SQLException;
import java.util.Date;

import hms.models.Patient;
import hms.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {
	// @Test
	// public void test_createPatient() throws SQLException {
	// 	Patient patient = new Patient("123456789", 
	// 								  "John Q Public", 
	// 								  "123456789", 
	// 								  "john@example.org", 
	// 								  "M", 
	// 								  "Test Treatment", 
	// 								  "123 Abc Street", 
	// 								  new Date(), 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "",
	// 								  true,
	// 								  1,
	// 								  1,
	// 								  1,
	// 								  Priority.HIGH);
	// 	assertTrue(patient.create());
	// 	patient.delete();
	// }
	
	// @Test
	// public void test_cannotCreatePatientWithSameHealthcareNumber() throws SQLException {
	// 	Patient patient = new Patient("123456789", 
	// 								  "John Q Public", 
	// 								  "0123456789", 
	// 								  "john@example.org", 
	// 								  "M", 
	// 								  "Test Treatment", 
	// 								  "123 Abc Street", 
	// 								  new Date(), 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "", 
	// 								  "",
	// 								  true,
	// 								  1,
	// 								  1,
	// 								  1,
	// 								  Priority.HIGH);
	// 	assertTrue(patient.create());
	// 	Patient patient2 = new Patient("123456789", "Mary Sue", "9876543210", "mary@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "", true,1,1,1, Priority.HIGH);
	// 	boolean hadException = false;
	// 	try{
	// 	assertFalse(patient2.create());
	// 	}catch(Exception e){
	// 		hadException = true;
	// 	}
	// 	assertTrue("Exception should have been thrown", hadException);
	// 	patient.delete();
	// }
	
	// @Test
	// public void test_deletePatient() throws SQLException {
	// 	Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "", true,1,1,1, Priority.HIGH);
	// 	patient.create();
	// 	assertTrue(patient.delete());
	// }
	
	// @Test
	// public void test_cannotDeleteNonexistantPatient() throws SQLException {
	// 	Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "", true,1,1,1, Priority.HIGH);
	// 	assertFalse(patient.delete());
	// }
	
	// @Test
	// public void test_findPatient() throws SQLException {
	// 	Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "", true,1,1,1, Priority.HIGH);
	// 	patient.create();
	// 	assertNotNull(Patient.find("123456789"));
	// 	patient.delete();
	// }
	
	// @Test
	// public void test_serchForPatients_patientDoesntExist() throws SQLException{
	// 	assertNull(Patient.findByName(Patient.RESERVED_TEST_NAME));
	// }
	
	// @Test
	// public void test_serchForPatients_oneValidPatientExists() throws SQLException{
	// 	Patient patient = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "", true,1,1,1, Priority.HIGH);
	// 	patient.create();
	// 	Patient [] results = Patient.findByName("John Q Public");
	// 	assertNotNull(results);
	// 	assertEquals(1, results.length);
	// 	assertTrue(results[0].getName().equals("John Q Public"));
	// 	patient.delete();
	// }
	
	// @Test
	// public void test_serchForPatients_twoValidPatientsExist() throws SQLException{
	// 	Patient patient1 = new Patient("123456789", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "", true,1,1,1, Priority.HIGH);
	// 	Patient patient2 = new Patient("987654321", "John Q Public", "123456789", "john@example.org", "M", "Test Treatment", "123 Abc Street", new Date(), "", "", "", "", "", "", "", true,1,1,1, Priority.HIGH);
	// 	patient1.create();
	// 	patient2.create();
	// 	Patient [] results = Patient.findByName("John Q Public");
	// 	assertNotNull(results);
	// 	assertEquals(2, results.length);
	// 	assertTrue(results[0].getName().equals("John Q Public") && results[0].getHealthcareNumber().equals("123456789"));
	// 	assertTrue(results[1].getName().equals("John Q Public") && results[1].getHealthcareNumber().equals("987654321"));
	// 	patient1.delete();
	// 	patient2.delete();
	// }
}*/
