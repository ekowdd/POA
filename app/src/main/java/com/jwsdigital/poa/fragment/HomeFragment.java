package com.jwsdigital.poa.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.jwsdigital.poa.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eko on 10/26/17.
 */

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
private View view;
private SliderLayout mDemoSlider;
@Nullable
@Override
public View onCreateView ( LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState ) {
	view = inflater.inflate(R.layout.home_fragment,container,false);
	mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

	HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
	file_maps.put("Big Stock",R.drawable.bigstock);
	file_maps.put("Big Bang Theory",R.drawable.bs);
	file_maps.put("Training",R.drawable.training);
	file_maps.put("Foto",R.drawable.download);

	for ( String name: file_maps.keySet()) {
		TextSliderView textSliderView = new TextSliderView(view.getContext());
		textSliderView
				.description(name)
				.image(file_maps.get(name))
				.setScaleType(BaseSliderView.ScaleType.Fit)
				.setOnSliderClickListener(this);
		textSliderView.bundle(new Bundle());
		textSliderView
				.getBundle()
				.putString("extra",name);
		mDemoSlider.addSlider(textSliderView);
	}

	mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
	mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
	mDemoSlider.setCustomAnimation(new DescriptionAnimation());
	mDemoSlider.setDuration(4000);
	return view;
}

@Override
public void onStop () {
	mDemoSlider.stopAutoCycle();
	super.onStop();
}

@Override
public void onSliderClick ( BaseSliderView slider ) {

}

@Override
public void onPageScrolled ( int position, float positionOffset, int positionOffsetPixels ) {

}

@Override
public void onPageSelected ( int position ) {

}

@Override
public void onPageScrollStateChanged ( int state ) {

}
}
