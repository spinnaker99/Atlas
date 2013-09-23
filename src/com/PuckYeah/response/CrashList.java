package com.PuckYeah.response;

import java.util.List;

import com.PuckYeah.model.Crash;
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
}
