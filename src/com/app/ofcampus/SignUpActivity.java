package com.app.ofcampus;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.app.ofcampus.networking.SendEmailAsyncTask;
import com.app.ofcampus.networking.SendEmailAsyncTask.SendEmailDeligate;
import com.app.ui.AbstractActivity;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.app.utils.FiledValidators;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AbstractActivity {

	private EditText edtUsername;
	private EditText edtEmail;
	private EditText edtPassword;
	private EditText edtConfirmPassword;
	private Button btnActivate;
	private ProgressDialog loadingDialog;
	private EditText edtFullName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		intUI();
	}

	@Override
	protected void intUI() {
		super.intUI();
		edtUsername = (EditText) findViewById(R.id.edt_username);
		edtEmail = (EditText) findViewById(R.id.edt_email);
		edtPassword = (EditText) findViewById(R.id.edt_password);
		edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
		edtFullName = (EditText) findViewById(R.id.edt_fullname);
		btnActivate = (Button) findViewById(R.id.btn_activate);
		loadingDialog = getLoadingDialog(context,getString(R.string.hold_on_), getString(R.string.signing_up_), false);
		setListeners();
	}

	private void setListeners() {
		btnActivate.setOnClickListener(new ActivateBtnClickListener());

	}

	private class ActivateBtnClickListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			boolean isValidInput = validateInput();
			if(isValidInput) {
				getSharedPreference(activity).putString(EMAIL_ID, edtEmail.getText().toString());
				continueSignUp();
			}
		}

	}
	
	private void continueSignUp() {
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        saveEmailId(email);
        String password = edtPassword.getText().toString();
        String fullName = edtFullName.getText().toString();
        ParseUser user = new ParseUser();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.put(IS_EMAIL_VERIFIED, false);
        user.put(USER_FULL_NAME, fullName);
        user.signUpInBackground(new SignUpUserCallBack());
        loadingDialog.show();
    }
	
	private class SignUpUserCallBack extends SignUpCallback {

		@Override
		public void done(ParseException arg0) {
			loadingDialog.dismiss();
			if(arg0!=null) {
				showAlert(getString(R.string.signup_failed_), arg0.getLocalizedMessage(),
						  getString(R.string.ok), null, context, null);
			}else{
				SendEmailAsyncTask emailAsyncTask = new SendEmailAsyncTask(getSavedEmailId(), context, new EmailVerificationDeligate());  
				emailAsyncTask.execute();
			}
		}
		
	}
	
	private class EmailVerificationDeligate implements SendEmailDeligate {

		@Override
		public void onEmailSend() {
			
			showAlert(getString(R.string.activation_pending_), getString(R.string.activation_message),
					getString(R.string.ok), null, context, new DialogDialogDeligate() {
				@Override
				public void positiveButtonClick(DialogInterface dialog, int id) {
					dialog.dismiss();
					switchToActivity(activity, EmailVerificationActivity.class, null);
				}
				
				@Override
				public void negativeButtonClick(DialogInterface dialog, int id) {
						dialog.dismiss();
				}
			});
			
		}
		
	}
	
	
	public void saveEmailId(String emailId) {
		getSharedPreference(activity).putString(EMAIL_ID, emailId);
	}
	
	public String getSavedEmailId() {
		return getSharedPreference(activity).getString(EMAIL_ID, "");
	}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		switchToActivity(activity, WelcomeActivity.class, null);
	}
	

	private boolean validateInput() {
		
		String username = edtUsername.getText().toString();
		boolean isValidUserName = TextUtils.isEmpty(username);
		if(isValidUserName) {
			showAlert(getString(R.string.valid_username_), getString(R.string.enter_valid_username_),getString(R.string.ok), 
					null, context, null);

			return false;
		}
		String fullName = edtFullName.getText().toString();
		boolean isVaildFullName = TextUtils.isEmpty(fullName);
		if(isVaildFullName) {
			showAlert(getString(R.string.invalid_fullname_),getString(R.string.enter_valid_fullname),getString(R.string.ok), 
					null, context, null);

			return false;
		}

		String email = edtEmail.getText().toString();
		boolean isEmptyEmail = TextUtils.isEmpty(email) ;
		boolean isValidEmail = FiledValidators.validateEmail(email);
		if(isEmptyEmail || !isValidEmail) {
			showAlert(getString(R.string.invalid_email_), getString(R.string.enter_valid_email_), getString(R.string.ok), 
					null, context, null);

			return false;
		}
		
		String password = edtPassword.getText().toString();
		String confrimPassword = edtConfirmPassword.getText().toString();
		boolean isPasswordEmpty = TextUtils.isEmpty(password) || TextUtils.isEmpty(confrimPassword);
		if(isPasswordEmpty) {

			showAlert(getString(R.string.invalid_password_), getString(R.string.enter_valid_password_), getString(R.string.ok), 
					null, context, null);

			return false;
		}

		boolean isBoothPassMatch = password.equals(confrimPassword);
		if(!isBoothPassMatch) {
			showAlert(getString(R.string.password_miss_match_), getString(R.string.both_the_pass_should_match_), getString(R.string.ok), 
					null, context, null);

			return false;
		}

		return true;
	}

}
