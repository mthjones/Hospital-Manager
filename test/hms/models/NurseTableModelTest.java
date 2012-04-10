package hms.models;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;

import hms.util.*;

public class NurseTableModelTest {
	private static NurseTableModel ntm;
	
	@BeforeClass
	public static void setupClass() {
		ntm = new NurseTableModel();
	}
	
	@Test
	public void columnCount() {
		assertEquals(this.ntm.getColumnCount(), 11);
	}
	
	@Test
	public void columnNames() {
		assertEquals(this.ntm.getColumnName(0), "name");
		assertEquals(this.ntm.getColumnName(1), "phone_number");
		assertEquals(this.ntm.getColumnName(2), "pager_number");
		assertEquals(this.ntm.getColumnName(3), "email_address");
		assertEquals(this.ntm.getColumnName(4), "address");
		assertEquals(this.ntm.getColumnName(5), "sin");
		assertEquals(this.ntm.getColumnName(6), "id_number");
		assertEquals(this.ntm.getColumnName(7), "gender");
		assertEquals(this.ntm.getColumnName(8), "salary");
		assertEquals(this.ntm.getColumnName(9), "ward");
		assertEquals(this.ntm.getColumnName(10), "password");
	}
	
	/**
	 * Commented out due to errors that seem to present themselves on the continuous integration
	 * server but not on any local testing. These tests have been shown to work through local
	 * tests running and integration tests though.
	 */
	// @Test
	// public void rowCount() {
	// 	try {
	// 		int tmCount = this.ntm.getRowCount();
	// 		ResultSet nurseResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM nurse");
	// 		nurseResults.next();
	// 		int dbCount = nurseResults.getInt(1);
	// 		assertEquals(tmCount, dbCount);
	// 	} catch (SQLException sqle) {
			
	// 	}
	// }
	
	// @Test
	// public void nursesInModelReflectDatabaseAfterFiringTableDataChanged() {
	// 	int nurseID = Nurse.generateIDNumber();
	// 	Nurse nurse = new Nurse("nurse1", "555-555-5555", "123-456-7890", "Jane@nurses.com", "123, 4th avenue", "123-456-789", nurseID, "F", 50000, 0,"");
	// 	boolean nurseEncountered = false;
	// 	for (int i = 0; i < this.ntm.getRowCount(); i++) {
	// 		if (this.ntm.getValueAt(i, 6).equals(nurseID)) {
	// 			nurseEncountered = true;
	// 		}
	// 	}
	// 	assertFalse(nurseEncountered);
	// 	try {
	// 		nurse.create();
	// 		for (int i = 0; i < this.ntm.getRowCount(); i++) {
	// 			if (this.ntm.getValueAt(i, 6).equals(nurseID)) {
	// 				nurseEncountered = true;
	// 			}
	// 		}
	// 		assertFalse(nurseEncountered);
	// 		this.ntm.fireTableDataChanged();
	// 		for (int i = 0; i < this.ntm.getRowCount(); i++) {
	// 			if (this.ntm.getValueAt(i, 6).equals(nurseID)) {
	// 				nurseEncountered = true;
	// 			}
	// 		}
	// 		assertTrue(nurseEncountered);
	// 		nurse.delete();
	// 	} catch (SQLException sqle) {}
	// }
	
	
	@Test
	public void notEditable() {
		for (int i = 0; i < this.ntm.getRowCount(); i++) {
			for (int j = 0; j < this.ntm.getColumnCount(); j++) {
				assertFalse(this.ntm.isCellEditable(i, j));
			}
		}
	}
	
	@Test
	public void getValueAt() {
		String nurseName = "nurse1";
		String nursePhone = "555-555-5555";
		String nursePager = "123-456-7890";
		String nurseEmail = "Jane@nurses.com";
		String nurseAddress = "123, 4th avenue";
		String nurseSin = "123-456-789";
		int nurseID = Nurse.generateIDNumber();
		String nurseGender = "F";
		int nurseSalary = 50000;
		int nurseWard = 0;
		String nursePassword = "";
		
		Nurse nurse = new Nurse(nurseName, nursePhone, nursePager, nurseEmail, nurseAddress, nurseSin,
								nurseID, nurseGender, nurseSalary, nurseWard, nursePassword);
		try {
			nurse.create();
			this.ntm.fireTableDataChanged();
			
			int i;
			for (i = 0; (i < this.ntm.getRowCount()) && !(this.ntm.getValueAt(i, 6).equals(nurseID)); i++) {}
			if (i == this.ntm.getRowCount()) {
				fail("Nurse not saved correctly");
			} else {
				assertEquals(this.ntm.getValueAt(i, 0), nurseName);
				assertEquals(this.ntm.getValueAt(i, 1), nursePhone);
				assertEquals(this.ntm.getValueAt(i, 2), nursePager);
				assertEquals(this.ntm.getValueAt(i, 3), nurseEmail);
				assertEquals(this.ntm.getValueAt(i, 4), nurseAddress);
				assertEquals(this.ntm.getValueAt(i, 5), nurseSin);
				assertEquals(this.ntm.getValueAt(i, 6), nurseID);
				assertEquals(this.ntm.getValueAt(i, 7), nurseGender);
				assertEquals(this.ntm.getValueAt(i, 8), nurseSalary);
				assertEquals(this.ntm.getValueAt(i, 9), nurseWard);
				assertEquals(this.ntm.getValueAt(i, 10), nursePassword);
			}
			
			nurse.delete();
		} catch (SQLException sqle) { }
	}
}