package hms.controllers;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;
import static org.junit.Assert.*;
import hms.controllers.PatientManager;
import hms.util.Priority;
import hms.models.Patient;
import java.sql.SQLException;

public class PatientManagerTest {
	
	private static Patient testPatient;
	private static final String NUMBER = "314159";
	
	@BeforeClass
	public static void setUpBeforeClass() {
		testPatient = new Patient(NUMBER, 
								  "test test", 
								  "1234567890", 
								  "test@test.com",
								  "m", 
								  "", 
								  "", 
								  new Date( Integer.parseInt("1991")-1900 , Integer.parseInt("1")-1 , Integer.parseInt("21") ), 
								  "test", 
								  "test", 
								  "test", 
								  "test", 
								  "test", 
								  "1234567890", 
								  "test@test",
								  true,
								  1,
								  1,
								  1, 
								  Priority.HIGH);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		try {
			testPatient.delete();
		} catch (SQLException e) {
		}
		
	}
}
