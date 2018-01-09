package com.jwsdigital.poa;

import android.app.Application;

import com.jwsdigital.poa.ApiService.WebServiceHelper;

/**
 * Created by eko on 10/25/17.
 */

public class App extends Application {
	@Override
	public void onCreate () {
		super.onCreate();
		WebServiceHelper.initialize();
	}
}
