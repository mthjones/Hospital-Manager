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
	
	public int getWardNumber(){
		return this.id;
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

	public static Ward[] getWards(){
		Ward[] wards = null;
		try
		{
			ResultSet wardNames = Database.getInstance().executeQuery("SELECT * FROM ward");
			if (wardNames == null) return null;
			wardNames.last();
			int numRows = wardNames.getRow();
			wardNames.first();
			wards = new Ward[numRows];
			int i = 0;
			do{
				String name = wardNames.getString("wardName");
				int number = Integer.parseInt(wardNames.getString("wardID"));
				wards[i] = new Ward(number, name);
				i++;
			}while(wardNames.next());
		}
		catch(SQLException e){} // Do nothing
		return wards;
	}
	
	public String toString(){
		return this.name;
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
