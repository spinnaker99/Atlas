package com.hockeyAndroid.hockeybuildmanager.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hockeyAndroid.hockeybuildmanager.AppVersionsActivity;
import com.hockeyAndroid.hockeybuildmanager.responseModel.Apps;

public class AppsListAdapter extends ArrayAdapter<Apps> {

	Context mContext;
	LayoutInflater mLayoutInflater;
	
	public AppsListAdapter(Context context, int textViewResourceId, LayoutInflater layoutInflater) {
		super(context, textViewResourceId);
		mContext = context;
		mLayoutInflater = layoutInflater;
	}
	
	public void setData(List<Apps> data) {
		clear();
		if (data != null) {
			addAll(data);
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Apps item = getItem(position);
		if (item != null) {
			convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
			tv.setText(item.getTitle());
			
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(mContext, AppVersionsActivity.class);
					i.putExtra("Identifier", item.getPublicIdentifier());
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mContext.startActivity(i);
				}
			});
			
		}
		
		return convertView;
	}

}
