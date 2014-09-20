package com.app.ofcampus;

import com.app.ofcampus.R;
import com.app.adapters.JobListAdapter;
import com.app.models.JobItem;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import static com.app.ofcampus.JobsActivity.JOB_TYPE;

public class JobListsActivity extends Activity {

	private JobListAdapter adapter;

	public static final String KEY_TITLE="job_title";
	public static final String KEY_DETAILS="job_details";
	public static final String KEY_PUBDATE = "job_pub_date";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jobs_lists);
		final String jobType;
		Intent intent = getIntent();
		if (null != intent) {
			jobType = intent.getStringExtra(JOB_TYPE);

		}
		adapter = new JobListAdapter(this, getData());
		ListView list = (ListView) findViewById(R.id.jobListText);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

				
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
				JobItem item = (JobItem) adapter.getItem(position);

				Intent intent = new Intent(getApplicationContext(), JobDetailsActivity.class);
				intent.putExtra(KEY_TITLE, item.getTitle());
				intent.putExtra(KEY_PUBDATE, item.getPubDate());
				intent.putExtra(KEY_DETAILS, item.getDetails());

				startActivity(intent); 
			}
		});
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
}