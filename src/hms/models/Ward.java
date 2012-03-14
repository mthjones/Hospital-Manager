package hms.models;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import hms.util.Database;

public class Ward {
	
	private Vector<Room> rooms;
	private int ward_id;
	private String ward_name;
	
	public Ward(int ID, String name, Vector<Room> room)
	{
		ward_id = ID;
		ward_name = name;
		rooms = room;
	}
	
	public static String[] getWardNames()
	{
		String[] Wards = null;
		try
		{
			ResultSet wardNames = Database.getInstance().executeQuery("SELECT wardName FROM ward");
			if (wardNames == null) return null;
			wardNames.last();
			int numRows = wardNames.getRow();
			wardNames.first();
			Wards = new String[numRows];
			int i = 0;
			do{
				Wards[i] = wardNames.getString("wardName");
				i++;
			}while(wardNames.next());
		}
		catch(SQLException e){} // Do nothing
		return Wards;
	}
	
	public static String getSingleWardName(int wardID)
	{
		String ward = null;
		try
		{
			ResultSet wardName = Database.getInstance().executeQuery("SELECT wardName FROM ward WHERE wardID = " + wardID);
			wardName.first();
			ward = wardName.getString("wardName");
		}
		catch(SQLException e) {}
		return ward;
	}

}
