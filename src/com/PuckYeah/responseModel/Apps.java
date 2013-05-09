package com.PuckYeah.responseModel;

import com.google.gson.annotations.SerializedName;

public class Apps {

	private String title;
	
	@SerializedName("bundle_identifier")
	private String bundleIdentifier;
	
	@SerializedName("public_identifier")
	private String publicIdentifier;
	
	@SerializedName("release_type")
	private String realeaseType;

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

	public String getRealeaseType() {
		return realeaseType;
	}

	public void setRealeaseType(String realeaseType) {
		this.realeaseType = realeaseType;
	}
}
