package com.PuckYeah.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.PuckYeah.VersionDetailActivity;
import com.PuckYeah.responseModel.AppVersions.AppVersion;

public class AppVersionsAdapter extends ArrayAdapter<AppVersion> {

	Context mContext;
	LayoutInflater mLayoutInflater;
	
	public AppVersionsAdapter(Context context, int textViewResourceId, LayoutInflater layoutInflater) {
		super(context, textViewResourceId);
		mContext = context;
		mLayoutInflater = layoutInflater;
	}
	
	public void setData(List<AppVersion> data) {
		clear();
		if (data != null) {
			for (AppVersion appVersion : data) {
				if (appVersion.getDownloadUrl() != null) {
					add(appVersion);
				}
			}
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final AppVersion item = getItem(position);
		if (item != null) {
			convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

			final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
			final Date date = new Date((long)item.getTimestamp() * 1000); 
			
			TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
			tv.setText(item.getVersion() + " - " + df.format(date));
			
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(mContext, VersionDetailActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.putExtra("title", item.getVersion() + " - " + df.format(date));
					i.putExtra("config_url", item.getConfigUrl());
					i.putExtra("download_url", item.getDownloadUrl());
					i.putExtra("notes", item.getNotes());
					
					mContext.startActivity(i);
				}
			});
		}
		
		return convertView;
	}

}
