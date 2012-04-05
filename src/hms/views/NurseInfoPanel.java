package hms.views;

import javax.swing.*;

import hms.models.Nurse;

public class NurseInfoPanel extends AbstractInfoPanel {
	public NurseInfoPanel() {
		initUI();
	}
	
	protected void initUI() {
		
	}
	
	public void reset() {
		
	}
	
	public void loadInformation(Object objToLoad) {
		Nurse nurse = (Nurse)objToLoad;
	}
	
	public boolean validateInformation() {
		return true;
	}
}