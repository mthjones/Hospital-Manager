package hms.models;

import hms.models.Patient;

import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {
	@Test
	public void test_getValueAtReturnsCorrectValue() {
		Patient patient = new Patient();
		assertEquals(patient.getValueAt(0, 0), 1);
	}
	
	@Test
	public void test_getColumnCountReturnsCorrectValue() {
		Patient patient = new Patient();
		assertEquals(patient.getColumnCount(), 1);
	}
	
	@Test
	public void test_getRowCountReturnsCorrectValue() {
		Patient patient = new Patient();
		assertEquals(patient.getRowCount(), 1);
	}
}