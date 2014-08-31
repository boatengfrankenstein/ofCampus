package com.app.ofcampus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.offcampus.fragments.PostAdPage1Fragment;
import com.app.offcampus.fragments.PostAdPage1Fragment.Page1NextBtnClick;
import com.app.offcampus.fragments.PostAdPage2Fragment;
import com.app.offcampus.fragments.PostAdPage2Fragment.Page2NextBtnClick;
import com.app.offcampus.fragments.PostAdPage3Fragment;
import com.app.ui.AbsActionBarActivity;

public class PostAdPageActivity extends AbsActionBarActivity
{

	private ViewPager adPager;
	private int currentPage = 0;
	private TextView txtPageNumber;
	private ImageView imgHome;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_post_an_ad );
		initUI();
	}

	@Override
	protected void initUI() {
		super.initUI();
		getSupportActionBar().hide();
		setUpFooter();
		imgHome = (ImageView) findViewById(R.id.rel_header_img_view);
		imgHome.setOnClickListener(new HomeClickListener());
		adPager = (ViewPager) findViewById(R.id.pager);
		adPager.setAdapter(new AdPagePagerAdapter(getSupportFragmentManager()));
		adPager.setOffscreenPageLimit(3);
		txtPageNumber = (TextView) findViewById(R.id.txt_page_number_textview);
		setListeners();
		setData();
	}
	
	private class HomeClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			onBackPressed();
		}
		
	}
	
	private void setData() {
		txtPageNumber.setText(currentPage+1+" of 3");
	}

	private void setListeners() {
		adPager.setOnPageChangeListener(new AdPagerListener());
	}

	
	private class AdPagerListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			currentPage = arg0;
			txtPageNumber.setText(currentPage+1+" of 3");
		}
		
	}

	private class AdPagePagerAdapter extends FragmentStatePagerAdapter {

		private static final int NUM_PAGES = 3;

		public AdPagePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = PostAdPage1Fragment.getInstance(new FragmentOnChange());
				break;
			case 1:
				fragment = PostAdPage2Fragment.getInstance(new FragmentTwoChange());
				break;
			case 2:
				fragment = PostAdPage3Fragment.getInstance();
				break;

			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
	
	private class FragmentOnChange implements Page1NextBtnClick {

		@Override
		public void nextBtnClick() {

			currentPage++;
			adPager.setCurrentItem(currentPage);
		}
		
	}
	
	private class FragmentTwoChange implements Page2NextBtnClick {

		@Override
		public void nextBtnClick() {

			currentPage++;
			adPager.setCurrentItem(currentPage);
		}
		
	}

}
