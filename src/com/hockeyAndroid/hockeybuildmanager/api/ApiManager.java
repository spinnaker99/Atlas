package com.hockeyAndroid.hockeybuildmanager.api;

import java.util.Collections;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.hockeyAndroid.hockeybuildmanager.responseModel.BaseResponse;

public class ApiManager {
	
	public static <T extends BaseResponse> BaseResponse doRequest(Context context, HttpMethod method, String url, Class<T> clazz) {
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		SharedPreferences sharedPrefs = context.getSharedPreferences(Constants.app_prefs, Context.MODE_MULTI_PROCESS);
		
		requestHeaders.add(Constants.header_name, sharedPrefs.getString(Constants.app_key, ""));
		
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();
		
		GsonBuilder builder = new GsonBuilder();
		
//		 Add the Gson message converter
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter(builder.create()));
		
		ResponseEntity<T> responseEntity = null;
		responseEntity = restTemplate.exchange(url, method, requestEntity, clazz); 
		
		T response = responseEntity.getBody();
		
		return response;
	}
	
	public static <T extends BaseResponse> BaseResponse doLoginRequest(Context context, HttpMethod method, String url, Class<T> clazz, String username, String password, String type) {
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		
		if (type.equals(Constants.auth_get)) {
			requestHeaders.add(Constants.login_header, username + ":" + password);
		} else if (type.equals(Constants.auth_post)) {
			requestHeaders.add(Constants.login_header, username + ":" + password + " -F \"rights=0\"");
		}
		
		HttpAuthentication httpAuth = new HttpBasicAuthentication(username, password);
		requestHeaders.setAuthorization(httpAuth);
		
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();
		
		GsonBuilder builder = new GsonBuilder();
		
//		 Add the Gson message converter
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter(builder.create()));
		ResponseEntity<T> responseEntity = null;
		responseEntity = restTemplate.exchange(url, method, requestEntity, clazz); 
		T response = responseEntity.getBody();
		
		return response;
	}
}
