package com.force.codes.project.app.data_layer.model;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("date")
	private String date;

	@SerializedName("cases")
	private int cases;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setCases(int cases){
		this.cases = cases;
	}

	public int getCases(){
		return cases;
	}
}