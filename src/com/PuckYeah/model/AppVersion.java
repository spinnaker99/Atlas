package com.PuckYeah.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AppVersion implements Parcelable {
	
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
	
	private AppVersion(Parcel in) {
		version = in.readString();
		configUrl = in.readString();
		downloadUrl = in.readString();
		notes = in.readString();
		timestamp = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(version);
		dest.writeString(configUrl);
		dest.writeString(downloadUrl);
		dest.writeString(notes);
		dest.writeString(timestamp);
	}
	
	public static final Parcelable.Creator<AppVersion> CREATOR = new Parcelable.Creator<AppVersion>() {

		@Override
		public AppVersion createFromParcel(Parcel source) {
			return new AppVersion(source);
		}

		@Override
		public AppVersion[] newArray(int size) {
			return new AppVersion[size];
		}
	};
}