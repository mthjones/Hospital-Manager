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
			ResultSet bedNames = Database.getInstance().executeQuery("SELECT bedID FROM bed WHERE room = " + room_id);
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

}
