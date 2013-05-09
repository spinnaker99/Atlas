package com.PuckYeah.responseModel;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CrashList {
	
	private List<Crash> crashes;
	
	public List<Crash> getCrashes() {
		return crashes;
	}

	public void setCrashes(List<Crash> crashes) {
		this.crashes = crashes;
	}

	public String getTotalEntries() {
		return totalEntries;
	}

	public void setTotalEntries(String totalEntries) {
		this.totalEntries = totalEntries;
	}

	@SerializedName("total_entries")
	private String totalEntries;
	
	class Crash {
		
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
	}
}
