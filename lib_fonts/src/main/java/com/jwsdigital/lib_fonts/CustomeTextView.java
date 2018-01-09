package com.jwsdigital.lib_fonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by eko on 10/26/17.
 */

@SuppressLint( "AppCompatCustomView" )
public class CustomeTextView extends TextView {
public CustomeTextView ( Context context, @Nullable AttributeSet attrs ) {
	super(context, attrs);
	this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Raleway-Regular.ttf"));
}
public CustomeTextView(Context context){
	super(context);
}
}
