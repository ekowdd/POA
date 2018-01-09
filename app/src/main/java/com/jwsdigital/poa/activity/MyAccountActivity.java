package com.jwsdigital.poa.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jwsdigital.lib_fonts.CustomeTextView;
import com.jwsdigital.poa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAccountActivity extends AppCompatActivity {
@BindView(R.id.onHideWalet)
CustomeTextView onHideWalet;
@BindView(R.id.frame_walet)
LinearLayout frame_walet;
boolean isVisible = false;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_my_account);
	ButterKnife.bind(this);
}

@OnClick(R.id.onHideWalet) void onHideWalet(){

}

}
