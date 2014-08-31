package com.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class DateUtils {

	public static String getLocaleDateString(Date gmtString) {

		SimpleDateFormat localeDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return localeDateFormat.format(gmtString);

	}

}
