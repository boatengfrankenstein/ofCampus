package com.app.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.app.models.MyClassified;
import com.app.ofcampus.viewholder.MyClassifiedViewHolder;

public class MyClassifiedAdapter extends BaseAdapter {

	private ArrayList<MyClassified> myClassifedList;
	private Activity mActivity;
	
	public MyClassifiedAdapter(Activity mActivity,ArrayList<MyClassified> myClassifedList) {
		this.mActivity = mActivity;
		this.myClassifedList = myClassifedList;
	}
	
	@Override
	public int getCount() {
		return myClassifedList.size();
	}

	@Override
	public MyClassified getItem(int position) {
		return myClassifedList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		MyClassifiedViewHolder classifiedViewHolder = new  MyClassifiedViewHolder(mActivity);
		
		if(convertView==null) {
			
			convertView = classifiedViewHolder.getConvertView();
			
			convertView.setTag(classifiedViewHolder);
			
		}else{
			classifiedViewHolder = (MyClassifiedViewHolder) convertView.getTag();
		}
		
		classifiedViewHolder.initData(myClassifedList.get(position));
		
		classifiedViewHolder.applyData();
		
		return convertView;
	}

	
	
}
