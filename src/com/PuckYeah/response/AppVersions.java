package com.PuckYeah.response;

import java.util.List;

import com.PuckYeah.model.AppVersion;
import com.google.gson.annotations.SerializedName;

public class AppVersions extends BaseResponse {
	
	@SerializedName("app_versions")
	private List<AppVersion> appVersions;
	
	public List<AppVersion> getAppVersions() {
		return appVersions;
	}

	public void setAppVersions(List<AppVersion> appVersions) {
		this.appVersions = appVersions;
	}
}
