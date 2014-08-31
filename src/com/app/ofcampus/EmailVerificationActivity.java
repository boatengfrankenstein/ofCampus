package com.app.ofcampus;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.ofcampus.networking.SendEmailAsyncTask;
import com.app.ofcampus.networking.SendEmailAsyncTask.SendEmailDeligate;
import com.app.ui.AbstractActivity;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class EmailVerificationActivity extends AbstractActivity {

	private EditText edtVerificationCode;
    private Button btnVerify;
    private Button btnResendCode;
    private ProgressDialog loadingDialog;
    private EditText edtEmail;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_verification);
		intUI();
	}
	   
	@Override
    protected void intUI() {
        super.intUI();
        edtVerificationCode = (EditText) findViewById(R.id.edt_verifiction_code);
        btnVerify = (Button) findViewById(R.id.btn_verify);
        btnResendCode = (Button) findViewById(R.id.btn_resend_code);
        edtEmail = (EditText) findViewById(R.id.edt_enter_mail);
        loadingDialog = getLoadingDialog(context, getString(R.string.hold_on_) , getString(R.string.signing_up_), false);
        setListeners();
    }
	
    private void setListeners() {

        btnVerify.setOnClickListener(new VerifyBtnClickListener());
        btnResendCode.setOnClickListener(new ResendCodeBtnClickListener());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        switchToActivity(activity, LoginScreenActivity.class, null);
    }


	private class VerifyBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			hideKeyboard(activity, edtVerificationCode);
			if( edtVerificationCode.getText().toString().trim().equalsIgnoreCase(String.valueOf(getSavedVerificationCode()) ) )
			{
				ParseUser user = ParseUser.getCurrentUser();
				user.put(IS_EMAIL_VERIFIED, true);
				user.saveInBackground(new VerfiyUserCallBack());
				loadingDialog = getLoadingDialog(activity, null, "Verifiying email...", false);
				loadingDialog.show();
			}else{
				showAlert("Failed!", "Email verfication failed, Try again!", "OK", null, context, null);
			}
		}
	}

	private class VerfiyUserCallBack extends SaveCallback {

		@Override
		public void done(ParseException arg0) {
			loadingDialog.dismiss();
			if(arg0==null) {
				showAlert("Success!","Email verified successfully, You can login to app now.","OK", null, context, new DialogDialogDeligate(){
					@Override
					public void positiveButtonClick(DialogInterface dialog,
							int id) {

						dialog.dismiss();
						switchToActivity(activity, LoginScreenActivity.class, null);
					}
					@Override
					public void negativeButtonClick(DialogInterface dialog,
							int id) {
					}
				});

			}else{
				showAlert("Failed!", "Email verfication failed, Try again!"+arg0.getLocalizedMessage(), "OK", null, context, null);
			}
		}

	}

	private class ResendCodeBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			String email = getSavedEmailId();
			if(TextUtils.isEmpty(email) && edtEmail.getVisibility() == View.GONE) {
				edtEmail.setVisibility(View.VISIBLE);
				Toast.makeText(context, "Enter your registered email.", Toast.LENGTH_SHORT).show();
				return;
			}else{
				email = edtEmail.getText().toString();
			}
			SendEmailAsyncTask emailAsyncTask = new SendEmailAsyncTask(email, context, new EmailVerificationDeligate());  
			emailAsyncTask.execute();
		}
	}
	
	private class EmailVerificationDeligate implements SendEmailDeligate {

		@Override
		public void onEmailSend() {
			showAlert(getString(R.string.activation_pending_),"Verification code has been sent to your email.",
					getString(R.string.ok), null, context,null);
		}
		
	}

	public void saveEmailId(String emailId) {
		getSharedPreference(activity).putString(EMAIL_ID, emailId);
	}
	
	public String getSavedEmailId() {
		return getSharedPreference(activity).getString(EMAIL_ID, "");
	}
	
	public int getSavedVerificationCode() {
		return getSharedPreference(activity).getInt(VERIFICATION_CODE, 0);
	}
	

}
