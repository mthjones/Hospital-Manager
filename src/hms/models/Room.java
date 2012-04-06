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
	
	private Vector<Bed> getBeds() {
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
	
	public static Integer[] getRoomNumbers(int ward_id)
	{
		Integer[] Rooms = null;
		try
		{
			ResultSet roomNames = Database.getInstance().executeQuery("SELECT roomID FROM room WHERE ward = " + ward_id);
			if (roomNames == null) return null;
			roomNames.last();
			int numRows = roomNames.getRow();
			roomNames.first();
			Rooms = new Integer[numRows];
			int i = 0;
			do{
				Rooms[i] = roomNames.getInt("roomID");
				i++;
		
			}while(roomNames.next());
		}
		catch(SQLException e){} // Do nothing
		return Rooms;
	}

}
