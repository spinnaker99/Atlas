package com.hockeyAndroid.hockeybuildmanager.responseModel;

import java.util.List;

public class AppsList extends BaseResponse {
	
	private List<Apps> apps;
	
	public List<Apps> getApps() {
		return apps;
	}

	public void setApps(List<Apps> apps) {
		this.apps = apps;
	}	
}
