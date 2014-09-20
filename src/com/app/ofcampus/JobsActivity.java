package com.app.ofcampus;

import java.util.ArrayList;

import android.content.Intent;
import android.content.ClipData.Item;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.app.adapters.JobsAdapter;
import com.app.ui.AbsActionBarActivity;
import com.app.utils.BmpItem;

public class JobsActivity extends AbsActionBarActivity
{
	GridView gridView; 
	ArrayList<BmpItem> gridArray = new ArrayList<BmpItem>(); 
	JobsAdapter jobsAdapter; 
	public static final String JOB_TYPE="jobType";
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_jobs_screen); //set grid view item 
		
		Bitmap consultingIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.consulting); 
		Bitmap financeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.fiance);
		Bitmap operationsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.operations);
		Bitmap salesIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.sales);
		Bitmap nonprofitIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.nonprofit);
		Bitmap hrIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.human);
		Bitmap itIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.tech);
		
		gridArray.add(new BmpItem(consultingIcon,"Consulting")); 
		gridArray.add(new BmpItem(financeIcon,"Finance"));
		gridArray.add(new BmpItem(operationsIcon,"Operations"));
		gridArray.add(new BmpItem(salesIcon,"Sales and Marketing"));
		gridArray.add(new BmpItem(nonprofitIcon,"Non-Profit"));
		gridArray.add(new BmpItem(hrIcon,"Human Resources"));
		gridArray.add(new BmpItem(itIcon,"IT & Systems"));
		
		gridView = (GridView) findViewById(R.id.gridViewCustom); 
		jobsAdapter = new JobsAdapter(this, R.layout.activity_jobs_screen_custom, gridArray); 
		gridView.setAdapter(jobsAdapter); 
		gridView.setOnItemClickListener(new OnItemClickListener() { 
			@Override public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) 
			{ 
				//Toast.makeText(getApplicationContext(),gridArray.get(position).getText(), Toast.LENGTH_SHORT).show();
				//switchToActivity(activity, JobListsActivity.class, null);
				Intent intent = new Intent(getApplicationContext(), JobListsActivity.class);
				intent.putExtra(JOB_TYPE, gridArray.get(position).getText());
				startActivity(intent);
			} 
		}); 
	} 
}


