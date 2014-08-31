package com.app.offcampus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.ofcampus.R;
import com.app.ui.AbstractFragment;

public class ClassifiedsFragment extends AbstractFragment{

	private Button btnRealEstate;
	private ImageButton imgButtonPostAnAd;
	private TextView txtHelp;
	private TextView txtContactUs;
	private TextView txtLegal;
	private EditText edttextSearch;
	private ImageButton imgButtonSearch;

	public static ClassifiedsFragment newInstance() {

		ClassifiedsFragment f = new ClassifiedsFragment();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_classifieds, null);
		return view;
	}


	public void onActivityCreated( Bundle savedInstanceState ) 
	{
		super.onActivityCreated( savedInstanceState ); 
		intUI();
	}

	private void intUI() {
		setUpFooter(getView());
		btnRealEstate = (Button) getView().findViewById(R.id.btn_realestate);
		edttextSearch = (EditText) getView().findViewById(R.id.search_edit_text);
		imgButtonSearch = (ImageButton) getView().findViewById(R.id.search_button);
		setListeners();

	}

	private void setListeners() {
		btnRealEstate.setOnClickListener(new RealEstateBtnClickListener());
		imgButtonSearch.setOnClickListener(new SearchButtonClickListener());
	}

	private class RealEstateBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
		}

	}

	private class SearchButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
		}

	}

}
