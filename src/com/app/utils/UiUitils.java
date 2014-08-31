package com.app.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;


public class UiUitils {

	public static void switchToActivity(Activity activity, Class<? extends Activity> destinationActivity, Bundle extras) {

		Intent intent = new Intent(activity, destinationActivity);

		if(extras!=null) {
			intent.putExtras(extras);
		}

		activity.startActivity(intent);

		activity.finish();
	}

	public static void goToActivity(Activity activity, Class< ? extends Activity> destinationActivity, Bundle extras) {

		Intent intent = new Intent(activity, destinationActivity);

		if(extras!=null) {
			intent.putExtras(extras);
		}

		activity.startActivity(intent);

	}


	public static Typeface createTypeface(Context context, String font) {

		Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/"+font);

		return typeface;

	}

	public static ProgressDialog getLoadingDialog(Context context, String title, String message, boolean isCancellable) {

		ProgressDialog loadingDialog = new ProgressDialog(context);

		loadingDialog.setCancelable(isCancellable);

		loadingDialog.setCanceledOnTouchOutside(false);

		loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		
		if(title!=null) {
			loadingDialog.setTitle(title);
		}
		
		
		if(message!=null) {
			loadingDialog.setMessage(message);
		}
		
		return loadingDialog;
	}
}
