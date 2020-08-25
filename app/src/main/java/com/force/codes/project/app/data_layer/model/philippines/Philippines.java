package com.force.codes.project.app.data_layer.model.philippines;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Philippines{

	@SerializedName("data")
	private List<DataFromDayOne> data;

	public void setData(List<DataFromDayOne> data){
		this.data = data;
	}

	public List<DataFromDayOne> getData(){
		return data;
	}
}