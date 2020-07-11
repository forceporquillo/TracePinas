/*
 * Created by Force Porquillo on 6/26/20 9:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/25/20 3:37 PM
 */

package com.force.codes.project.app.data_layer.model.map_data;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity
public class PHDataSet{

    @PrimaryKey
    @SerializedName("case_code")
    private String caseCode;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("age")
    private String age;
    @SerializedName("sex")
    private String sex;
    @SerializedName("location")
    private String location;

    public PHDataSet(){
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getAge(){
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    @NotNull
    public String getCaseCode(){
        return caseCode;
    }

    public void setCaseCode(@NotNull String caseCode){
        this.caseCode = caseCode;
    }

    @Nullable
    public String getLatitude(){
        return latitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    @Nullable
    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
}