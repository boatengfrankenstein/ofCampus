package com.app.offcampus.fragments;


import static com.app.ofcampus.JobsActivity.JOB_TYPE;

import java.util.ArrayList;

import com.app.adapters.JobListAdapter;
import com.app.models.JobItem;
import com.app.ofcampus.JobDetailsActivity;
import com.app.ofcampus.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
 
public class JobsFragment extends Fragment {
 
  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_jobs_lists, container, false);
         
        return rootView;
    }
}
 /*   
	private JobListAdapter adapter;

	public static final String KEY_TITLE="job_title";
	public static final String KEY_DETAILS="job_details";
	public static final String KEY_PUBDATE = "job_pub_date";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_jobs_lists, container, false);
       
		//adapter = new JobListAdapter(getActivity(), getData());
		ListView list = (ListView) rootView.findViewById(R.id.jobListText);
		//list.setAdapter(adapter);

		/*list.setOnItemClickListener(new OnItemClickListener() {

				
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
				JobItem item = (JobItem) adapter.getItem(position);

				Intent intent = new Intent(getActivity().getApplicationContext(), JobDetailsActivity.class);
				intent.putExtra(KEY_TITLE, item.getTitle());
				intent.putExtra(KEY_PUBDATE, item.getPubDate());
				intent.putExtra(KEY_DETAILS, item.getDetails());

				startActivity(intent); 
			}
			
		});
		return list;
	}

	private ArrayList<JobItem> getData() {
		ArrayList<JobItem> newsList = new ArrayList<JobItem>();
		String[] headlines = getResources().getStringArray(R.array.jobs_title);
		String[] pubDate = getResources().getStringArray(R.array.jobs_pubdate);
		String[] details = getResources().getStringArray(R.array.jobs_details);

		for (int i = 0; i < headlines.length; i++) {
			JobItem item = new JobItem();
			item.setTitle(headlines[i]);
			item.setPubDate(pubDate[i]);
			item.setDetails(details[i]);
			newsList.add(item);
		}
		return newsList;
	}
}*/