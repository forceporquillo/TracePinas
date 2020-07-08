/*
 * Created by Force Porquillo on 6/26/20 9:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/21/20 1:07 AM
 */

package com.force.codes.project.app.data_layer.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.force.codes.project.app.data_layer.TypeConverter.StringToObjectList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class LocalListData{
    @TypeConverters(StringToObjectList.class)
    @SerializedName("data")
    private List <PHDataSet> data;
    @NonNull
    @PrimaryKey
    @SerializedName("success")
    private boolean success;

    public LocalListData(List <PHDataSet> data, boolean success){
        this.data = data;
        this.success = success;
    }

    public List <PHDataSet> getData(){
        return data;
    }

    public void setData(List <PHDataSet> data){
        this.data = data;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }
}