package com.hockeyAndroid.hockeybuildmanager.loader;

import org.springframework.http.HttpMethod;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.hockeyAndroid.hockeybuildmanager.api.ApiManager;
import com.hockeyAndroid.hockeybuildmanager.api.Constants;
import com.hockeyAndroid.hockeybuildmanager.responseModel.AppsList;

public class AppsListLoader extends AsyncTaskLoader<AppsList> {

	Context mContext;
	AppsList mData;
	Bundle mBundle;
	
	public AppsListLoader(Context context, Bundle bundle) {
		super(context);
		mBundle = bundle;
		mContext = context;
	}

	@Override
	public AppsList loadInBackground() {
		String url = Constants.baseUrl;
		return (AppsList) ApiManager.doRequest(mContext, HttpMethod.GET, url, AppsList.class);
	}
	
	@Override
	public void deliverResult(AppsList data) {
		mData = data;

		if (isStarted()) {
			super.deliverResult(data);
		}
	}
	
	@Override
	protected void onStartLoading() {
		if (mData != null) {
			deliverResult(mData);
		}
		
		if (takeContentChanged() || mData == null) {
			forceLoad();
		}
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
	}
	
	@Override
	public void onCanceled(AppsList data) {
		super.onCanceled(data);
	}
	
	@Override
	protected void onReset() {
		super.onReset();
	}

}
