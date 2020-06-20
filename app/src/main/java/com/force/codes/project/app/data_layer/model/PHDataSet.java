package com.force.codes.project.app.data_layer.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity
public class PHDataSet{

	public PHDataSet(@NonNull String caseCode, String latitude, String longitude){
		this.caseCode = caseCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@PrimaryKey
	@SerializedName("case_code")
	private String caseCode;


	@SerializedName("latitude")
	private String latitude;


	@SerializedName("longitude")
	private String longitude;

	public void setCaseCode(@NotNull String caseCode){
		this.caseCode = caseCode;
	}

	@NotNull
	public String getCaseCode(){
		return caseCode;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	@Nullable
	public String getLatitude(){
		return latitude;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	@Nullable
	public String getLongitude(){
		return longitude;
	}
}