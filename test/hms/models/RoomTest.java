package hms.models;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import hms.util.*;

public class RoomTest {
	private Integer firstUnusedID;
	
	@Test
	public void roomsWithSameIDAreEqual() {
		try {
			Room room1 = Room.find(getFirstUnusedID() - 1);
			Room room2 = Room.find(getFirstUnusedID() - 1);		
			assertEquals(room1, room2);
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void roomsToStringReturnsTheNumber() {
		try {
			Room room1 = Room.find(getFirstUnusedID() - 1);
			
			assertEquals(Integer.toString(room1.getNumber()), room1.toString());
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void findRoom() {
		try {
			assertNull("unused room id should return null", Room.find(getFirstUnusedID()));
			assertNotNull("used room id should return room object", Room.find(getFirstUnusedID() - 1));
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void getAllBeds() {
		try {
			Room testRoom = Room.find(getFirstUnusedID() - 1);
			ResultSet bedResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM bed WHERE room = " + testRoom.getNumber());
			bedResults.next();
			int bedCountInRoom = bedResults.getInt(1);
			
			Vector<Bed> bedsInRoom = testRoom.getBeds();
			assertEquals(bedCountInRoom, bedsInRoom.size());
			for (int i = 0; i < bedCountInRoom; i++) {
				assertEquals(bedsInRoom.get(i).getRoom(), testRoom);
			}
		} catch (SQLException sqle) {}
	}
	
	@Test
	public void getAvailableBeds() {
		try {
			Room testRoom = Room.find(getFirstUnusedID() - 1);
			Vector<Bed> bedsInRoom = testRoom.getBeds();
			boolean bedOccupied = bedsInRoom.get(0).getOccupied();
			bedsInRoom.get(0).setOccupied(true);
			
			ResultSet bedResults = Database.getInstance().executeQuery("SELECT COUNT(*) FROM bed WHERE room = " + testRoom.getNumber() + " AND occupied = 'N'");
			bedResults.next();
			int availableBedCountInRoom = bedResults.getInt(1);
			
			Vector<Bed> availableBedsInRoom = testRoom.getAvailableBeds();
			
			assertEquals(availableBedCountInRoom, availableBedsInRoom.size());
			
			for (int i = 0; i < availableBedCountInRoom; i++) {
				assertTrue(bedsInRoom.contains(availableBedsInRoom.get(i)));
				assertEquals(availableBedsInRoom.get(i).getRoom(), testRoom);
			}
			
			bedsInRoom.get(0).setOccupied(bedOccupied);
		} catch (SQLException sqle) {}
	}
	
	private Integer getFirstUnusedID() throws SQLException {
		if (firstUnusedID == null) {
			ResultSet roomResults = Database.getInstance().executeQuery("SELECT roomID FROM room");
			Vector<Integer> allRoomNumbers = new Vector<Integer>();
			while (roomResults.next()) {
				allRoomNumbers.add(roomResults.getInt(1));
			}
			
			Collections.sort(allRoomNumbers);
			
			int i;
			for (i = 0; i < (allRoomNumbers.size() - 1); i++) {
				if (allRoomNumbers.get(i+1) != allRoomNumbers.get(i) + 1) {
					break;
				}
			}
			
			this.firstUnusedID = allRoomNumbers.get(i) + 1;
		}
		return firstUnusedID;
	}
}