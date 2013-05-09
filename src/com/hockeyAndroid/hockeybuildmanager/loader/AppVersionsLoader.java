package com.hockeyAndroid.hockeybuildmanager.loader;

import org.springframework.http.HttpMethod;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.hockeyAndroid.hockeybuildmanager.api.ApiManager;
import com.hockeyAndroid.hockeybuildmanager.api.Constants;
import com.hockeyAndroid.hockeybuildmanager.responseModel.AppVersions;

public class AppVersionsLoader extends AsyncTaskLoader<AppVersions> {

	Context mContext;
	AppVersions mData;
	String identifier;
	
	public AppVersionsLoader(Context context, String id) {
		super(context);
		mContext = context;
		identifier = id;
	}

	@Override
	public AppVersions loadInBackground() {
		String url = Constants.BASE_URL + "/" + identifier +"/app_versions";
		return (AppVersions) ApiManager.doRequest(mContext, HttpMethod.GET, url, AppVersions.class);
	}

	@Override
	public void deliverResult(AppVersions data) {
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
	public void onCanceled(AppVersions data) {
		super.onCanceled(data);
	}
	
	@Override
	protected void onReset() {
		super.onReset();
	}
}
