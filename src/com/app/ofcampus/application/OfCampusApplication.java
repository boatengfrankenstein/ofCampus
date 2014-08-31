package com.app.ofcampus.application;

import android.app.Application;

import com.parse.Parse;

public class OfCampusApplication extends Application{

	public OfCampusApplication() {
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Parse.initialize(this, "bcWf33BNfxTdEui7ZKqEXKR8RfjJvjHHc59GP5UJ", "i90TTUnw3wHTARD7giwtNhgooAdpNR5o8dAfuzuC");
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	
	
}
