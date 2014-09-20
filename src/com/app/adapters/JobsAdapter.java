package com.app.adapters;

import java.util.ArrayList;

import com.app.utils.BmpItem;

import com.app.ofcampus.R;
import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class JobsAdapter extends ArrayAdapter {
	
	Context context; 
	int layoutResourceId; 
	ArrayList<BmpItem> data = new ArrayList<BmpItem>(); 
	
	@SuppressWarnings("unchecked")
	public JobsAdapter(Context context, int layoutResourceId, ArrayList<BmpItem> data) { 
		super(context, layoutResourceId, data); 
		this.layoutResourceId = layoutResourceId; 
		this.context = context; 
		this.data = data; 
		} 
	
	@Override 
	public View getView(int position, View convertView, ViewGroup parent) 
	{ 
		View row = convertView; 
		RecordHolder holder = null; 
		if (row == null) { 
			LayoutInflater inflater = ((Activity) context).getLayoutInflater(); 
			row = inflater.inflate(layoutResourceId, parent, false); 
			holder = new RecordHolder(); 
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder); 
			} 
		else { 
			holder = (RecordHolder) row.getTag(); 
			} 
		BmpItem item = data.get(position); 
		holder.txtTitle.setText(item.getText()); 
		holder.imageItem.setImageBitmap(item.getImage()); 
		return row; 
		} 
	static class RecordHolder 
	{ 
		TextView txtTitle; 
		ImageView imageItem; 
		} 
	}
