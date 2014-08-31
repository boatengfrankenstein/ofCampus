package com.app.ofcampus;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.offcampus.fragments.HomeFragment;
import com.app.offcampus.fragments.MyAlertFragment;
import com.app.offcampus.fragments.MyClassifiedFragment;
import com.app.offcampus.fragments.MyJobProfileFragment;
import com.app.offcampus.fragments.MySettingFragment;
import com.app.ui.AbsActionBarActivity;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.parse.ParseUser;

public class HomeScreenActivity extends AbsActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mMenuTitles;


    @Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home_screen);

		initUI();

	}

	@Override
	protected void initUI() {
		super.initUI();
		mTitle = mDrawerTitle = getResources().getString(R.string.app_name);
		mMenuTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mMenuTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor( R.color.main_background_color )) );
		getSupportActionBar().setIcon( getApplicationContext().getResources().getDrawable( R.drawable.logo_with_back_button ) );
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                 
				mDrawerLayout,         
				R.drawable.ic_drawer,  
				R.string.drawer_open,  
				R.string.drawer_close  
				) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		selectItem(0);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return false;
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new MySettingFragment();
			break;
		case 2:
			fragment = new MyClassifiedFragment();
			break;
		case 3:
			fragment = new MyJobProfileFragment();
			break;
		case 4:
			fragment = new MyAlertFragment();
			break;
			
		case 5:
			
			if(isNetworkAvailable(activity)) {
				showAlertForLogout();
			}
			
			break;

		default:
			break;
		}

		if(position==5){
			return;
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		mDrawerList.setItemChecked(position, true);
		setTitle(mMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	private void showAlertForLogout() {
		
		
		showAlert(getString(R.string.logout_), getString(R.string.are_you_sure_wants_to_logout_), getString(R.string.yes), getString(R.string.no), context, 
				new DialogDialogDeligate() {
			
			@Override
			public void positiveButtonClick(DialogInterface dialog, int id) {
				
				ParseUser.logOut();
			
				getSharedPreference(activity).clearAllPreferences();
				
				dialog.dismiss();
				
				switchToActivity(activity, LoginScreenActivity.class, null);
			}
			
			@Override
			public void negativeButtonClick(DialogInterface dialog, int id) {
					dialog.dismiss();
			}
		});
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);

	}

}

