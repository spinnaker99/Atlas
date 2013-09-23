/*
   Copyright 2013 Vijay Penemetsa

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.PuckYeah;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.PuckYeah.api.Constants;

public class VersionDetailActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_version_detail);
		
		final String title = getIntent().getStringExtra("title");
		final String configUrl = getIntent().getStringExtra("config_url");
		final String downloadUrl = "https://rink.hockeyapp.net/api/2/apps/2f5dd3434e4947788573e0e76f26654f/app_versions/70?format=apk";
//				getIntent().getStringExtra("download_url") + "?format=apk";
		String notes = getIntent().getStringExtra("notes");
		
		final WebView wv = (WebView) findViewById(R.id.notes_webview);
		wv.loadData(notes, "text/html", "UTF-8");
		
		((TextView)findViewById(R.id.app_version)).setText(title);
		
		findViewById(R.id.config_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put(Constants.HEADER_NAME, Constants.API_KEY);
				wv.loadUrl(configUrl, headers);
			}
		});
		
		findViewById(R.id.download_button).setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
				request.setDescription("Downloading apk");
				request.setTitle("Puck-Yeah");
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					request.allowScanningByMediaScanner();
					request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				}
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
				
				SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//				request.addRequestHeader("Authorization", "Basic " + Base64.encodeToString("vijaypenemetsa@yahoo.com:ammamma1".getBytes(), Base64.NO_WRAP));
				request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "yest.apk");
				DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				manager.enqueue(request);

//				Map<String, String> headers = new HashMap<String, String>();
//				headers.put(Constants.HEADER_NAME, Constants.API_KEY);
//				wv.setWebViewClient(new MyWebViewClient());
//				wv.loadUrl(downloadUrl, headers);
				
//				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl));
//				startActivity(i);
			}
		});
	}
	
	private class MyWebViewClient extends WebViewClient {
		@Override
		public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
		    handler.proceed("vijaypenemetsa@yahoo.com", "ammamma1");
		}
	}
	
}
