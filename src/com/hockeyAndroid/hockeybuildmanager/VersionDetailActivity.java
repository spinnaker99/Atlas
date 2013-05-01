package com.hockeyAndroid.hockeybuildmanager;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
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
	
}
