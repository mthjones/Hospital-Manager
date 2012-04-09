package hms.models;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import hms.util.*;

public class BedTest {
	private Integer firstUnusedID;
	
	@Test
	public void bedsWithSameNumberAreEqual() {
		try {
			Bed bed1 = Bed.find(getFirstUnusedID() - 1);
			Bed bed2 = Bed.find(getFirstUnusedID() - 1);
			
			assertEquals(bed1, bed2);
		} catch(SQLException sqle) {}
	}
	
	@Test
	public void bedsToStringReturnsTheNumber() {
		try {
			Bed bed1 = Bed.find(getFirstUnusedID() - 1);
			assertEquals(Integer.toString(bed1.getNumber()), bed1.toString());
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void findBed() {
		try {
			assertNull(Bed.find(getFirstUnusedID()));
			assertNotNull(Bed.find(getFirstUnusedID() - 1));
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void setOccupiedIsReflectedInDatabase() {
		try {
			Bed bed = Bed.find(getFirstUnusedID() - 1);
			boolean isOccupied = bed.getOccupied();
			
			bed.setOccupied(false);
			ResultSet bedResults = Database.getInstance().executeQuery("SELECT bedID FROM bed WHERE bedID = " + bed.getNumber());
			bedResults.next();
			assertEquals(bed.getOccupied() ? "Y" : "N", bedResults.getString("occupied"));
			
			bed.setOccupied(true);
			bedResults = Database.getInstance().executeQuery("SELECT bedID FROM bed WHERE bedID = " + bed.getNumber());
			bedResults.next();
			assertEquals(bed.getOccupied() ? "Y" : "N", bedResults.getString("occupied"));
			
			bed.setOccupied(isOccupied);
		} catch (SQLException sqle) {}
	}
	
	private Integer getFirstUnusedID() throws SQLException {
		if (firstUnusedID == null) {
			ResultSet bedResults = Database.getInstance().executeQuery("SELECT bedID FROM bed");
			Vector<Integer> allBedNumbers = new Vector<Integer>();
			while (bedResults.next()) {
				allBedNumbers.add(bedResults.getInt(1));
			}
			
			Collections.sort(allBedNumbers);
			
			int i;
			for (i = 0; i < (allBedNumbers.size() - 1); i++) {
				if (allBedNumbers.get(i+1) != allBedNumbers.get(i) + 1) {
					break;
				}
			}
			
			this.firstUnusedID = allBedNumbers.get(i) + 1;
		}
		return firstUnusedID;
	}
}