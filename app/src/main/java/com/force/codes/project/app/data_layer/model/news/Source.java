/*
 * Created by Force Porquillo on 7/11/20 1:56 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/10/20 9:13 AM
 */

package com.force.codes.project.app.data_layer.model.news;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity
public class Source{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}
}