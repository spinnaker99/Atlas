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

import com.PuckYeah.adapter.AppsListAdapter;
import com.PuckYeah.loader.AppsListLoader;
import com.PuckYeah.response.AppsList;

public class MainActivity extends BaseFragmentActivity implements LoaderCallbacks<AppsList> {

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
		
		getSupportLoaderManager().initLoader(1, new Bundle(), this);
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
		getSupportLoaderManager().restartLoader(1, new Bundle(), this);
	}
}
