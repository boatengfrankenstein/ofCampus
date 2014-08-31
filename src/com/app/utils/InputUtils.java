package com.app.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class InputUtils {

	public static void hideKeyboard(Context context, EditText view) {
		
		InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		
	}

}
