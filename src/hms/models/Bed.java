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
	
	/**
	 * Finds a bed given the id for it and returns it.
	 * @param id The id of the bed to find.
	 * @return The bed with the matching ID.
	 */
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
						   bedResults.getString("occupied").equals("Y"), bedResults.getString("size"));
		} catch (SQLException sqle) {
			return null;
		}
	}
	
	/**
	 * Sets whether a bed is occupied or not.
	 * NOTE: This immediately updates the value in the database.
	 * @param The value to set the bed's occupied value to
	 */
	public void setOccupied(boolean isOccupied) {
		this.occupied = isOccupied;
		try {
			Database.getInstance().executeUpdate("UPDATE bed SET (occupied = '" + (isOccupied ? "Y" : "N") + "') WHERE bedID = " + this.number);
		} catch (SQLException sqle) { }
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
