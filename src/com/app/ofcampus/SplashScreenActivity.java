package com.app.ofcampus;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.text.TextUtils;

import com.app.ui.AbstractActivity;

public class SplashScreenActivity extends AbstractActivity {

	private TimerTask timerTask;
	
	private Timer timer;
	
	private String sessionToken;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash_screen);
		
		timerTask = new TimerTask() {
			
			private Boolean isRememberMe;

			@Override
			public void run() {
				
				sessionToken = getSharedPreference(activity).getString(SESSION_TOKEN, "");
				
				isRememberMe = getSharedPreference(activity).getBoolean(IS_REMEMBER_ME, false);
				
				if(!TextUtils.isEmpty(sessionToken) && isRememberMe) {

					switchToActivity(activity, HomeScreenFragmentActivity.class, null);
					
				}else{
					
					getSharedPreference(activity).clearAllPreferences();
					
					switchToActivity(activity, WelcomeActivity.class, null);
				}
				
				
				
			}
		};
		
		timer = new Timer();
		
		timer.schedule(timerTask, 2*1000);
		
	}

}
