package com.jwsdigital.lib_fonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by eko on 10/26/17.
 */

@SuppressLint( "AppCompatCustomView" )
public class CustomeButtonView extends Button {
public CustomeButtonView ( Context context, AttributeSet attrs ) {
	super(context, attrs);
	this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Regular.ttf"));
}
}
