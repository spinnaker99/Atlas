package com.PuckYeah.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Crash implements Parcelable {
	
	private String model;
	
	private String id;
	
	@SerializedName("created_at")
	private String createdAt;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	private Crash(Parcel in) {
		model = in.readString();
		id = in.readString();
		createdAt = in.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(model);
		dest.writeString(id);
		dest.writeString(createdAt);
	}
	
	public static final Parcelable.Creator<Crash> CREATOR = new Parcelable.Creator<Crash>() {

		@Override
		public Crash createFromParcel(Parcel source) {
			return new Crash(source);
		}

		@Override
		public Crash[] newArray(int size) {
			return new Crash[size];
		}
	};
}
