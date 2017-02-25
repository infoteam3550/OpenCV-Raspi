package Robotronix.vision.core;

import java.util.prefs.*;

public class preferencesAPI {
	public Preferences m_prefs; //The preferenceAPI name
	
	public preferencesAPI() 
	{
		m_prefs = Preferences.userNodeForPackage(this.getClass()); //set the preferencesAPI variable
		
	}
	
	public String get(String key, String def) 
	{
		return m_prefs.get(key, def);
	}
	
	public void put(String key, String value) 
	{
		m_prefs.put(key, value);
	}
	
	public boolean isExist(String key) 
	{
		return m_prefs.get(key, null) != null;
	}
	
	public String initKey(String key, String def) 
	{
		String value;
		if (isExist(key) == true) 
		{
			value = get(key,def);
		}
		else 
		{
			put(key,def);
			value = def;
		}
		return value;
	}
}