package hms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import hms.util.Database;

public class Bed {
	private int number;
	private Room room;
	private boolean occupied;
	private String size;
	
	public Bed(int number, Room room, boolean occupied, String size)
	{
		this.room = room;
		this.number = number;
		this.occupied = occupied;
		this.size = size;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public boolean getOccupied() {
		return this.occupied;
	}
	
	public String getSize() {
		return this.size;
	}
	
	public static Bed find(int id) {
		try {
			ResultSet bedResults = Database.getInstance().executeQuery("SELECT * FROM bed WHERE bedID = " + id);
			bedResults.last();
			if(bedResults.getRow() == 0) {
				return null;
			} else {
				bedResults.first();
			}
			return new Bed(bedResults.getInt("bedID"), Room.find(bedResults.getInt("room")), 
						   bedResults.getBoolean("occupied"), bedResults.getString("size"));
		} catch (SQLException sqle) {
			return null;
		}
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Bed)) return false;
		Bed otherBed = (Bed)other;
		return this.number == otherBed.number;
	}
	
	public String toString() {
		return "" + this.number;
	}
}
