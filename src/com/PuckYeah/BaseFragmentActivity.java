/*
   Copyright 2013 Victor Sima

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

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.PuckYeah.api.Constants;
import com.PuckYeah.dialog.LogoutDialogFragment;

public class BaseFragmentActivity extends FragmentActivity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem selectedItem){
		if (selectedItem.getItemId() == R.id.menu_logout) {
			LogoutDialogFragment logoutFragment = new LogoutDialogFragment();
			logoutFragment.show(getSupportFragmentManager(), "logout confirm");
		} else if (selectedItem.getItemId() == android.R.id.home) {
			finish();
		}
		
		return super.onOptionsItemSelected(selectedItem);
	}
	
	public void logout() {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPrefs.edit().remove(Constants.APP_KEY).commit();
		
		Intent i = new Intent(this, LoginActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
		finish();
	}
}
