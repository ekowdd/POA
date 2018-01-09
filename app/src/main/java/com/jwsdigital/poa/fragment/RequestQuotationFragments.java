package com.jwsdigital.poa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwsdigital.poa.R;

/**
 * Created by eko on 10/30/17.
 */

public class RequestQuotationFragments extends Fragment {
private View view;

@Nullable
@Override
public View onCreateView ( LayoutInflater inflater,
                           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
	view = inflater.inflate(R.layout.request_quotations_fragments,container,false);
	return view;
}
}
