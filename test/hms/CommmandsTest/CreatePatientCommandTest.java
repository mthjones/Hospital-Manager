package hms.CommmandsTest;

import static org.junit.Assert.*;
import hms.HMSCommands.CreatePatientCommand;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreatePatientCommandTest {

	private CreatePatientCommand cpc;
	//private Patient patient;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	//	patient = new Patient(/*patient info*/);
	//	cpc = new CreatePatientCommand(patient);
	}

	@After
	public void tearDown() throws Exception {
		cpc = null;
	}

	@Test
	public void testCreatePatientCommand() {
		CreatePatientCommand cpc = new CreatePatientCommand();
		assertNotNull(cpc);
	}
	
	@Test
	public void testRun() {
		cpc.run();
	}

}
