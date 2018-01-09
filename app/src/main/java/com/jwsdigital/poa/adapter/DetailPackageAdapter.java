package com.jwsdigital.poa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwsdigital.poa.R;

/**
 * Created by eko on 11/3/17.
 */

public class DetailPackageAdapter extends RecyclerView.Adapter<DetailPackageAdapter.ViewHolder> {
private View view;
@Override
public DetailPackageAdapter.ViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
	view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail_package,null);
	return new ViewHolder(view);
}

@Override
public void onBindViewHolder ( DetailPackageAdapter.ViewHolder holder, int position ) {

}

@Override
public int getItemCount () {
	return 0;
}

public class ViewHolder extends RecyclerView.ViewHolder {
	public ViewHolder ( View itemView ) {
		super(itemView);
	}
}
}
