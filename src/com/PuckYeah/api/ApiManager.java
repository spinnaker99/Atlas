package com.PuckYeah.api;

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
import android.preference.PreferenceManager;

import com.PuckYeah.responseModel.BaseResponse;
import com.google.gson.GsonBuilder;

public class ApiManager {
	
	public static <T extends BaseResponse> BaseResponse doRequest(Context context, HttpMethod method, String url, Class<T> clazz) {
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		
		requestHeaders.add(Constants.HEADER_NAME, sharedPrefs.getString(Constants.APP_KEY, ""));
		
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
		
		if (type.equals(Constants.AUTH_GET)) {
			requestHeaders.add(Constants.LOGIN_HEADER, username + ":" + password);
		} else if (type.equals(Constants.AUTH_POST)) {
			requestHeaders.add(Constants.LOGIN_HEADER, username + ":" + password + " -F \"rights=0\"");
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
