package com.app.ofcampus;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import static com.app.ofcampus.JobListsActivity.KEY_TITLE;
import static com.app.ofcampus.JobListsActivity.KEY_PUBDATE;
import static com.app.ofcampus.JobListsActivity.KEY_DETAILS;

public class JobDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jobs_details);

		String headline = "";
		String details = "";
		String pubDate = "";

		Intent intent = getIntent();
		if (null != intent) {
			headline = intent.getStringExtra(KEY_TITLE);
			details = intent.getStringExtra(KEY_DETAILS);
			pubDate = intent.getStringExtra(KEY_PUBDATE);
		}

		TextView headlineTxt = (TextView) findViewById(R.id.title);
		headlineTxt.setText(headline);

		TextView pubdateTxt = (TextView) findViewById(R.id.pub_date);
		pubdateTxt.setText(pubDate);

		TextView descriptionTxt = (TextView) findViewById(R.id.description);
		descriptionTxt.setText(details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}