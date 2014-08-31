package com.app.ofcampus;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.app.ui.AbstractActivity;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreenActivity extends AbstractActivity {

	private Button btnLogin;
	private EditText edtUsername;
	private EditText edtPassword;
	private CheckBox chkRememberMe;
	private ProgressDialog loadingDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		intUI();
	}

	@Override
	protected void intUI() {
		super.intUI();
		setUpFooter();
		btnLogin = (Button) findViewById(R.id.btn_login);
		edtUsername = (EditText) findViewById(R.id.edt_username);
		edtPassword = (EditText) findViewById(R.id.edt_password);
		chkRememberMe = (CheckBox) findViewById(R.id.chk_rememberme);
		setListeners();

	}

	private void setListeners() {
		btnLogin.setOnClickListener(new LoginBtnClickListener());
		edtPassword.setOnEditorActionListener(new GoEditorActionListener());
		
	}

	private class LoginBtnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			
			hideKeyboard(context, edtPassword);
			hideKeyboard(context, edtUsername);
		
			String userName = edtUsername.getText().toString();
			String password = edtPassword.getText().toString();

			boolean checkForValidCredientials = (userName != null && password != null) && (userName.length()> 0 && password.length()>0);

			if(checkForValidCredientials){
				boolean isRememberMe =  chkRememberMe.isChecked();
				getSharedPreference(activity).putBoolean(IS_REMEMBER_ME, isRememberMe);
				continueLogin(userName, password);
			}else{
				showAlert(getString(R.string.valid_credentials_), getString(R.string.valid_credentials_msg), 
						getString(R.string.ok), null, context, null);
			}
		}

		private void continueLogin(String userName, String password) {
			ParseUser.logInInBackground(userName, password, new UserLoginCallBack());
			loadingDialog = getLoadingDialog(context, getString(R.string.hold_on_) , getString(R.string.login_in_), false);
			loadingDialog.show();
		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		switchToActivity(activity, WelcomeActivity.class, null);
	}

	private class UserLoginCallBack extends LogInCallback {

		@Override
		public void done(ParseUser arg0, ParseException arg1) {

			loadingDialog.dismiss();
			if(arg1 != null) {
				showAlert(getString(R.string.login_failed_),arg1.getLocalizedMessage(),getString(R.string.ok),
						null, context, null);
			}else{
				boolean isVerified = arg0.getBoolean(IS_EMAIL_VERIFIED);
				if(isVerified) {
					getSharedPreference(activity).putString(SESSION_TOKEN, arg0.getSessionToken());
					switchToActivity(activity, HomeScreenActivity.class, null);
				}else{
					showAlert(getString(R.string.login_failed_),getString(R.string.email_not_verfied),getString(R.string.ok), null, context, new DialogDialogDeligate() {

						@Override
						public void positiveButtonClick(DialogInterface dialog, int id) {

							switchToActivity(activity, EmailVerificationActivity.class, null);
						}

						@Override
						public void negativeButtonClick(DialogInterface dialog, int id) {
							dialog.dismiss();
						}
					});
				}
			}

		}

	}

	private class GoEditorActionListener implements OnEditorActionListener {

		@Override
		public boolean onEditorAction(TextView arg0, int actionId, KeyEvent arg2) {

			if(actionId == EditorInfo.IME_ACTION_GO) {
				new LoginBtnClickListener().onClick(null);
			}
			return true;
		}

	}
	
    

}
