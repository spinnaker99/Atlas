package com.hockeyAndroid.hockeybuildmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hockeyAndroid.hockeybuildmanager.api.Constants;
import com.hockeyAndroid.hockeybuildmanager.dialog.LogoutDialogFragment;

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
		} else if (selectedItem.getItemId() == R.id.menu_settings) {
			
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
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(i);
		finish();
	}
	
}
