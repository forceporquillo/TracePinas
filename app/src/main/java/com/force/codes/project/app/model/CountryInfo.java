/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/15/20 4:31 PM
 */

package com.force.codes.project.app.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class CountryInfo extends BaseObservable{
    @ColumnInfo(name = "Flag Url")
    @SerializedName("flag")
    private String flag;

    @ColumnInfo(name = "Latitude")
    @SerializedName("lat")
    private String latitude;

    @ColumnInfo(name = "Longitude")
    @SerializedName("long")
    private String longitude;

    @Bindable
    public String getFlag(){
        return flag;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}