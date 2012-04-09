package hms.models;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;

import hms.util.*;

public class PatientTableModelTest {
	private static PatientTableModel ptm;
	
	@BeforeClass
	public static void setupClass() {
		ptm = new PatientTableModel();
	}
	
	@Test
	public void columnCount() {
		assertEquals(this.ptm.getColumnCount(), 6);
	}
	
	@Test
	public void columnNames() {
		assertEquals(this.ptm.getColumnName(0), "Health #");
		assertEquals(this.ptm.getColumnName(1), "Name");
		assertEquals(this.ptm.getColumnName(2), "Gender");
		assertEquals(this.ptm.getColumnName(3), "Birthdate");
		assertEquals(this.ptm.getColumnName(4), "In Hospital?");
		assertEquals(this.ptm.getColumnName(5), "Priority");
	}
	
	@Test
	public void columnClasses() {
		for (int i = 0; i < this.ptm.getColumnCount(); i++) {
			if (i == 5) {
				assertEquals(this.ptm.getColumnClass(i), Priority.class);
			} else {
				assertEquals(this.ptm.getColumnClass(i), Object.class);
			}
		}
	}
	
	@Test
	public void rowCount() {
		try {
			ResultSet patientResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM patient");
			patientResults.next();
			int patientCount = patientResults.getInt(1);
			assertEquals(this.ptm.getRowCount(), patientCount);
		} catch (SQLException sqle) {
			
		}
	}
	
	@Test
	public void notEditable() {
		for (int i = 0; i < this.ptm.getRowCount(); i++) {
			for (int j = 0; j < this.ptm.getColumnCount(); j++) {
				assertFalse(this.ptm.isCellEditable(i, j));
			}
		}
	}
	
	@Test
	public void patientsInModelReflectDatabaseAfterFiringTableDataChanged() {
		String patientID = "74355395739";
		Patient patient = new Patient(patientID, 
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
		boolean patientEncountered = false;
		for (int i = 0; i < this.ptm.getRowCount(); i++) {
			if (this.ptm.getValueAt(i, 0).equals(patientID)) {
				patientEncountered = true;
			}
		}
		assertFalse(patientEncountered);
		try {
			patient.create();
			for (int i = 0; i < this.ptm.getRowCount(); i++) {
				if (this.ptm.getValueAt(i, 0).equals(patientID)) {
					patientEncountered = true;
				}
			}
			assertFalse(patientEncountered);
			this.ptm.fireTableDataChanged();
			for (int i = 0; i < this.ptm.getRowCount(); i++) {
				if (this.ptm.getValueAt(i, 0).equals(patientID)) {
					patientEncountered = true;
				}
			}
			assertTrue(patientEncountered);
			patient.delete();
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void getValueAt() {
		String patientID = "79847239";
		String patientName = "John Q Public";
		String patientGender = "M";
		Date patientBirthdate = new Date();
		boolean patientInHospital = true;
		Priority patientPriority = Priority.HIGH;
		Patient patient = new Patient(patientID, 
									  patientName, 
									  "0123456789", 
									  "john@example.org", 
									  patientGender, 
									  "Test Treatment", 
									  "123 Abc Street", 
									  patientBirthdate, 
									  "", 
									  "", 
									  "", 
									  "", 
									  "", 
									  "", 
									  "",
									  patientInHospital,
									  Ward.find(1),
									  Room.find(3),
									  Bed.find(12),
									  patientPriority);
		try {
			patient.create();
			this.ptm.fireTableDataChanged();
			
			int i;
			for (i = 0; (i < this.ptm.getRowCount()) && !(this.ptm.getValueAt(i, 0).equals(patientID)); i++) {}
			if (i == this.ptm.getRowCount()) {
				fail("Patient not saved correctly");
			} else {
				assertEquals(this.ptm.getValueAt(i, 0), patientID);
				assertEquals(this.ptm.getValueAt(i, 1), patientName);
				assertEquals(this.ptm.getValueAt(i, 2), patientGender);
				assertEquals(this.ptm.getValueAt(i, 3), patientBirthdate);
				assertEquals(this.ptm.getValueAt(i, 4), patientInHospital);
				assertEquals(this.ptm.getValueAt(i, 5), patientPriority);
			}
			
			patient.delete();
		} catch (SQLException sqle) { }
	}
}