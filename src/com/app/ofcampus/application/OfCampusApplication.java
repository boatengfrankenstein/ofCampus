package com.app.ofcampus.application;

import android.app.Application;

import com.parse.Parse;

public class OfCampusApplication extends Application{

	public OfCampusApplication() {
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		//Parse.initialize(this, "bcWf33BNfxTdEui7ZKqEXKR8RfjJvjHHc59GP5UJ", "i90TTUnw3wHTARD7giwtNhgooAdpNR5o8dAfuzuC");
		Parse.initialize(this,"201E8yM9vC7Z6eAEW36XaVrMnWqhD8rosstf7tH6","MQYL9QiV8KscHrDV1h4D8uXAlYTJt5JrSMBxNFV1");
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
