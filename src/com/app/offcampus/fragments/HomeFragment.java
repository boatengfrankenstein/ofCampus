package com.app.offcampus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.app.ofcampus.ClassifiedsActivity;
import com.app.ofcampus.JobsActivity;
import com.app.ofcampus.R;
import com.app.ui.AbstractFragment;

public class HomeFragment extends AbstractFragment{
	private Button btnClassifieds;
	private Button btnJobs;
	private TextView txtHelp;
	private TextView txtContactUs;
	private TextView txtLegal;

	public static HomeFragment newInstance() {

		HomeFragment f = new HomeFragment();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_home, null);

		return view;

	}

	public void onActivityCreated( Bundle savedInstanceState ) 
	{
		super.onActivityCreated( savedInstanceState );
		

		intUI();
	}

	protected void intUI() {
		
		setUpFooter(getView());
		
		btnClassifieds = (Button) getView().findViewById(R.id.btn_classifields);
        btnJobs = (Button) getView().findViewById(R.id.btn_jobs); 
		
		setListeners();

	}

	private void setListeners() {

		btnClassifieds.setOnClickListener(new ClassifiedsBtnClassClickListener());
		btnJobs.setOnClickListener(new ClassifiedsBtnJobsClickListener());

	}

	private class ClassifiedsBtnClassClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {

			switchToActivity(getActivity(), ClassifiedsActivity.class, null);
		}

	}
	private class ClassifiedsBtnJobsClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {

			switchToActivity(getActivity(), JobsActivity.class, null);
		}

	}


}
