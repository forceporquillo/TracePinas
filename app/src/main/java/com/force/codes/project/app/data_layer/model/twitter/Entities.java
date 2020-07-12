/*
 * Created by Force Porquillo on 7/11/20 1:50 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/11/20 1:50 AM
 */

package com.force.codes.project.app.data_layer.model.twitter;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.force.codes.project.app.data_layer.TypeConverter.TwitterMediaConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Entities{
    @TypeConverters(TwitterMediaConverter.class)
    @SerializedName("media")
    @Expose
    private List<TwitterMediaUrl> media = null;

    public List<TwitterMediaUrl> getMedia(){
        return media;
    }

    public void setMedia(List<TwitterMediaUrl> media){
        this.media = media;
    }
}