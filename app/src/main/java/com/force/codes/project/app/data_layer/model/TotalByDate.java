package com.force.codes.project.app.data_layer.model;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class TotalByDate{
	@PrimaryKey
	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}