package com.app.offcampus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.ui.AbstractFragment;

public class ContactUsFragment extends AbstractFragment{

	public static ContactUsFragment newInstance() {
		
		ContactUsFragment f = new ContactUsFragment();

		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
