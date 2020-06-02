package com.force.codes.project.covid19.data.resources.web.model;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import com.google.gson.annotations.SerializedName;

public class CountryInfo{

    @SerializedName("flag")
    private String flag;

    @SerializedName("lat")
    private String latitude;

    @SerializedName("long")
    private String longitude;

    public String getFlag(){
        return flag;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }
}