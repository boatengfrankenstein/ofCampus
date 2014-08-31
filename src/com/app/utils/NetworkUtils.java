package com.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

	public static boolean isNetworkAvailable(Context context){

		ConnectivityManager cm =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		
		if (netInfo != null && ( netInfo.isConnectedOrConnecting() || netInfo.isConnected()) ) {
			
			return true;
		}else{

			return false;
		}
	}
}
