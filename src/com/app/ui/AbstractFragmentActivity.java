package com.app.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.app.utils.AppConstants;
import com.app.utils.AppPreferences;

public class AbstractFragmentActivity extends FragmentActivity implements UitilityHelper, AppConstants{

	private UitilityHelperImp activityHimp = new UitilityHelperImp(); 
	protected Activity activity = this;
	protected Context context = this;

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



	@Override
	public ProgressDialog getLoadingDialog(Context context, String title,
			String message, boolean isCancellable) {
		return activityHimp.getLoadingDialog(context, title, message, isCancellable);
	}
}
