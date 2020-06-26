package com.force.codes.project.app.data_layer.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity
public class PHDataSet{

    public PHDataSet(){
    }

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