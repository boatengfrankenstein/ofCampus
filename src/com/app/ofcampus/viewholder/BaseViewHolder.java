package com.app.ofcampus.viewholder;

import android.view.View;

public abstract class BaseViewHolder {

	protected View view;
	
	public abstract void applyData();
	
	public abstract View getConvertView();

	public void initData(Object object) {
		
	}
	
}
