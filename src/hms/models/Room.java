package hms.models;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import hms.util.Database;

public class Room {
	private int number;
	private Ward ward;
	
	public Room(int number, Ward ward)
	{
		this.number = number;
		this.ward = ward;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public Ward getWard() {
		return this.ward;
	}
	
	public Vector<Bed> getBeds() {
		Vector<Bed> beds = new Vector<Bed>();
		try {
			ResultSet bedResults = Database.getInstance().executeQuery("SELECT * FROM bed WHERE room = " + this.number);
			
			while (bedResults.next()) {
				beds.add(new Bed(bedResults.getInt("bedID"),
								 this,
								 bedResults.getBoolean("occupied"),
								 bedResults.getString("size")));
			}
		} catch(SQLException e) {}
		return beds;
	}
	
	public Vector<Bed> getAvailableBeds() {
		Vector<Bed> beds = new Vector<Bed>();
		try {
			ResultSet bedResults = Database.getInstance().executeQuery("SELECT * FROM bed WHERE room = " + this.number + " AND occupied = N");
			
			while (bedResults.next()) {
				beds.add(new Bed(bedResults.getInt("bedID"),
								 this,
								 bedResults.getBoolean("occupied"),
								 bedResults.getString("size")));
			}
		} catch(SQLException e) {}
		return beds;
	}
	
	public static Room find(int id) {
		try {
			ResultSet roomResults = Database.getInstance().executeQuery("SELECT * FROM room WHERE roomID = " + id);
			roomResults.last();
			if(roomResults.getRow() == 0) {
				return null;
			} else {
				roomResults.first();
			}
			return new Room(roomResults.getInt("roomID"), Ward.find(roomResults.getInt("ward")));
		} catch (SQLException sqle) {
			return null;
		}
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Room)) return false;
		Room otherRoom = (Room)other;
		return this.number == otherRoom.number;
	}
	
	public String toString() {
		return "" + this.number;
	}
}
