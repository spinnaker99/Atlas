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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;

import com.PuckYeah.api.Constants;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@SuppressLint("HandlerLeak")
public class SplashActivity extends Activity {
	
	private static final long DELAY_TIME = 2500;
	
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
