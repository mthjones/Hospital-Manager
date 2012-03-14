package hms.models;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import hms.util.Database;

public class Room {
	
	private Vector<Bed> beds;
	private int room_id;
	private int ward_id;
	
	public Room(int roomid, int wardid, Vector<Bed> bed)
	{
		room_id = roomid;
		ward_id = wardid;
		beds = bed;
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
