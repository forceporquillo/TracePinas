package com.force.codes.project.app.data_layer.model.doh_data_drop_csv;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	public DataItem(String region) {
		this.region = region;
	}

	@SerializedName("recovered")
	private int recovered;

	@SerializedName("cases")
	private int cases;

	@SerializedName("region")
	private String region;

	@SerializedName("deaths")
	private int deaths;

	public void setRecovered(int recovered){
		this.recovered = recovered;
	}

	public int getRecovered(){
		return recovered;
	}

	public void setCases(int cases){
		this.cases = cases;
	}

	public int getCases(){
		return cases;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}

	public void setDeaths(int deaths){
		this.deaths = deaths;
	}

	public int getDeaths(){
		return deaths;
	}
}