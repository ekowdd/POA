package com.jwsdigital.poa.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jwsdigital.poa.R;

public class PackageDetailsActivity extends AppCompatActivity {
Toolbar toolbar;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_package_details);
	toolbar = (Toolbar ) findViewById(R.id.toolbar);
	toolbar.setNavigationIcon(R.drawable.back);
}
}
