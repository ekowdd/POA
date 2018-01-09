package com.jwsdigital.poa.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eko on 10/30/17.
 */

public class TabsAdapter extends FragmentPagerAdapter {
private final List<Fragment> mFragments = new ArrayList<>();
private final List<String> mFragmentTitles = new ArrayList<>();
public TabsAdapter ( FragmentManager fm ) {
	super(fm);
}

@Override
public Fragment getItem ( int position ) {
	return mFragments.get(position);
}

@Override
public int getCount () {
	return mFragments.size();
}
public void addFrag(Fragment fragment, String titles){
	mFragments.add(fragment);
	mFragmentTitles.add(titles);
}

@Override
public CharSequence getPageTitle ( int position ) {
	return mFragmentTitles.get(position);
}
}
