package com.jwsdigital.poa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwsdigital.lib_fonts.CustomeButtonView;
import com.jwsdigital.poa.R;
import com.jwsdigital.poa.activity.LoginActivity;

/**
 * Created by eko on 10/29/17.
 */

public class SplashFragmentThree extends Fragment {
private View view;
private CustomeButtonView start;
@Nullable
@Override
public View onCreateView ( LayoutInflater inflater,
                           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
	view = inflater.inflate(R.layout.slidethree, container, false);
	start = (CustomeButtonView) view.findViewById(R.id.vstart);
	start.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick ( View view ) {
			Intent intent = new Intent(view.getContext(), LoginActivity.class);
			view.getContext().startActivity(intent);
		}
	});
	return view;
}
}
