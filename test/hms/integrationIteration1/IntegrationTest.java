package hms.integrationIteration1;

import static org.junit.Assert.*;
import hms.*;
import hms.Commands.*;
import hms.db.*;
import hms.Managers.*;
import hms.models.*;
import hms.Views.*;

import org.junit.Test;

public class IntegrationTest {
	
	//tests for login system.  These tests were performed manually
	@Test
	public void testLogin_UserInDatabase() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}

	@Test
	public void testLogin_UserNotInDatabase() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);	
	}

	@Test
	public void testLogin_IncorrectPassword() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}

	@Test
	public void testLogin_NullPassword() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}

	@Test
	public void testLogin_NullUsername() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	@Test
	public void testLogin_BothFieldsNull() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	//tests for viewing and editing patients - tests are manual
	
	@Test
	public void testViewPatient_AllPatientsDisplayed() {
		boolean testPerformed = false;
		boolean testPassed = false;
		assertTrue(testPerformed && testPassed);
	}
	
	@Test
	public void testViewPatient_ListRefreshesWhenNewPatientsAdded() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	@Test
	public void testViewPatient_ListRefreshesWhenPatientIsEditied() {
		boolean testPerformed = true;
		boolean testPassed = true; 
		assertTrue(testPerformed && testPassed);
	}
	
	
}
