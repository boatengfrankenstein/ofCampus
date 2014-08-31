package com.app.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.app.utils.AlertMessage;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.app.utils.AppPreferences;
import com.app.utils.DeviceUtils;
import com.app.utils.InputUtils;
import com.app.utils.NetworkUtils;
import com.app.utils.UiUitils;

public class UitilityHelperImp implements UitilityHelper{

	@Override
	public void switchToActivity(Activity activity,
			Class<? extends Activity> destinationClass, Bundle extras) {
		
		UiUitils.switchToActivity(activity, destinationClass, extras);
		
	}

	@Override
	public void goToActivity(Activity activity,
			Class<? extends Activity> destinationClass, Bundle extras) {
		
		UiUitils.goToActivity(activity, destinationClass, extras);
		
	}

	@Override
	public Typeface createTypeface(Context context, String font) {
		
		return UiUitils.createTypeface(context, font);
	}

	@Override
	public void hideKeyboard(Context context, EditText view) {
		
		InputUtils.hideKeyboard(context, view);
	}

	@Override
	public DisplayMetrics getDisplayMetrics(Activity activity) {
		
		return DeviceUtils.getDisplayMetrics(activity);
	}

	@Override
	public boolean isNetworkAvailable(Context context) {
		
		return NetworkUtils.isNetworkAvailable(context);
	}

	@Override
	public AppPreferences getSharedPreference(Context context) {
		return AppPreferences.getAppPreferences(context);
	}

	@Override
	public String getDeviceId(Context context) {
		
		return null;
	}

	@Override
	public int getDisplayWidth(Activity activity) {
		
		return DeviceUtils.getDisplayWidth(activity);
	}

	@Override
	public int getDisplayHeight(Activity activity) {
		
		return DeviceUtils.getDisplayHeight(activity);
	}

	@Override
	public void showAlert(String title, String message, String positiveBtn,
			String negativeBtn, Context context, DialogDialogDeligate deligate) {
		AlertMessage.showAlert(title, message, positiveBtn, negativeBtn, context, deligate);
	}

	@Override
	public ProgressDialog getLoadingDialog(Context context, String title,
			String message, boolean isCancellable) {
		return UiUitils.getLoadingDialog(context, title, message, isCancellable);
	}


}
