package com.PuckYeah.response;

import java.util.List;

import com.PuckYeah.model.User;
import com.google.gson.annotations.SerializedName;

public class Team extends BaseResponse {

	@SerializedName("app_users")
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
