package com.jwsdigital.poa.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jwsdigital.poa.R;
import com.jwsdigital.poa.adapter.TabsAdapter;
import com.jwsdigital.poa.fragment.PackagesFragments;
import com.jwsdigital.poa.fragment.RequestQuotationFragments;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackagesActivity extends AppCompatActivity {
Toolbar toolbar;
SearchView searchView;
@BindView(R.id.viewpager)
ViewPager viewPager;
@BindView(R.id.tabs)
TabLayout tabLayout;

@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_packages);
	setTitle(null);
	ButterKnife.bind(this);
	toolbar = (Toolbar ) findViewById(R.id.toolbar);
	toolbar.setNavigationIcon(R.drawable.back);
	setSupportActionBar(toolbar);
	setupViewPager(viewPager);
	tabLayout.setupWithViewPager(viewPager);
}
public void setupViewPager(ViewPager viewPager){
	TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
	adapter.addFrag(new PackagesFragments(),"Packages");
	adapter.addFrag(new RequestQuotationFragments(),"Request Quotation");
	viewPager.setAdapter(adapter);
}

@Override
public boolean onCreateOptionsMenu ( Menu menu ) {
	getMenuInflater().inflate(R.menu.src_menu,menu);
//	SearchManager searchManager =
//			(SearchManager ) getSystemService(Context.SEARCH_SERVICE);
//	SearchView searchView =
//			(SearchView ) menu.findItem(R.id.search).getActionView();
//	searchView.setSearchableInfo(
//			searchManager.getSearchableInfo(getComponentName()));
	return true;
}

@Override
public boolean onContextItemSelected ( MenuItem item ) {
	return super.onContextItemSelected(item);
}

@Override
protected void onNewIntent ( Intent intent ) {
	handleIntent(intent);
}
private void handleIntent(Intent intent) {

	if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
		String query = intent.getStringExtra(SearchManager.QUERY);
		//use the query to search your data somehow
	}
}
}
