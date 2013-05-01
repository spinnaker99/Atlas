package com.hockeyAndroid.hockeybuildmanager;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ListView;

import com.hockeyAndroid.hockeybuildmanager.adapter.AppsListAdapter;
import com.hockeyAndroid.hockeybuildmanager.loader.AppsListLoader;
import com.hockeyAndroid.hockeybuildmanager.responseModel.AppsList;

public class MainActivity extends Activity implements LoaderCallbacks<AppsList> {

	ListView list;
	AppsListAdapter appsListAdapter;
	AppsList appsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		appsListAdapter = new AppsListAdapter(getApplicationContext(), 0, getLayoutInflater());
		list = (ListView) findViewById(R.id.app_list);
		list.setAdapter(appsListAdapter);
		
		getLoaderManager().initLoader(1, new Bundle(), this);
	}

	@Override
	public Loader<AppsList> onCreateLoader(int id, Bundle args) {
		return new AppsListLoader(getApplicationContext(), new Bundle());
	}

	@Override
	public void onLoadFinished(Loader<AppsList> loader, AppsList data) {
		appsListAdapter.setData(data.getApps());
	}

	@Override
	public void onLoaderReset(Loader<AppsList> loader) {
		appsListAdapter.setData(null);
	}
	
	public void restartLoaders() {
		getLoaderManager().restartLoader(1, new Bundle(), this);
	}
}
