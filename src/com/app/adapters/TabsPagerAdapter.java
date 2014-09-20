package com.app.adapters;
 

import com.app.models.JobItem;
import com.app.ofcampus.R;
import com.app.offcampus.fragments.ClassifiedsFragment;
import com.app.offcampus.fragments.JobsFragment;
import com.app.offcampus.fragments.MeetupsFragment;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        if (index == 0) {
            return new JobsFragment();
        	
		} else if (index == 1) {
			
            return new ClassifiedsFragment();
		} else if (index == 2) {
			
            return new MeetupsFragment();
		}
 
        return null;
    }
 
    @Override
    public int getCount() {
        // Change this so that it does not have to be changed manually
        return 3;
    }
 
}