package com.force.codes.project.app.data_layer.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.List;

import com.force.codes.project.app.data_layer.model.Coverter.Converter;
import com.google.gson.annotations.SerializedName;

@Entity
public class Response{
	public Response(List<DataItem> data, boolean success){
		this.data = data;
		this.success = success;
	}

	@TypeConverters(Converter.class)
	@SerializedName("data")
	private List<DataItem> data;

	@NonNull
	@PrimaryKey
	@SerializedName("success")
	private boolean success;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}
}