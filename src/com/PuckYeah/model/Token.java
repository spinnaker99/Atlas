package com.PuckYeah.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Token implements Parcelable {
	
	private String token;
	
	private int rights;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	private Token(Parcel in) {
		token = in.readString();
		rights = in.readInt();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(token);
		dest.writeInt(rights);
	}
}
