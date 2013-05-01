package com.hockeyAndroid.hockeybuildmanager.api;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.hockeyAndroid.hockeybuildmanager.responseModel.BaseResponse;

public class ApiManager {
	
	public static <T extends BaseResponse> BaseResponse doRequest(Context context, HttpMethod method, String url, Class<T> clazz) {
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		requestHeaders.add(Constants.header_name, Constants.api_key);
		
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
