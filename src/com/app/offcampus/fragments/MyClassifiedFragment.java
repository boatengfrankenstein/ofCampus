package com.app.offcampus.fragments;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.app.adapters.MyClassifiedAdapter;
import com.app.models.MyClassified;
import com.app.ofcampus.R;
import com.app.ofcampus.customviews.LoadingView;
import com.app.ui.AbstractFragment;
import com.app.utils.DateUtils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MyClassifiedFragment extends AbstractFragment {
	
	private ListView lvMyClassifed;
	private MyClassifiedAdapter adapter;
	private ArrayList<MyClassified> myClassifiedList;
	private RelativeLayout rltLayoutContainer;
	private View myClassifiedView;
	@SuppressLint("InflateParams")
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_classified, null);
		return view;
	}
	
	@Override
	public void onActivityCreated(/*@Nullable*/ Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		intUI();
		getMyClassifeds();
	}
	
	private void getMyClassifeds() {
		rltLayoutContainer.removeAllViews();
		LoadingView loadingView = new LoadingView(getActivity());
		loadingView.setLoadingMessage("Loading your classified...please wait...");
		rltLayoutContainer.addView(loadingView);
		ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("RealEstateClassified");
		parseQuery.whereEqualTo("postedBy", ParseUser.getCurrentUser().getObjectId());
		parseQuery.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				
				if(arg1!=null){
					rltLayoutContainer.removeAllViews();
				}else{
					if(arg0.size()>0){
						setData(arg0);
					}else{
						showAlert(getString(R.string.alert), getActivity()
								.getString(
										R.string.not_created_classifieds), getString(R.string.ok), null, context, null);
					}
				}
			}
		});
		
	}
	
	@SuppressLint("InflateParams")
	private void intUI() {
		myClassifiedView = LayoutInflater.from(getActivity()).inflate(R.layout.view_my_classified, null);
		lvMyClassifed = (ListView) myClassifiedView.findViewById(R.id.lv_my_classified);
		rltLayoutContainer = (RelativeLayout) getView().findViewById(R.id.rlt_layout_myclassified);
		setListeners();
	}
	
	private void setListeners() {

	}
	
	private void setData(List<ParseObject> arg0) {
		rltLayoutContainer.removeAllViews();
		rltLayoutContainer.addView(myClassifiedView);
		setUpFooter(myClassifiedView);
		MyClassified classified=null;
		
		myClassifiedList = new ArrayList<MyClassified>();
		
		for (int i = 0; i < arg0.size(); i++) {
			classified = new MyClassified();
			classified.setAdTitle(arg0.get(i).getString("title"));
			classified.setAdDescription(arg0.get(i).getString("description"));
			classified.setAreaInSqft(arg0.get(i).getInt("areaInSqFt"));
			classified.setMinPrice(arg0.get(i).getInt("minPrice"));
			classified.setMaxPrice(arg0.get(i).getInt("maxPrice"));
			classified.setCity("country");
			classified.setCountry("city");
			classified.setSuburb("suburb");
			classified.setApartmentType("apartmentTypeText");
			classified.setCreatedOn(DateUtils.getLocaleDateString(arg0.get(i).getCreatedAt()));
			myClassifiedList.add(classified);
		}
		
		adapter = new MyClassifiedAdapter(getActivity(), myClassifiedList);
		lvMyClassifed.setAdapter(adapter);
	}
}
