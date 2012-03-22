package hms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import hms.util.Database;

public class Bed {
	
	private int room_id;
	private boolean taken;
	private int bed_id;
	private String size;
	
	public Bed(int roomid, int bedid)
	{
		room_id = roomid;
		bed_id = bedid;
	}
	
	public static Integer[] getBedNumbers(int room_id)
	{
		Integer[] Beds = null;
		try
		{
			ResultSet bedNames = Database.getInstance().executeQuery("SELECT bedID FROM bed WHERE room = " + room_id + " AND occupied = 'N'");
			if (bedNames == null) return null;
			bedNames.last();
			int numRows = bedNames.getRow();
			bedNames.first();
			Beds = new Integer[numRows];
			int i = 0;
			do{
				Beds[i] = bedNames.getInt("bedID");
				i++;
			}while(bedNames.next());
		}
		catch(SQLException e){} // Do nothing
		return Beds;
	}
	
	public static void changeBedAvailability(int bed_id)
	{
		try
		{
			String availability;
			ResultSet bedNames = Database.getInstance().executeQuery("SELECT * FROM bed WHERE bedID = " + bed_id);
			
			bedNames.first();
			availability = bedNames.getString("occupied");
			if(availability.equals("N"))
				availability = "Y";
			else if(availability.equals("Y"))
				availability = "N";
			
			Database.getInstance().executeUpdate("UPDATE bed SET occupied = '" + availability + "' WHERE bedID = " + bed_id);
			
		}
		catch(SQLException e){} // Do nothing
	}

}
