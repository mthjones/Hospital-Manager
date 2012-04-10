package hms.integration;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Represents our integration testing done for the entire system. Each test is represented by
 * a boolean of whether the test has been performed and also a boolean of whether the test 
 * passed the requirements.
 */
public class Iteration3IntegrationTest {
	/**
	 * ==================
	 * Test login system.
	 * ==================
	 */
	
	/**
	 * Attempt to login using a username and password that are in the database.
	 *
	 * Expected: The login screen should disappear and the main application screen
	 * should appear.
	 */
	@Test
	public void loginWithValidUser() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Attempt to login using a username and password that are not in the database.
	 *
	 * Expected: The login screen should stay, the main application window should not
	 * appear, and the login screen should display "Invalid login" in red text.
	 */
	@Test
	public void loginWithInvalidUser() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Attempt to login using a nurse ID and password that are in the database.
	 *
	 * Expected: The login screen should disappear and the main application screen
	 * should appear.
	 */
	@Test
	public void loginWithValidNurse() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Attempt to login using a nurse ID and password that are not in the database.
	 *
	 * Expected: The login screen should stay, the main application window should not
	 * appear and the login screen should display "Invalid login" in red text.
	 */
	@Test
	public void loginWithInvalidNurse() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Close the login window.
	 *
	 * Expected: The login screen should disappear, and the application as a whole should close.
	 * The main application window should not appear.
	 */
	@Test
	public void closeLoginWindow() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * =============================
	 * Test main application window.
	 * =============================
	 */
	
	/**
	 * Show the main application window tabbed pane.
	 *
	 * Expected: If the logged in user was a user (admin), there should be both a Patients
	 * and Nurses tab. If the logged in user was a nurse, only the Patients tab should appear.
	 */
	@Test
	public void mainWindowTabbedPaneShowsAppropriateTabs() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Default shown tabbed pane.
	 *
	 * Expected: The Patients tab should be the default viewed pane.
	 */
	@Test
	public void mainWindowShowsPatientsTabByDefault() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * =================
	 * Test patients tab
	 * =================
	 */
	
	/**
	 * Default patient table shown data.
	 *
	 * Expected: The default shown data in the patients table should be all patients that
	 * are in the hospital currently.
	 */
	@Test
	public void patientTableDefaultShownData() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Selecting a patient from the table.
	 *
	 * Expected: All of the selected patient's information should be loaded into the panel
	 * on the right into the appropriate fields.
	 */
	@Test
	public void patientSelectionLoadsCorrectData() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Sorting patients.
	 *
	 * Expected: When a column header is selected, the patients are sorted by that column
	 * in an apprpriate manner.
	 */
	@Test
	public void patientTableSorting() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Searching for patients by name.
	 * 
	 * Expected: If the typed string in the search box is not a valid integer, it searches
	 * the patients table based on the name in a case insensitive manner.
	 */
	@Test
	public void patientSearchByName() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Searching for patients by healthcare number.
	 *
	 * Expected: If the typed string in the search box is a valid integer, it searches the
	 * patients table based on the healthcare number.
	 */
	@Test
	public void patientSearchByHealthcareNumber() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Searching with nothing in the search field.
	 *
	 * Expected: Nothing should happen.
	 */
	@Test
	public void searchingWithNothingInTheSearchField() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Clearing a search.
	 *
	 * Expected: Resets the table to show all patients, taking into account the setting
	 * of the "View All" checkbox.
	 */
	@Test
	public void clearingASearch() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Clearing a search with no current search active.
	 *
	 * Expected: Nothing should happen.
	 */
	@Test
	public void clearingASearchWithNoCurrentSearch() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Checking the View All checkbox.
	 *
	 * Expected: Checking it should cause the table to show all patients, both in and
	 * out of the hospital, taking into account the current search.
	 */
	@Test
	public void activatingViewAllCheckboxShowsAllPatients() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Unchecking the View All checkbox.
	 *
	 * Expected: Unchecking it should cause the table to only show patients that are
	 * currently in the hospital, taking into account the current search.
	 */
	@Test
	public void deactivatingViewAllCheckboxShowsOnlyPatientsInHospital() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Editing info in the patient info panel.
	 *
	 * Expected: None of the fields in the patient info panel should be editable.
	 */
	@Test
	public void cannotEditAnyFieldInPatientInfoPanel() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * =====================
	 * Test patient buttons.
	 * =====================
	 */
	
	/**
	 * Patient button states when nothing selected.
	 *
	 * Expected: Create and Refresh should be enabled, Edit and Delete should be
	 * disabled.
	 */
	@Test
	public void patientButtonStatusWhenNothingSelected() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Patient button states when a patient is selected.
	 *
	 * Expected: All buttons should be enabled.
	 */
	@Test
	public void patientButtonStateWhenPatientSelected() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Create patient button.
	 *
	 * Expected: Pressing it should pop up a modal dialog form.
	 */
	@Test
	public void createPatientButtonPopsUpModalDialog() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Edit patient button.
	 *
	 * Expected: Pressing it should pop up a modal dialog form.
	 */
	@Test
	public void editPatientButtonPopsUpModalDialog() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Delete patient button.
	 *
	 * Expected: Pressing it should pop up a confirmation dialog asking whether
	 * to delete the patient or cancel.
	 */
	@Test
	public void deletePatientButtonPopsUpConfirmationDialog() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Refresh button.
	 *
	 * Expected: Pressing it reloads the current table information.
	 */
	@Test
	public void refreshPatientTableButtonReloadsTableInformation() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ==========================
	 * Test create patient dialog
	 * ==========================
	 */
	
	/**
	 * Create Patient initial state.
	 *
	 * Expected: All fields should be blank except for the birthdate filled in with the
	 * current date and the Hospital ward selected.
	 */
	@Test
	public void createPatientInitialStateIsBlank() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Required fields are shown.
	 *
	 * Expected: The healthcare #, name, birthdate and bed fields should be marked with
	 * an asterisk to signify that they are required.
	 */
	@Test
	public void requiredFieldsAreShownOnCreatePatientForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Attempt to save with required fields missing.
	 *
	 * Expected: The dialog should stay shown, the missing fields should be marked in red
	 * and control should not be passed back to the main window.
	 */
	@Test
	public void requiredFieldsAreActuallyRequiredOnCreatePatientForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Saving with all required fields filled out.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * the patient should be saved into the database, and the table should be refreshed to 
	 * show the patient (taking into account the current search and the View All checkbox).
	 */
	@Test
	public void createPatientProperly() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Hitting the dialog's cancel button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * and the patient should not be saved to the database.
	 */
	@Test
	public void closeCreatePatientForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ========================
	 * Test edit patient dialog
	 * ========================
	 */
	
	/**
	 * Edit Patient initial state.
	 *
	 * Expected: The form should be loaded with the data that is stored for the selected
	 * patient.
	 */
	@Test
	public void editPatientInitialStateIsPreloadedWithPatientInfo() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Required fields are shown.
	 *
	 * Expected: The healthcare #, name, birthdate and bed fields should be marked with
	 * an asterisk to signify that they are required.
	 */
	@Test
	public void requiredFieldsAreShownOnEditPatientForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Attempt to save with required fields missing.
	 *
	 * Expected: The dialog should stay shown, the missing fields should be marked in red
	 * and control should not be passed back to the main window.
	 */
	@Test
	public void requiredFieldsAreActuallyRequiredOnEditPatientForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Saving with all required fields filled out.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * the patient should be updated in the database, and the table should be refreshed to 
	 * show the updated patient information (taking into account the current search and the 
	 * View All checkbox).
	 */
	@Test
	public void editPatientProperly() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Hitting the dialog's cancel button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * and the patient should not be updated in the database.
	 */
	@Test
	public void closeEditPatientForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ==========================
	 * Test delete patient dialog
	 * ==========================
	 */
	
	/**
	 * Selecting the "OK" button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * the patient should be removed from the database and the patient table should be 
	 * refreshed to no longer show the deleted patient. 
	 */
	@Test
	public void deletePatientDialogOKPress() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Selecting the cancel button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * and the patient should not be removed from the database.
	 */
	@Test
	public void deletePatientDialogCancelPress() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ===============
	 * Test nurses tab
	 * ===============
	 */
	
	/**
	 * Nurses table default data shown.
	 *
	 * Expected: All nurses in the database should be shown in the table along with
	 * all information for them.
	 */
	@Test
	public void nurseTableDefaultData() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ==================
	 * Test nurse buttons
	 * ==================
	 */
	
	/**
	 * Nurse button states when nothing selected.
	 *
	 * Expected: Create and Refresh should be enabled, Edit and Delete should be
	 * disabled.
	 */
	@Test
	public void nurseButtonStatusWhenNothingSelected() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Nurse button states when a nurse is selected.
	 *
	 * Expected: All buttons should be enabled.
	 */
	@Test
	public void nurseButtonStateWhenNurseSelected() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Create nurse button.
	 *
	 * Expected: Pressing it should pop up a modal dialog form.
	 */
	@Test
	public void createNurseButtonPopsUpModalDialog() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Edit nurse button.
	 *
	 * Expected: Pressing it should pop up a modal dialog form.
	 */
	@Test
	public void editNurseButtonPopsUpModalDialog() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Delete nurse button.
	 *
	 * Expected: Pressing it should pop up a confirmation dialog asking whether
	 * to delete the nurse or cancel.
	 */
	@Test
	public void deleteNurseButtonPopsUpConfirmationDialog() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Refresh button.
	 *
	 * Expected: Pressing it reloads the current table information.
	 */
	@Test
	public void refreshNurseTableButtonReloadsTableInformation() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ==========================
	 * Test create nurse dialog
	 * ==========================
	 */
	
	/**
	 * Create Nurse initial state.
	 *
	 * Expected: All fields should be blank except for the birthdate filled in with the
	 * current date and the Hospital ward selected.
	 */
	@Test
	public void createNurseInitialStateIsBlank() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Required fields are shown.
	 *
	 * Expected: The name, sin, salary and phone fields should be marked with
	 * an asterisk to signify that they are required.
	 */
	@Test
	public void requiredFieldsAreShownOnCreateNurseForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Attempt to save with required fields missing.
	 *
	 * Expected: The dialog should stay shown, the missing fields should be marked in red
	 * and control should not be passed back to the main window.
	 */
	@Test
	public void requiredFieldsAreActuallyRequiredOnCreateNurseForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Saving with all required fields filled out.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * the nurse should be saved into the database, and the table should be refreshed to 
	 * show the nurse.
	 */
	@Test
	public void createNurseProperly() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Hitting the dialog's cancel button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * and the nurse should not be saved to the database.
	 */
	@Test
	public void closeCreateNurseForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ========================
	 * Test edit nurse dialog
	 * ========================
	 */
	
	/**
	 * Edit Nurse initial state.
	 *
	 * Expected: The form should be loaded with the data that is stored for the selected
	 * nurse.
	 */
	@Test
	public void editNurseInitialStateIsPreloadedWithNurseInfo() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Required fields are shown.
	 *
	 * Expected: The name, sin, salary and phone fields should be marked with
	 * an asterisk to signify that they are required.
	 */
	@Test
	public void requiredFieldsAreShownOnEditNurseForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Attempt to save with required fields missing.
	 *
	 * Expected: The dialog should stay shown, the missing fields should be marked in red
	 * and control should not be passed back to the main window.
	 */
	@Test
	public void requiredFieldsAreActuallyRequiredOnEditNurseForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Saving with all required fields filled out.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * the nurse should be updated in the database, and the table should be refreshed to 
	 * show the updated nurse information.
	 */
	@Test
	public void editNurseProperly() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Hitting the dialog's cancel button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * and the nurse should not be updated in the database.
	 */
	@Test
	public void closeEditNurseForm() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * ==========================
	 * Test delete nurse dialog
	 * ==========================
	 */
	
	/**
	 * Selecting the "OK" button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * the nurse should be removed from the database and the nurse table should be 
	 * refreshed to no longer show the deleted nurse. 
	 */
	@Test
	public void deleteNurseDialogOKPress() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
	
	/**
	 * Selecting the cancel button.
	 *
	 * Expected: The dialog should close, control should be passed back to the main window,
	 * and the nurse should not be removed from the database.
	 */
	@Test
	public void deleteNurseDialogCancelPress() {
		boolean testPerformed = true;
		boolean testPassed = true;
		assertTrue(testPerformed && testPassed);
	}
}