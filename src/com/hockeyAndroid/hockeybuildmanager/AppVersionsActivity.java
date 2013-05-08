package com.hockeyAndroid.hockeybuildmanager;

import com.hockeyAndroid.hockeybuildmanager.adapter.AppVersionsAdapter;
import com.hockeyAndroid.hockeybuildmanager.api.Constants;
import com.hockeyAndroid.hockeybuildmanager.loader.AppVersionsLoader;
import com.hockeyAndroid.hockeybuildmanager.responseModel.AppVersions;

import android.app.Activity;
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

public class AppVersionsActivity extends Activity implements LoaderCallbacks<AppVersions> {

	ListView list;
	AppVersionsAdapter adapter;
	AppVersions appVersions;
	String identifier;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_versions);
		
		adapter = new AppVersionsAdapter(getApplicationContext(), 0, getLayoutInflater());
		list = (ListView) findViewById(R.id.app_version_list);
		list.setAdapter(adapter);
		
		identifier = getIntent().getStringExtra("Identifier");
		
		getLoaderManager().initLoader(1, new Bundle(), this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem selectedItem){
		if (selectedItem.getItemId() == R.id.action_logout) {
			SharedPreferences sharedPrefs = getSharedPreferences(Constants.app_prefs, Context.MODE_MULTI_PROCESS);
			sharedPrefs.edit().remove(Constants.app_key).commit();
			
			Intent i = new Intent(this, LoginActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(i);
			finish();
		}
		
		return super.onOptionsItemSelected(selectedItem);
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
