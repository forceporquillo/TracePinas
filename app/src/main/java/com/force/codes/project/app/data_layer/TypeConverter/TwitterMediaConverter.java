/*
 * Created by Force Porquillo on 7/10/20 8:54 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/10/20 8:54 AM
 */

package com.force.codes.project.app.data_layer.TypeConverter;

import androidx.room.TypeConverter;

import com.force.codes.project.app.data_layer.model.twitter.TwitterMediaUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TwitterMediaConverter{
    @TypeConverter
    public static TwitterMediaUrl stringToObjectList(String value){
        if(value == null)
            return null;
        Type listType = new TypeToken<List<TwitterMediaUrl>>(){
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectListToString(TwitterMediaUrl objects){
        return new Gson().toJson(objects);
    }
}
