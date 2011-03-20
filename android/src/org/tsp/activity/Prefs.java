package org.tsp.activity;

import org.tsp.R;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity
{
	/*
	 * The preference keys along with default values.
	 */
	private static final String PREF_TOGGLE_SHOWOFF_BEHAVIOUR = "toggle_showoff_behaviour";
	private static final boolean PREF_TOGGLE_SHOWOFF_BEHAVIOUR_DEFAULT = true;
	
	private static final String PREF_SERVER_IP = "server_ip";
	private static final String PREF_SERVER_IP_DEFAULT = "10.0.0.2:7070";
	
	private static final String PREF_SELECT_DESCRIPTOR = "select_descriptor";
	private static final String PREF_SELECT_DESCRIPTOR_DEFAULT = "SIFT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	public static boolean getPref_isShowoffBehaviourEnabled(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context)
			.getBoolean(PREF_TOGGLE_SHOWOFF_BEHAVIOUR, PREF_TOGGLE_SHOWOFF_BEHAVIOUR_DEFAULT);
	}
	
	public static String getPref_serverIP(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context)
			.getString(PREF_SERVER_IP, PREF_SERVER_IP_DEFAULT);
	}
	
	public static String getPref_selectDescriptor(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context)
			.getString(PREF_SELECT_DESCRIPTOR, PREF_SELECT_DESCRIPTOR_DEFAULT);
	}
	
	
}
