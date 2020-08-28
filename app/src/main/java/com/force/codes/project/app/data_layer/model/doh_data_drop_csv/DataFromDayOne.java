package com.force.codes.project.app.data_layer.model.doh_data_drop_csv;

import com.google.gson.annotations.SerializedName;

public class DataFromDayOne {

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