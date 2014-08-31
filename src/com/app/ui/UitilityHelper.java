package com.app.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.app.utils.AppPreferences;

public interface UitilityHelper {

	public void switchToActivity(Activity activity, Class<? extends Activity> destinationClass, Bundle extras);
	
	public void goToActivity(Activity activity, Class<? extends Activity> destinationClass, Bundle extras);

	public Typeface createTypeface(Context context, String font);
	
	public void hideKeyboard(Context context, EditText view);
	
	public DisplayMetrics getDisplayMetrics(Activity activity);
	
	public boolean isNetworkAvailable(Context context);
	
	public AppPreferences getSharedPreference(Context context);
	
	public String getDeviceId(Context context);
	
	public int getDisplayWidth(Activity activity);
	
	public int getDisplayHeight(Activity activity);
	
	public void showAlert(String title, String message, String positiveBtn, String negativeBtn,Context context, final DialogDialogDeligate deligate);
	
	public ProgressDialog getLoadingDialog(Context context, String title, String message, boolean isCancellable);
}
