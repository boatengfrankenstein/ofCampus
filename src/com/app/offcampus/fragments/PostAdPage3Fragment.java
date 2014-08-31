package com.app.offcampus.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.ofcampus.DataStore;
import com.app.ofcampus.R;
import com.app.ui.AbstractFragment;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.app.utils.Logger;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PostAdPage3Fragment extends AbstractFragment{

	private Button btnPostClassifieds;
	private ImageView imgViewPicture;
	private ImageView imgViewAddPicture;
	private EditText edtMoreDetail;
	private static final int PICK_IMAGE = 1;
	private ProgressDialog progressDialog;

	public static PostAdPage3Fragment getInstance() {

		PostAdPage3Fragment postAdPage3Fragment = new PostAdPage3Fragment();

		return postAdPage3Fragment;

	}


	@Override
	public View onCreateView(LayoutInflater inflater,
			/*@Nullable*/ ViewGroup container, /*@Nullable*/ Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_post_an_ad_three, null);

		initUI(view);

		return view;
	}

	@Override
	public void onActivityCreated(/*@Nullable*/ Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initUI(getView());
	}

	private void initUI(View view) {

		btnPostClassifieds = (Button) view.findViewById(R.id.btn_postClassifieds);
		imgViewPicture = (ImageView) view.findViewById( R.id.pictureImageview );
		imgViewAddPicture = (ImageView) view.findViewById( R.id.addpictureImageview );
		edtMoreDetail = (EditText) view.findViewById(R.id.edt_more_detail);

		setListeners();
	}



	private void setListeners() {

		btnPostClassifieds.setOnClickListener(new PostClassifiedsBtnClickListener());

		imgViewAddPicture.setOnClickListener(new AddPictureClickListener());
	}

	private class PostClassifiedsBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			
			DataStore.setMoreDetai(edtMoreDetail.getText().toString());
			
			ParseObject parseObject = new ParseObject("RealEstateClassified");
			parseObject.put("postedBy", ParseUser.getCurrentUser().getObjectId());
			parseObject.put("categoryId", DataStore.getCategory().getObjectId());
			parseObject.put("subcategoryId", DataStore.getSubCategory().getObjectId());
			parseObject.put("categoryText", DataStore.getCategory().getString("category"));
			parseObject.put("subcategoryText", DataStore.getSubCategory().getString("subcategory"));
			parseObject.put("option", DataStore.getOption().getString("option"));
			parseObject.put("optionId", DataStore.getOption().getObjectId());
			parseObject.put("title", DataStore.getTitle());
			parseObject.put("description", DataStore.getDescription());
			parseObject.put("country", DataStore.getCountry());
			parseObject.put("city", DataStore.getCity());
			parseObject.put("suburb", DataStore.getSuburb());
			parseObject.put("typeApartmentId", DataStore.getApartmentType());
			parseObject.put("areaInSqFt", Integer.parseInt(DataStore.getAreaInSquareFt().trim()));
			parseObject.put("minPrice", DataStore.getPrice());
			parseObject.put("maxPrice", DataStore.getMaxPrice());
			parseObject.put("moreDescription", DataStore.getMoreDetai());
			progressDialog = getLoadingDialog(context, "Posting...", "Please wait while posting classified...", true);
			progressDialog.show();
			parseObject.saveInBackground(new PostClassifedCallBack());
		}

	}
	
	public class PostClassifedCallBack extends SaveCallback {

		@Override
		public void done(ParseException arg0) {
			
			progressDialog.dismiss();
			
			if(arg0!=null) {
				
				Logger.debug("Save error--->"+arg0.getLocalizedMessage());
				showAlert(getString(R.string.alert), "Error while saving classified.", getString(R.string.ok), null, context, null);
			}else{
				showAlert(getString(R.string.alert), "Your classified has been posted successfully.", getString(R.string.ok), null, context, new AdsSuccessFullPostDialogDeligate());
			}
			
		}
		
	}
	
	private class AdsSuccessFullPostDialogDeligate implements DialogDialogDeligate {

		@Override
		public void positiveButtonClick(DialogInterface dialog, int id) {
			dialog.dismiss();
			getActivity().finish();
		}

		@Override
		public void negativeButtonClick(DialogInterface dialog, int id) {
			
		}
		
	}

	private class AddPictureClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {

			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);//
			startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE );

		}

	}



}
