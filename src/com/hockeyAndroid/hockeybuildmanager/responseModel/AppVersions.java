package com.hockeyAndroid.hockeybuildmanager.responseModel;

import java.util.List;

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
	
	public class AppVersion {
		
		private String version;
		
		@SerializedName("config_url")
		private String configUrl;
		
		@SerializedName("download_url")
		private String downloadUrl;
		
		private String notes;

		private String timestamp;
		
		public long getTimestamp() {
			return Long.parseLong(timestamp);
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getConfigUrl() {
			return configUrl;
		}

		public void setConfigUrl(String configUrl) {
			this.configUrl = configUrl;
		}

		public String getDownloadUrl() {
			return downloadUrl;
		}

		public void setDownloadUrl(String downloadUrl) {
			this.downloadUrl = downloadUrl;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}
		
		
	}
}
