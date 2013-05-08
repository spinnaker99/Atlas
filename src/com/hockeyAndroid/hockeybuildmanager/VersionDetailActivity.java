package com.hockeyAndroid.hockeybuildmanager;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

import com.hockeyAndroid.hockeybuildmanager.api.Constants;

public class VersionDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_version_detail);
		
		String title = getIntent().getStringExtra("title");
		final String configUrl = getIntent().getStringExtra("config_url");
		final String downloadUrl = getIntent().getStringExtra("download_url");
		String notes = getIntent().getStringExtra("notes");
		
		final WebView wv = (WebView) findViewById(R.id.notes_webview);
		wv.loadData(notes, "text/html", "UTF-8");
		
		findViewById(R.id.config_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put(Constants.header_name, Constants.api_key);
				wv.loadUrl(configUrl, headers);
			}
		});
		
		findViewById(R.id.download_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put(Constants.header_name, Constants.api_key);
				wv.loadUrl(downloadUrl, headers);
			}
		});
		
		setTitle(title);
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
	
}
