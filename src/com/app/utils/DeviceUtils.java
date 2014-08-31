package com.app.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class DeviceUtils {

	public static DisplayMetrics getDisplayMetrics(Activity activity) {

		DisplayMetrics metrics = new DisplayMetrics();

		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		return metrics;
	}

	public static int getDisplayWidth(Activity activity){
		
		return getDisplayMetrics(activity).widthPixels;
	}

	public static int getDisplayHeight(Activity activity){
		
		 return getDisplayMetrics(activity).heightPixels;
	}
	
}
