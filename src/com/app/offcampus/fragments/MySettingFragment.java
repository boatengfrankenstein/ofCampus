package com.app.offcampus.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.app.ofcampus.R;
import com.app.ofcampus.customviews.LoadingView;
import com.app.ui.AbstractFragment;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MySettingFragment extends AbstractFragment{
	
	private EditText edtName;
	private EditText edtYearOfGraduation;
	private EditText edtPrimaryEmail;
	private EditText edtMobileNumber;
	private EditText edtAlternateNumber;
	private EditText edtWhatsAppNumber;
	private EditText edtAlternateEmail;
	private EditText edtCountry;
	private EditText edtCity;
	private Button btnSaveSettings;
	private View profileView;
	private RelativeLayout rltLayoutProfileContainer;
	private ProgressDialog progressDialog;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			/*@Nullable*/ ViewGroup container, /*@Nullable*/ Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_settings, null);
		initUI(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(/*@Nullable*/ Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if(isNetworkAvailable(getActivity())) {
			getUserDetail();
		}
	}

	@SuppressLint("InflateParams")
	private void initUI(View view) {
		
		profileView = LayoutInflater.from(getActivity()).inflate(R.layout.view_my_settings, null);
		rltLayoutProfileContainer = (RelativeLayout) view.findViewById(R.id.rlt_layout_profile_container);
		edtName = (EditText)profileView.findViewById(R.id.edt_name);
		edtYearOfGraduation = (EditText) profileView.findViewById(R.id.edt_year_of_graduation);
		edtPrimaryEmail = (EditText) profileView.findViewById(R.id.edt_primary_email);
		edtMobileNumber = (EditText) profileView.findViewById(R.id.edt_mobile_number);
		edtAlternateNumber = (EditText) profileView.findViewById(R.id.edt_alternate_mobile_number);
		edtWhatsAppNumber = (EditText) profileView.findViewById(R.id.edt_whatsapp_number);
		edtAlternateEmail = (EditText) profileView.findViewById(R.id.edt_alt_email);
		edtCountry = (EditText) profileView.findViewById(R.id.edt_country);
		edtCity = (EditText) profileView.findViewById(R.id.edt_city);
		btnSaveSettings = (Button) profileView.findViewById(R.id.btn_save_profile);
		btnSaveSettings.setOnClickListener(new SaveSettingOnClickListeners());
	}
	
	private void getUserDetail() {
		ParseUser.becomeInBackground(getSessionToken(), new UserProfileCallBack());
		LoadingView loadingView = new LoadingView(getActivity());
		loadingView.setLoadingMessage("Loading your profile...please wait...");
		rltLayoutProfileContainer.addView(loadingView);
	}
	
	private class UserProfileCallBack extends LogInCallback {

		@Override
		public void done(ParseUser user, ParseException arg1) {
			rltLayoutProfileContainer.removeAllViews();
			if(arg1!=null) {
				showAlert(getString(R.string.oops_),arg1.getMessage(), getString(R.string.ok), null, context, null);
			}else{
				setProfileData(user);
			}
		}
	}
	
	private void setProfileData(ParseUser user) {
		edtName.setText(user.getString(USER_FULL_NAME));
		edtYearOfGraduation.setText(String.valueOf(user.getInt(USER_YEAR_OF_GRADUATION)));
		edtPrimaryEmail.setText(user.getEmail());
		edtMobileNumber.setText(user.getString(USER_MOBILE_NUMBER));
		edtWhatsAppNumber.setText(user.getString(USER_WHATS_APP_NUMBER));
		edtAlternateNumber.setText(user.getString(USER_ALT_MOBILE_NUMBER));
		edtAlternateEmail.setText(user.getString(USER_ALT_EAMIL_ID));
		edtCity.setText(user.getString(USER_CITY));
		edtCountry.setText(user.getString(USER_COUNTRY));
		rltLayoutProfileContainer.removeAllViews();
		rltLayoutProfileContainer.addView(profileView);
	}
	
	private class SaveSettingOnClickListeners implements OnClickListener  {

		@Override
		public void onClick(View v) {
			
			if(isNetworkAvailable(getActivity())) {
				saveMySettings();
			}
			
			
		}
		
	}

	public void saveMySettings() {
		
		String fullName = edtName.getText().toString();
		
		boolean isFullNameEmpty = TextUtils.isEmpty(fullName);
		
		if(isFullNameEmpty) {
			showAlert(getString(R.string.invalid_fullname_), getString(R.string.enter_valid_fullname),
					  getString(R.string.ok), null, getActivity(), null);
			return;
		}
		
		String strYOG = edtYearOfGraduation.getText().toString();
		int yearOfGraduation = 0;
		if(!TextUtils.isEmpty(strYOG)){
			yearOfGraduation = Integer.parseInt(edtYearOfGraduation.getText().toString());
		}
		String mobileNumber = edtMobileNumber.getText().toString();
		String altMobileNumber = edtAlternateNumber.getText().toString();
		String whatsAppNumber = edtWhatsAppNumber.getText().toString();
		String altEmail = edtAlternateEmail.getText().toString();
		String city = edtCity.getText().toString();
		String country = edtCountry.getText().toString();
		
		ParseUser parseUser = ParseUser.getCurrentUser();
		parseUser.put(USER_FULL_NAME, fullName);
		parseUser.put(USER_YEAR_OF_GRADUATION, yearOfGraduation);
		parseUser.put(USER_MOBILE_NUMBER, mobileNumber);
		parseUser.put(USER_ALT_MOBILE_NUMBER, altMobileNumber);
		parseUser.put(USER_WHATS_APP_NUMBER, whatsAppNumber);
		parseUser.put(USER_ALT_EAMIL_ID, altEmail);
		parseUser.put(USER_CITY, city);
		parseUser.put(USER_COUNTRY, country);
		parseUser.saveInBackground(new UpdateUserCallBack());
		progressDialog = getLoadingDialog(getActivity(), null, "Saving your settings...", false);
		progressDialog.show();
		
	}
	
	private class UpdateUserCallBack extends SaveCallback {

		@Override
		public void done(ParseException arg0) {
			
			if(progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			
			if(arg0!=null) {
				showAlert(getString(R.string.error_), arg0.getLocalizedMessage(), getString(R.string.ok), null, getActivity(), null);
			}else{
				showAlert(getString(R.string.success_), getString(R.string.setting_update_msg), getString(R.string.ok), null, getActivity(), null);
			}
			
		}
		
	}

}
