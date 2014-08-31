package com.app.ofcampus.viewholder;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.models.MyClassified;
import com.app.ofcampus.R;

public class MyClassifiedViewHolder extends BaseViewHolder{

	private Activity mActivity;
	private ImageView imgClassifiedImage;
	private TextView txtDescription;
	private TextView txtPrice;
	private TextView txtDate;
	private MyClassified myClassified;
	public MyClassifiedViewHolder(Activity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public void applyData() {
		txtDescription.setText(myClassified.getAdDescription());
		
		if(myClassified.getMaxPrice()==0){
			txtPrice.setText(myClassified.getMinPrice()+" INR");
		}else{
			txtPrice.setText(myClassified.getMinPrice()+"-"+myClassified.getMaxPrice()+" INR");
		}
		
		txtDate.setText(myClassified.getCreatedOn());
	}

	@Override
	public View getConvertView() {
		
		view = LayoutInflater.from(mActivity).inflate(R.layout.my_classified_list_item, null);
		imgClassifiedImage = (ImageView) view.findViewById(R.id.img_classified_image);
		txtDescription = (TextView) view.findViewById(R.id.txt_ad_description);
		txtPrice = (TextView) view.findViewById(R.id.txt_price);
		txtDate = (TextView) view.findViewById(R.id.txt_date);
		
		return view;
	}
	
	@Override
	public void initData(Object object) {
		myClassified = (MyClassified) object;
	}
}
