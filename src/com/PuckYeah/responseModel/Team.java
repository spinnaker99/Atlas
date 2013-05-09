package com.PuckYeah.responseModel;

import java.util.List;

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

	class User {
		
		private String role;
		
		private String id;
		
		@SerializedName("full_name")
		private String name;
		
		private String email;

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
