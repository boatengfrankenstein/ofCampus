package com.app.adapters;

import com.app.models.JobItem;
import com.app.ofcampus.R;
import com.app.offcampus.fragments.JobsFragment;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JobListAdapter extends BaseAdapter {

	private ArrayList<JobItem> listData;
	private LayoutInflater layoutInflater;
	private Context context;

	public JobListAdapter(Context context, ArrayList<JobItem> listData) {
		this.listData = listData;
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
	}


	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.activity_jobs_lists_row, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.jobTitle);
			holder.pubDate = (TextView) convertView.findViewById(R.id.pubDate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(listData.get(position).getTitle());
		holder.pubDate.setText(listData.get(position).getPubDate());

		if (position % 2 == 1) {
			convertView.setBackgroundColor(context.getResources().getColor(R.color.list_row_color1));  
		} else {
			convertView.setBackgroundColor(context.getResources().getColor(R.color.list_row_color2));  
		}

		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView pubDate;
	}

}