package com.app.offcampus.fragments;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.ofcampus.DataStore;
import com.app.ofcampus.R;
import com.app.ui.AbstractFragment;
import com.app.utils.AlertMessage;
import com.app.utils.AlertMessage.SingleChoiceItemListener;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@SuppressLint({ "InflateParams", "ValidFragment" })
public class PostAdPage1Fragment extends AbstractFragment{

	private TextView edtCategory;
	private EditText edtTextMobileNumber;
	private EditText edtTextWhatsappNumber;
	private EditText edtTextEmailAddress;
	private TextView edtSubCategory;
	private TextView edtOption;
	private EditText edtPostedBy;
	private ProgressDialog progressDialog;
	private List<ParseObject> categoryList = new ArrayList<ParseObject>();
	private List<ParseObject> subcategoryList = new ArrayList<ParseObject>();
	private List<ParseObject> optionList = new ArrayList<ParseObject>();
	private String selectedCategory;
	private String selectedSubcategory;
	private Page1NextBtnClick page1NextBtnClick;
	private View btnNextPage;
	int selectedCategoryPos = -1;
	int selectedSubCategoryPos = -1;
	int selectedOptionPos = -1;
	public PostAdPage1Fragment() {
	}
	
	public PostAdPage1Fragment(Page1NextBtnClick page1NextBtnClick) {
		this.page1NextBtnClick = page1NextBtnClick;
	}

	public static PostAdPage1Fragment getInstance(Page1NextBtnClick page1NextBtnClick) {
		PostAdPage1Fragment postAdPage1Fragment = new PostAdPage1Fragment(page1NextBtnClick);
		return postAdPage1Fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			/*@Nullable*/ ViewGroup container, /*@Nullable*/ Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_post_an_ad_one, null);
		return view;
	}

	@Override
	public void onActivityCreated(/*@Nullable*/ Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		intUI(getView());
		hideKeyboard(getActivity(), edtPostedBy);
		setData();
	}


	protected void intUI(View view) {
		progressDialog = getLoadingDialog(context, null,getString(R.string.loading_), false);
		edtPostedBy = (EditText) view.findViewById(R.id.edt_posted_by);
		edtTextMobileNumber = ( EditText ) view.findViewById(R.id.mobile_no_edittext);
		edtTextWhatsappNumber = ( EditText ) view.findViewById(R.id.whatsapp_no_edittext);
		edtTextEmailAddress = ( EditText ) view.findViewById(R.id.email_edittext);
		edtCategory = ( TextView ) view.findViewById( R.id.category_spinner );
		edtCategory.setOnClickListener(new PageItemClickListeners());
		edtSubCategory = ( TextView ) view.findViewById( R.id.sub_category_spinner );
		edtSubCategory.setOnClickListener(new PageItemClickListeners());
		edtOption = (TextView) view.findViewById(R.id.option_spinner);
		edtOption.setOnClickListener(new PageItemClickListeners());
		btnNextPage = (Button) view.findViewById(R.id.btn_nextPage);
		btnNextPage.setOnClickListener(new NextPageBtnClickListener());
	}
	
	private class NextPageBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			boolean isVaild = checkValidation();
			
			if(isVaild) {
				
				DataStore.setPostedBy(edtPostedBy.getText().toString());
				DataStore.setMobileNumber(edtTextEmailAddress.getText().toString());
				DataStore.setWhatsAppNumber(edtTextWhatsappNumber.getText().toString());
				DataStore.setEmailId(edtTextEmailAddress.getText().toString());
				
				if(categoryList.size()>0) {
					DataStore.setCategory(categoryList.get(selectedCategoryPos));
				}
				
				if(subcategoryList.size()>0){
					DataStore.setSubCategory(subcategoryList.get(selectedCategoryPos));
				}
				
				if(optionList.size()>0) {
					DataStore.setOption(optionList.get(selectedOptionPos));
				}
				
				page1NextBtnClick.nextBtnClick();
			}
		}
		
	}
	
	public boolean checkValidation() {
		
		String postedBy = edtPostedBy.getText().toString();
		if(TextUtils.isEmpty(postedBy)) {
			showAlert(getString(R.string.alert), getActivity().getString(R.string.enter_posted_by_),getString(R.string.ok), null, context, null);
			return false;
		}
		
		String mobileNumber = edtTextMobileNumber.getText().toString();
		if(TextUtils.isEmpty(mobileNumber)) {
			showAlert(getString(R.string.alert), getActivity().getString(R.string.enter_mobile_number_),getString(R.string.ok), null, context, null);
			return false;
		}
		
		if(selectedCategoryPos == -1) {
			showAlert(getString(R.string.alert),getActivity().getString(R.string.select_category_),getString(R.string.ok), null, context, null);
			return false;
		}
		
		if(selectedSubCategoryPos == -1) {
			showAlert(getString(R.string.alert),getActivity().getString(R.string.select_subcategory_),getString(R.string.ok), null, context, null);
			return false;
		}
		
		if(selectedOptionPos == -1) {
			showAlert(getString(R.string.alert),getActivity().getString(R.string.select_option_),getString(R.string.ok), null, context, null);
			return false;
		}
		
		return true;
	}

	
	private class PageItemClickListeners implements OnClickListener {

		@Override
		public void onClick(View v) {

			int id = v.getId();

			if (id == R.id.category_spinner) {
				if(categoryList.size()==0) {
					getCategory();
				}else{
					showCategorySelectionDialog();
				}
			} else if (id == R.id.sub_category_spinner) {
				if(!TextUtils.isEmpty(selectedCategory)) {
					getSubcategory(selectedCategory);
				}else{
					Toast.makeText(context, "Select category first.", Toast.LENGTH_SHORT).show();
				}
			} else if (id == R.id.option_spinner) {
				if(!TextUtils.isEmpty(selectedSubcategory)) {
					getOptionList(selectedSubcategory);
				}else{
					Toast.makeText(context, "Select subcategory first.", Toast.LENGTH_SHORT).show();
				}
			} else {
			}
		}
	}


	public void getOptionList(String subcategoryId) {

		progressDialog.show();
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("SubcategoryOption");
		query.whereEqualTo("subcategory_id", subcategoryId);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				progressDialog.dismiss();
				if(arg0!=null) {
					optionList.clear();
					optionList.addAll(arg0);
					showOptionSelectionDialog();
				}
			}
		});
	}
	
	public void getCategory() {

		progressDialog.show();
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ClassfiedCategory");
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				progressDialog.dismiss();
				if(arg0!=null) {
					categoryList.addAll(arg0);
					showCategorySelectionDialog();
				}
			}
		});

	}
	
	public void showCategorySelectionDialog() {
		String [] categoryItems = new String[categoryList.size()];
		for (int i = 0; i < categoryList.size(); i++) {
			categoryItems[i] = categoryList.get(i).getString("category");
		}
		AlertMessage.singleChoiceDialog(context,getActivity().getString(R.string.select_category), categoryItems, new CategorySelectionListener());
	}

	private class CategorySelectionListener implements SingleChoiceItemListener {
		@Override
		public void onItemSelected(int which) {
			selectedCategoryPos = which;
			edtCategory.setText(categoryList.get(which).getString("category"));
			selectedCategory = categoryList.get(which).getObjectId();
		}
	}

	protected void getSubcategory(String objectId) {
		progressDialog.show();
		ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("ClassifedSubcategory");
		parseQuery.whereEqualTo("category_id", objectId);
		parseQuery.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				progressDialog.dismiss();
				if(arg0!=null) {
					subcategoryList.clear();
					subcategoryList.addAll(arg0);
					showSubcategorySelectionDialog();
				}
			}
		});

	}

	public void showSubcategorySelectionDialog() {

		String [] categoryItems = new String[subcategoryList.size()];
		for (int i = 0; i < subcategoryList.size(); i++) {
			categoryItems[i] = subcategoryList.get(i).getString("subcategory");
		}
		AlertMessage.singleChoiceDialog(context,"Select Subcategory", categoryItems, new SubCategorySelectionListener());
	}

	private class SubCategorySelectionListener implements SingleChoiceItemListener {

		@Override
		public void onItemSelected(int which) {
			edtSubCategory.setText(subcategoryList.get(which).getString("subcategory"));
			selectedSubcategory = subcategoryList.get(which).getObjectId();
			selectedSubCategoryPos = which;
		}
	}
	
	public void showOptionSelectionDialog() {
		String [] categoryItems = new String[optionList.size()];
		for (int i = 0; i < optionList.size(); i++) {
			categoryItems[i] = optionList.get(i).getString("option");
		}
		AlertMessage.singleChoiceDialog(context,"Select Option", categoryItems, new OptionSelectionListener());
	}

	private class OptionSelectionListener implements SingleChoiceItemListener {

		@Override
		public void onItemSelected(int which) {
			selectedOptionPos = which;
			edtOption.setText(optionList.get(which).getString("option"));
		}
	}

	private void setData() {

		ParseUser parseUser = getCurrentUser();
		edtTextEmailAddress.setText(parseUser.getEmail());
		edtPostedBy.setText(parseUser.getString(USER_FULL_NAME));
		edtTextMobileNumber.setText(parseUser.getString(USER_MOBILE_NUMBER));
		edtTextWhatsappNumber.setText(parseUser.getString(USER_WHATS_APP_NUMBER));
	}


	public ParseUser getCurrentUser() {
		return ParseUser.getCurrentUser();
	}

	public interface Page1NextBtnClick {
		public void nextBtnClick();
	}
	
}
