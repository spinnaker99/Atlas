package com.hockeyAndroid.hockeybuildmanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;

import com.hockeyAndroid.hockeybuildmanager.api.Constants;
import com.hockeyAndroid.hockeybuildmanager.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@SuppressLint("HandlerLeak")
public class SplashActivity extends Activity {
	
	private static final long DELAY_TIME = 4000;
	
	private MainHandler mMainHandler = new MainHandler();
	private LoginHandler mLoginHandler = new LoginHandler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);
		
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String token = sharedPrefs.getString(Constants.APP_KEY, "");
		
		if (token.equals("")) {
			mLoginHandler.sendMessageDelayed(mLoginHandler.obtainMessage(), DELAY_TIME);
		} else {
			mMainHandler.sendMessageDelayed(mMainHandler.obtainMessage(), DELAY_TIME);
		}
		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
	}
	
	private class MainHandler extends Handler {
			@Override
			public void handleMessage(Message msg) {
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
	}
	
	private class LoginHandler extends Handler {
			@Override
			public void handleMessage(Message msg) {
				Intent i = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(i);
				finish();
			}
	}
}
