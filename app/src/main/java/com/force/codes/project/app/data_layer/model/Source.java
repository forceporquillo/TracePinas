package com.force.codes.project.app.data_layer.model;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity
public class Source{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private Object id;

	public String getName(){
		return name;
	}

	public Object getId(){
		return id;
	}
}