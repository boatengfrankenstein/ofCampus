package com.app.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.app.ofcampus.FooterActivity;
import com.app.ofcampus.R;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.app.utils.AppConstants;
import com.app.utils.AppPreferences;

public class AbstractActivity extends Activity implements UitilityHelper, AppConstants{

	private UitilityHelperImp activityHimp = new UitilityHelperImp(); 
	protected Activity activity = this;
	protected Context context = this;
	private TextView txtHelp;
	private TextView txtContactUs;
	private TextView txtLegal;

	
	@Override
	public void switchToActivity(Activity activity,
			Class<? extends Activity> destinationClass, Bundle extras) {
		activityHimp.switchToActivity(activity, destinationClass, extras);
	}

	@Override
	public void goToActivity(Activity activity,
			Class<? extends Activity> destinationClass, Bundle extras) {
		activityHimp.goToActivity(activity, destinationClass, extras);
	}

	@Override
	public Typeface createTypeface(Context context, String font) {
		return activityHimp.createTypeface(context, font);
	}

	@Override
	public void hideKeyboard(Context context, EditText view) {
		activityHimp.hideKeyboard(context, view);
	}

	@Override
	public DisplayMetrics getDisplayMetrics(Activity activity) {
		return activityHimp.getDisplayMetrics(activity);
	}

	@Override
	public boolean isNetworkAvailable(Context context) {

		return activityHimp.isNetworkAvailable(context);
	}

	@Override
	public AppPreferences getSharedPreference(Context context) {
		return activityHimp.getSharedPreference(context);
	}

	@Override
	public String getDeviceId(Context context) {
		return null;
	}

	@Override
	public int getDisplayWidth(Activity activity) {

		return activityHimp.getDisplayWidth(activity);
	}

	@Override
	public int getDisplayHeight(Activity activity) {

		return activityHimp.getDisplayHeight(activity);
	}
	
	@Override
	public void showAlert(String title, String message, String positiveBtn,
			String negativeBtn, Context context, DialogDialogDeligate deligate) {
		activityHimp.showAlert(title, message, positiveBtn, negativeBtn, context, deligate);
	}

	protected void intUI(){

		
	}

	protected void setUpFooter() {

		txtHelp = (TextView) findViewById(R.id.txt_helpTextView);

		txtContactUs = (TextView) findViewById(R.id.txt_contactUsTextView);

		txtLegal = (TextView) findViewById(R.id.txt_legalTextView);

		setFooterListeners();
	}

	private void setFooterListeners() {

		txtHelp.setOnClickListener(new FooterLabelsClickListeners());

		txtContactUs.setOnClickListener(new FooterLabelsClickListeners());

		txtLegal.setOnClickListener(new FooterLabelsClickListeners());
	}

	private class FooterLabelsClickListeners implements OnClickListener {

		@Override
		public void onClick(View v) {

			TextView text = (TextView)v;

			int id = text.getId();

			switch (id) {

			case R.id.txt_helpTextView:

				helpClick();

				break;

			case R.id.txt_contactUsTextView:

				contactUsClick();

				break;
			case R.id.txt_legalTextView:

				legalClick();

				break;

			default:
				break;
			}

		}

		private void legalClick() {

			Bundle bundle = new Bundle();

			bundle.putString(FOOTER_TITLE, FOOTER_LEGAL);

			goToActivity(activity, FooterActivity.class, bundle);

		}

		private void contactUsClick() {

			Bundle bundle = new Bundle();

			bundle.putString(FOOTER_TITLE, FOOTER_CONTACT_US);

			goToActivity(activity, FooterActivity.class, bundle);

		}

		public void helpClick() {

			Bundle bundle = new Bundle();

			bundle.putString(FOOTER_TITLE, FOOTER_HELP);

			goToActivity(activity, FooterActivity.class, bundle);

		}

	}

	@Override
	public ProgressDialog getLoadingDialog(Context context, String title,
			String message, boolean isCancellable) {
		return activityHimp.getLoadingDialog(context, title, message, isCancellable);
	}



}
