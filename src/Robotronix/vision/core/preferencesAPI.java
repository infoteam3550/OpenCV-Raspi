package Robotronix.vision.core;

import java.util.prefs.*;

public class preferencesAPI {
	public Preferences m_prefs; //The preferenceAPI name
	
	public preferencesAPI() {
		m_prefs = Preferences.userNodeForPackage(this.getClass()); //set the preferencesAPI variable
		
	}
	
	
}
