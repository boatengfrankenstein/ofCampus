package com.app.offcampus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.ofcampus.R;
import com.app.ui.AbstractFragment;

public class HelpFragment extends AbstractFragment{

	public static HelpFragment newInstance() {
		
		HelpFragment f = new HelpFragment();

		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_help, null);
		
		return view;
		
	}

}
