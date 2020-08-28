package com.force.codes.project.app.data_layer.model.doh_data_drop_csv;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DOHDataDrop {

	@SerializedName("data")
	private List<DataFromDayOne> data;

	public void setData(List<DataFromDayOne> data){
		this.data = data;
	}

	public List<DataFromDayOne> getData(){
		return data;
	}
}