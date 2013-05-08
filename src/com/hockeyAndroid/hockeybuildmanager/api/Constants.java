package com.hockeyAndroid.hockeybuildmanager.api;

public class Constants {

	public static final String api_key = "f435bf9836f64ec2a5903b8ac4a2b622";
	public static final String header_name = "X-HockeyAppToken";
	public static final String app_identifier = "2f5dd3434e4947788573e0e76f26654f";

	public static final String login_header = "user";
	
	public static final String app_prefs = "app_prefs";
	public static final String app_key = "app_key";
	
	public static final String auth_get = "auth_get";
	public static final String auth_post = "auth_post";
	
	//Urls
	public static final String baseUrl = "https://rink.hockeyapp.net/api/2/apps";
	public static final String loginUrl = "https://rink.hockeyapp.net/api/2/auth_tokens";
	public static final String appVersions = "/" + app_identifier +"/app_versions";
	
	
}
