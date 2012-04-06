package hms.models;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import hms.util.Database;

public class Ward {
	private int id;
	private String name;

	public Ward(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Vector<Room> getRooms() {
		Vector<Room> rooms = new Vector<Room>();
		try {
			ResultSet roomResults = Database.getInstance().executeQuery("SELECT * FROM room WHERE ward = " + this.id);
			
			while (roomResults.next()) {
				rooms.add(new Room(roomResults.getInt("roomID"), this));
			}
		} catch(SQLException e) {}
		return rooms;
	}
	
	public static Vector<Ward> getAllWards() {
		Vector<Ward> wards = new Vector<Ward>();
		try {
			ResultSet wardResults = Database.getInstance().executeQuery("SELECT * FROM ward");
			
			while (wardResults.next()) {
				wards.add(new Ward(wardResults.getInt("wardID"), wardResults.getString("wardName")));
			}
		} catch (SQLException sqle) {}
		return wards;
	}
	
	public static Ward find(int id) {
		try {
			ResultSet wardResults = Database.getInstance().executeQuery("SELECT * FROM ward WHERE wardID = " + id);
			wardResults.last();
			if(wardResults.getRow() == 0) {
				return null;
			} else {
				wardResults.first();
			}
			return new Ward(wardResults.getInt("wardID"), wardResults.getString("wardName"));
		} catch (SQLException sqle) {
			return null;
		}
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Ward)) return false;
		Ward otherWard = (Ward)other;
		return this.id == otherWard.id;
	}
	
	public String toString() {
		return this.name;
	}
}
