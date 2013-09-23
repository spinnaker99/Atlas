package com.PuckYeah.response;

import java.util.List;

import com.PuckYeah.model.Apps;

public class AppsList extends BaseResponse {
	
	private List<Apps> apps;
	
	public List<Apps> getApps() {
		return apps;
	}

	public void setApps(List<Apps> apps) {
		this.apps = apps;
	}	
}
