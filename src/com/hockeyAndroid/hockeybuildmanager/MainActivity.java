package com.hockeyAndroid.hockeybuildmanager;

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

import com.hockeyAndroid.hockeybuildmanager.adapter.AppsListAdapter;
import com.hockeyAndroid.hockeybuildmanager.api.Constants;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
//		
//		menu.findItem(R.id.action_logout).getActionView().setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				SharedPreferences sharedPrefs = getSharedPreferences(Constants.app_prefs, Context.MODE_MULTI_PROCESS);
//				sharedPrefs.edit().remove(Constants.app_key).commit();
//				
//				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				startActivity(i);
//			}
//		});
//		
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
