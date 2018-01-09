package com.jwsdigital.poa.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.jwsdigital.poa.R;
import com.jwsdigital.poa.fragment.SplashFragmentOne;
import com.jwsdigital.poa.fragment.SplashFragmentThree;
import com.jwsdigital.poa.fragment.SplashFragmentTwo;

public class SplashActivity extends AppCompatActivity {
private Activity activity;
boolean laterThanNougt = false;
Fragment SlideOne,SlideTwo, SlideThree;
FragmentManager fragmentManager;
private Thread thread;
private static final int CEK_PERMISSION = 123;
private static final int RC_LOCATION_CONTACTS_PERM = 124;

@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.activity_splash);
	fragmentManager = getSupportFragmentManager();
	SlideOne = new SplashFragmentOne();

	SplasScreen();
}

@Override
protected void onResume () {
	super.onResume();
	onCekPermissionTask();
}

@Override
protected void onPause () {
	super.onPause();
	finish();
}
private void SplasScreen(){
	thread = new Thread(){
		@Override
		public void run () {
			try{
				sleep(1000);
			}catch ( InterruptedException e ){
				e.fillInStackTrace();
			}finally {
				SlideTwo = new SplashFragmentTwo();
				fragmentManager.beginTransaction().addToBackStack(null)
						.replace(R.id.frames,SlideOne)
						.commit();
//				Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
//				startActivity(intent);
			}
			super.run();
		}
	};
	thread.start();
}


private void onCekPermissionTask(){
	String[] mPermissionTaks = {
			Manifest.permission.CAMERA,
			Manifest.permission.ACCESS_FINE_LOCATION,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.ACCESS_NETWORK_STATE,
			Manifest.permission.ACCESS_COARSE_LOCATION,
			Manifest.permission.SEND_SMS,
			Manifest.permission.RECEIVE_SMS,
			Manifest.permission.READ_CONTACTS,
			Manifest.permission.INTERNET
	};
}
}
