package hms.models;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;

import hms.util.*;

public class WardTest {
	@Test
	public void wardsWithSameIDAreEqual() {
		Ward ward1 = Ward.find(0);
		Ward ward2 = Ward.find(0);
		
		assertEquals(ward1, ward2);
	}
	
	@Test
	public void wardsToStringReturnsTheName() {
		Ward ward1 = Ward.find(0);
		
		assertEquals(ward1.getName(), ward1.toString());
	}
	
	@Test
	public void findWard() {
		int unusedID = getFirstUnusedID();
		
		assertNull(Ward.find(unusedID));
		assertNotNull(Ward.find(unusedID - 1));
	}
	
	@Test
	public void getAllWards() {
		try {
			ResultSet wardResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM ward");
			wardResults.next();
			int wardCount = wardResults.getInt(1);
			
			assertEquals(wardCount, Ward.getAllWards().size());
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void getAllRooms() {
		Ward testWard = Ward.find(getFirstUnusedID() - 1);
		
		try {
			ResultSet roomResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM room WHERE ward = " + testWard.getID());
			roomResults.next();
			int roomCountInWard = roomResults.getInt(1);
		
			Vector<Room> roomsInWard = testWard.getRooms();
			assertEquals(roomCountInWard, roomsInWard.size());
			for (int i = 0; i < roomCountInWard; i++) {
				assertEquals(roomsInWard.get(i).getWard(), testWard);
			}
		} catch (SQLException sqle) { }
	}
	
	private int getFirstUnusedID() {
		Vector<Ward> allWards = Ward.getAllWards();
		Collections.sort(allWards, new Comparator<Ward>() {
			@Override
			public int compare(Ward a, Ward b) {
				return ((Integer)a.getID()).compareTo((Integer)b.getID());
			}
		});
		
		int i;
		for (i = 0; i < (allWards.size() - 1); i++) {
			if (allWards.get(i+1).getID() != allWards.get(i).getID() + 1) {
				return allWards.get(i).getID() + 1;
			}
		}
		
		return allWards.get(i).getID() + 1;
	}
}