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

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.widget.ListView;

import com.PuckYeah.adapter.AppVersionsAdapter;
import com.PuckYeah.loader.AppVersionsLoader;
import com.PuckYeah.response.AppVersions;

public class AppVersionsActivity extends BaseFragmentActivity implements LoaderCallbacks<AppVersions> {

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
		
		getSupportLoaderManager().initLoader(1, new Bundle(), this);
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
		getSupportLoaderManager().restartLoader(1, new Bundle(), this);
	}
}
