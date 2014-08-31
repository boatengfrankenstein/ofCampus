package com.app.ofcampus.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.ofcampus.R;


public class LoadingView extends LinearLayout{
	
	private TextView txtLoading;

	public LoadingView(Context context) {
		super(context);
		
		initView();
	}

	@SuppressLint("InflateParams")
	private void initView() {
		
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_loading, null);
		txtLoading = (TextView) view.findViewById(R.id.txt_loading);
		addView(view);
	}
	
	public void setLoadingMessage(String loadingMessage) {
		txtLoading.setText(loadingMessage);
	}

}
