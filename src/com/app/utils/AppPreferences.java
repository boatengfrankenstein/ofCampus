package com.app.utils;

import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.app.ofcampus.R;

@SuppressLint("NewApi")
public class AppPreferences {

	private SharedPreferences preferences;
	private SharedPreferences.Editor prefEditor;
	private static AppPreferences appPreferences;

	protected AppPreferences() {

	}

	protected AppPreferences(Context context) {

		preferences = context.getSharedPreferences(context.getString(R.string.app_name), Activity.MODE_PRIVATE);
	}

	public static AppPreferences getAppPreferences(Context context) {

		if(appPreferences == null) {
			appPreferences = new AppPreferences(context);
		}

		return appPreferences;
	}

	public void putString(String key, String value) {

		prefEditor = preferences.edit();
		
		prefEditor.putString(key, value);
		
		prefEditor.commit();
	}

	public String getString(String key, String defaultValue) {
		
		return preferences.getString(key, defaultValue);
	}

	public void putBoolean(String key, Boolean value) {

		prefEditor = preferences.edit();
		
		prefEditor.putBoolean(key, value);
		
		prefEditor.commit();
	}
	
	public Boolean getBoolean(String key, Boolean defaultValue) {
		
		return preferences.getBoolean(key, defaultValue);
	}
	
	public void putLong(String key, Long value) {

		prefEditor = preferences.edit();
		
		prefEditor.putLong(key, value);
		
		prefEditor.commit();
	}
	
	
	public Long getLong(String key, Long defaultValue) {

		return preferences.getLong(key, defaultValue);
	}
	
	
	public void putFloat(String key, Float value) {

		prefEditor = preferences.edit();
		
		prefEditor.putFloat(key, value);
		
		prefEditor.commit();
	}
	
	public Float getFloat(String key, Float defaultValue) {

		return preferences.getFloat(key, defaultValue);
	}
	
	public void putInt(String key, Integer value) {

		prefEditor = preferences.edit();
		
		prefEditor.putInt(key, value);
		
		prefEditor.commit();
	}
	
	
	public Integer getInt(String key, Integer defaultValue) {

		return preferences.getInt(key, defaultValue);
	}
	
	public void putStringSet(String key, Set<String> value) {

		prefEditor = preferences.edit();
		
		prefEditor.putStringSet(key, value);
		
		prefEditor.commit();
	}
	
	
	public Set<String> getStringSet(String key, Set<String> defaultValue) {

		return preferences.getStringSet(key, defaultValue);
	}
	
	public void clearAllPreferences(){
		
		prefEditor = preferences.edit();
		
		prefEditor.clear();
		
		prefEditor.commit();
	}
	

}
