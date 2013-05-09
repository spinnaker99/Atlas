package com.hockeyAndroid.hockeybuildmanager;

import android.app.ActionBar;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.hockeyAndroid.hockeybuildmanager.adapter.AppVersionsAdapter;
import com.hockeyAndroid.hockeybuildmanager.api.Constants;
import com.hockeyAndroid.hockeybuildmanager.loader.AppVersionsLoader;
import com.hockeyAndroid.hockeybuildmanager.responseModel.AppVersions;

@SuppressWarnings("unused")
public class AppVersionsActivity extends BaseFragmentActivity implements LoaderCallbacks<AppVersions> {

	ListView list;
	AppVersionsAdapter adapter;
	AppVersions appVersions;
	String identifier;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_versions);
		
		ActionBar ab = getActionBar();
		ab.setHomeButtonEnabled(true);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle(R.string.app_versions_title);
		
		adapter = new AppVersionsAdapter(getApplicationContext(), 0, getLayoutInflater());
		list = (ListView) findViewById(R.id.app_version_list);
		list.setAdapter(adapter);
		
		identifier = getIntent().getStringExtra("Identifier");
		
		getLoaderManager().initLoader(1, new Bundle(), this);
	}

	
	@Override
	public Loader<AppVersions> onCreateLoader(int id, Bundle args) {
		return new AppVersionsLoader(getApplicationContext(), identifier);
	}

	@Override
	public void onLoadFinished(Loader<AppVersions> loader, AppVersions data) {
		adapter.setData(data.getAppVersions());
	}

	@Override
	public void onLoaderReset(Loader<AppVersions> loader) {
		adapter.setData(null);
	}
	
	public void restartLoaders() {
		getLoaderManager().restartLoader(1, new Bundle(), this);
	}
}
