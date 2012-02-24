package hms.models;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import hms.db.Database;

public class Patient {
	private String healthcare_number;
	private String name;
	private String phone_number;
	private String email;
	private String gender;
	private String treatment;
	private String address;
	private Date birthdate;
	private String medications;
	private String special_care;
	private String history;
	private String comments;
	private String emerg_name;
	private String emerg_phone_number;
	private String emerg_email;
	public ArrayList<String> errors = new ArrayList<String>();
	
	public Patient(String healthcare_number, String name, String phone_number, String email,
			String gender, String treatment, String address, Date birthdate, String medications, 
			String special_care, String history, String comments, String emerg_name, 
			String emerg_phone_number, String emerg_email) {
		this.name = name;
		this.phone_number = phone_number;
		this.email = email;
		this.healthcare_number = healthcare_number;
		this.gender = gender;
		this.treatment = treatment;
		this.address = address;
		this.birthdate = birthdate;
		this.medications = medications;
		this.special_care = special_care;
		this.history = history;
		this.comments = comments;
		this.emerg_name = emerg_name;
		this.emerg_phone_number = emerg_phone_number;
		this.emerg_email = emerg_email;
	}
	
	/**
	 * Finds the patient corresponding to the given healthcare number in a patient object. If
	 * the patient doesn't exist, returns null.
	 * @param healthcare_number The healthcare number for the patient to be found
	 * @return Patient object if found, or null if it isn't
	 */
	public static Patient find(String healthcare_number) throws SQLException {
		ResultSet patient = Database.getInstance().executeQuery("SELECT * FROM patient WHERE healthcare_number = '" + healthcare_number + "'");
		patient.last();
		if(patient.getRow() == 0) {
			return null;
		} else {
			patient.first();
		}
		return new Patient(patient.getString(1), patient.getString(2), patient.getString(3),
				patient.getString(4), patient.getString(5), patient.getString(6), patient.getString(7),
				patient.getDate(8), patient.getString(9), patient.getString(10), patient.getString(11),
				patient.getString(12), patient.getString(13), patient.getString(14), patient.getString(15));
	}
	
	/**
	 * Finds all patients and returns them as in a patient objects. If
	 * no patients exist, null is returnes
	 * @return Patients if found, or null if there arent any
	 */
	public static Vector<Patient> findAllPatients() throws SQLException{
		ResultSet patient = Database.getInstance().executeQuery("SELECT * FROM patient");
		if (patient == null) return null;
		patient.first();
		Vector<Patient> paitents = new Vector<Patient>();
		while(!patient.isLast()){
			paitents.add(new Patient(patient.getString(1), patient.getString(2), patient.getString(3),
					patient.getString(4), patient.getString(5), patient.getString(6), patient.getString(7),
					patient.getDate(8), patient.getString(9), patient.getString(10), patient.getString(11),
					patient.getString(12), patient.getString(13), patient.getString(14), patient.getString(15)) );
			if(!patient.next()) return null;
		}
		return paitents;
	}
	
	/**
	 * Tries to save the patient to the database. Returns true on a successful save or false
	 * if the save fails for any reason.
	 * @return true if the save is successful; false otherwise
	 */
	public boolean create() throws SQLException {
		try {
			int rows_added = Database.getInstance().executeUpdate("INSERT INTO patient VALUES ('" + 
				this.healthcare_number + "','" + this.name + "','" + this.phone_number + "','" + 
				this.email + "','" + this.gender + "','" + this.treatment + "','" + 
				this.address + "','" + new java.sql.Date(this.birthdate.getTime()) + "','" + this.medications + "','" + 
				this.special_care + "','" + this.history + "','" + this.comments + "','" + 
				this.emerg_name + "','" + this.emerg_phone_number + "','" + this.emerg_email + "')");
			this.errors.clear();
			return true;
		} catch (SQLException sqle) {
			this.errors.add("Couldn't add to database");
			return false;
		}
	}
	
	/**
	 * Tries to delete the patient from the database. Returns true on a successful delete or
	 * false if the delete fails for any reason.
	 * @return true if the delete is successful; false otherwise
	 */
	public boolean delete() throws SQLException {
		try {
			int patient = Database.getInstance().executeUpdate("DELETE FROM patient WHERE healthcare_number = '" + this.healthcare_number + "'");
			if (patient == 0) {
				this.errors.add("Patient does not exist");
				return false;
			}
			this.errors.clear();
			return true;
		} catch (SQLException sqle) {
			this.errors.add("Could not delete patient");
			return false;
		}
	}
}