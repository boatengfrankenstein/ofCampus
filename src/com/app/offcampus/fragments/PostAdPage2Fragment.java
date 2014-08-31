package com.app.offcampus.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.ofcampus.DataStore;
import com.app.ofcampus.R;
import com.app.ui.AbstractFragment;

@SuppressLint( "ValidFragment" )
public class PostAdPage2Fragment extends AbstractFragment{

	private EditText edtTextTitle;
	private EditText edtTextDescription;
	private EditText edtSuburb;
	private EditText edtTextArea;
	private EditText edtTextPrice;
	private EditText edtCountry;
	private EditText edtCity;
	private TextView spinnerAppartmentType;
	private Button btnNextPage;
	private Page2NextBtnClick page2NextBtnClick; 
	
	public PostAdPage2Fragment() {
	}

	public PostAdPage2Fragment(Page2NextBtnClick page2NextBtnClick) {
		this.page2NextBtnClick = page2NextBtnClick;
	}

	public static PostAdPage2Fragment getInstance(Page2NextBtnClick page2NextBtnClick) {
		
		PostAdPage2Fragment postAdPage2Fragment = new PostAdPage2Fragment(page2NextBtnClick);

		return postAdPage2Fragment;

	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			/*@Nullable*/ ViewGroup container, /*@Nullable*/ Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_post_an_ad_two, null);

		return view;
	}

	@Override
	public void onActivityCreated(/*@Nullable*/ Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initUI(getView());
	}

	private void initUI(View view) {

		edtTextTitle = ( EditText ) view.findViewById(R.id.title_edittext);
		edtTextDescription = ( EditText ) view.findViewById(R.id.description_edittext);
		edtSuburb = ( EditText ) view.findViewById(R.id.edt_suburb);
		edtTextArea = ( EditText ) view.findViewById(R.id.area_edittext);
		edtTextPrice = ( EditText ) view.findViewById(R.id.price_edittext);
		edtCountry = ( EditText ) view.findViewById( R.id.country_spinner );
		edtCity = ( EditText ) view.findViewById( R.id.city_spinner );
		spinnerAppartmentType = ( TextView ) view.findViewById( R.id.appartment_type_spinner );
		btnNextPage = (Button) view.findViewById(R.id.btn_nextPage);
		btnNextPage.setOnClickListener(new NextPageBtnClickListener());
	}
	
	private class NextPageBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			boolean isValid = checkValidation();
			
			if(isValid) {
				DataStore.setTitle(edtTextTitle.getText().toString());
				DataStore.setDescription(edtTextDescription.getText().toString());
				DataStore.setCountry(edtCountry.getText().toString());
				DataStore.setCity(edtCity.getText().toString());
				DataStore.setApartmentType(spinnerAppartmentType.getText().toString());
				DataStore.setPrice(Integer.parseInt(edtTextPrice.getText().toString()));
				DataStore.setAreaInSquareFt(edtTextArea.getText().toString());
				DataStore.setSuburb(edtSuburb.getText().toString());
				page2NextBtnClick.nextBtnClick();
			}
		}

	}
	

	private boolean checkValidation() {
		
		String title = edtTextTitle.getText().toString();
		if(TextUtils.isEmpty(title)) {
			showAlert(getActivity().getString(R.string.alert), getActivity().getString(R.string.enter_ad_title_), getString(R.string.ok), null, getActivity(), null);
			return false;
		}
		
		String description = edtTextDescription.getText().toString();
		if(TextUtils.isEmpty(description)) {
			showAlert(getActivity().getString(R.string.alert), getActivity().getString(R.string.enter_ad_description_), getString(R.string.ok), null, getActivity(), null);
			return false;
		}
		
		String country = edtCountry.getText().toString();
		if(TextUtils.isEmpty(country)) {
			showAlert(getActivity().getString(R.string.alert), getActivity().getString(R.string.enter_country), getString(R.string.ok), null, getActivity(), null);
			return false;
		}
		
		String city = edtCity.getText().toString();
		if(TextUtils.isEmpty(city)) {
			showAlert(getActivity().getString(R.string.alert), getActivity().getString(R.string.enter_city_), getString(R.string.ok), null, getActivity(), null);
			return false;
		}
		
		String suburb = edtSuburb.getText().toString();
		if(TextUtils.isEmpty(suburb)) {
			showAlert(getActivity().getString(R.string.alert), getActivity().getString(R.string.enter_suburb_), getString(R.string.ok), null, getActivity(), null);
			return false;
		}
		
		String area = edtSuburb.getText().toString();
		if(TextUtils.isEmpty(area)) {
			showAlert(getActivity().getString(R.string.alert), getActivity().getString(R.string.enter_area_in_sq_ft_), getString(R.string.ok), null, getActivity(), null);
			return false;
		}
		
		
		String price = edtTextPrice.getText().toString();
		if(TextUtils.isEmpty(price)) {
			showAlert(getActivity().getString(R.string.alert), getActivity().getString(R.string.enter_area_in_inr_), getString(R.string.ok), null, getActivity(), null);
			return false;
		}
		
		
		return true;
	}
	
	
	public interface Page2NextBtnClick {
		public void nextBtnClick();
	}
}
