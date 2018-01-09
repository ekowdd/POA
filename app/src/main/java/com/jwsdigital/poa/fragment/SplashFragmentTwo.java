package com.jwsdigital.poa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwsdigital.poa.R;

/**
 * Created by eko on 10/29/17.
 */

public class SplashFragmentTwo extends Fragment {
private View view;
private Thread thread;
@Nullable
@Override
public View onCreateView ( LayoutInflater inflater,
                           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
	view = inflater.inflate(R.layout.slidetwo, container, false);
	thread = new Thread(){
		@Override
		public void run () {
			super.run();
			try {
				sleep(3000);
			}catch ( Exception e ){
				e.printStackTrace();
			}finally {
				(( FragmentActivity)view.getContext()).getSupportFragmentManager()
						.beginTransaction()
						.addToBackStack(null)
						.replace(R.id.frames, new SplashFragmentThree())
						.commit();
			}
		}
	};
	thread.start();
	return view;
}
}
