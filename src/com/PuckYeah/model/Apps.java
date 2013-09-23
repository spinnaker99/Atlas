package com.PuckYeah.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Apps implements Parcelable {

	private String title;
	
	@SerializedName("bundle_identifier")
	private String bundleIdentifier;
	
	@SerializedName("public_identifier")
	private String publicIdentifier;
	
	@SerializedName("release_type")
	private String releaseType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBundleIdentifier() {
		return bundleIdentifier;
	}

	public void setBundleIdentifier(String bundleIdentifier) {
		this.bundleIdentifier = bundleIdentifier;
	}

	public String getPublicIdentifier() {
		return publicIdentifier;
	}

	public void setPublicIdentifier(String publicIdentifier) {
		this.publicIdentifier = publicIdentifier;
	}

	public String getReleaseType() {
		return releaseType;
	}

	public void setReleaseType(String realeaseType) {
		this.releaseType = realeaseType;
	}
	
	private Apps(Parcel in) {
		title = in.readString();
		bundleIdentifier = in.readString();
		publicIdentifier = in.readString();
		releaseType = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(bundleIdentifier);
		dest.writeString(publicIdentifier);
		dest.writeString(releaseType);
	}
	
	public static final Parcelable.Creator<Apps> CREATOR = new Parcelable.Creator<Apps>() {

		@Override
		public Apps createFromParcel(Parcel source) {
			return new Apps(source);
		}

		@Override
		public Apps[] newArray(int size) {
			return new Apps[size];
		}
	};
}
