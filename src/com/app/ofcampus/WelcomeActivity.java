package com.app.ofcampus;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.app.ui.AbstractActivity;

public class WelcomeActivity extends AbstractActivity {

	private Button btnLogin;
	
	private Button btnSignUp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_welcome);
		
		intUI();
	}
	
	@Override
	protected void intUI() {
		
		super.intUI();
		
		setUpFooter();
		
		btnLogin = (Button) findViewById(R.id.btn_login);
		
		btnSignUp = (Button) findViewById(R.id.btn_signup);
		
		setListeners();
	
	}

	private void setListeners() {
		
		btnLogin.setOnClickListener(new LoginBtnClickListener());
		
		btnSignUp.setOnClickListener(new SignUpBtnClickListener());
	}
	
	private class LoginBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
		
			switchToActivity(activity, LoginScreenActivity.class, null);
		}
		
	}
	
	private class SignUpBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			switchToActivity(activity, SignUpActivity.class, null);
		}
		
	}

}
