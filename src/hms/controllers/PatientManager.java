package hms.controllers;

import java.sql.SQLException;

import javax.swing.JFrame;

import hms.models.Ward;

import hms.views.PatientView;
import hms.views.DeletePatientConfirmationView;
import hms.models.Patient;
import hms.models.PatientTableModel;
import hms.models.Room;
import hms.models.Bed;

public class PatientManager {
	private PatientTableModel mainViewTableModel;

	public PatientManager(PatientTableModel tableModel) {
		this.mainViewTableModel = tableModel;
	}
	
	public Patient GetPatient(int healthCareNumber) throws SQLException {
		Patient patient = new Patient();
		return patient.find(Integer.toString(healthCareNumber));
	}
	
	public Patient [] searchForPatient(String firstName, String lastName) throws SQLException {
		return Patient.findByName(firstName);
	}

	public void EditPatient(JFrame mainScreenJFrame, String[] row) {
		OpenPatientView(row);
	}
    
	public void CreatePatient(JFrame mainScreenJFrame) {
		OpenPatientView();
	}
	
	public static String[] getPatientWardNames()
	{
		return Ward.getWardNames();
	}
	
	public static Integer[] getPatientRooms(int ward_id)
	{
		return Room.getRoomNumbers(ward_id);
	}
	
	public static Integer[] getPatientBeds(int room_id)
	{
		return Bed.getBedNumbers(room_id);
	}
	public static String getPatientSingleWardName(String ward_id)
	{
		int wardID = Integer.getInteger(ward_id);
		return Ward.getSingleWardName(wardID);
	}
	public static String getPatientSingleWardName(int ward_id)
	{
		return Ward.getSingleWardName(ward_id);
	}
	
	//Changes availability of chosen bed to its opposite
	public static void changeBedAvailability(int bed_id)
	{
		Bed.changeBedAvailability(bed_id);
	}
	
	public void deletePatient(JFrame frmMain, String healthcareNumber) {
		DeletePatientConfirmationView confirmationView = new DeletePatientConfirmationView(healthcareNumber, mainViewTableModel);
		confirmationView.frmConfirmDeletePatient.setVisible(true);
	}
	
	public static boolean doDeletePatient(String healthcareNumber) {
		return Patient.deleteFromString(healthcareNumber);
	}
	
	private void OpenPatientView() {
		PatientView patientView = new PatientView(mainViewTableModel);
		patientView.frmPatient.setVisible(true);
	}
	
	private void OpenPatientView(String[] row) {
		PatientView patientView = new PatientView(row, mainViewTableModel);
		patientView.frmPatient.setVisible(true);
	}
}
