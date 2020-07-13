/*
 * Created by Force Porquillo on 6/20/20 8:37 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:37 PM
 */

package com.force.codes.project.app.data_layer.converters;

import androidx.room.TypeConverter;

import com.force.codes.project.app.data_layer.model.map_data.PHDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class LocalDataConverter{
    @TypeConverter
    public static List<PHDataSet> stringToObjectList(String value){
        if(value == null)
            return Collections.emptyList();
        Type listType = new TypeToken<List<PHDataSet>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectListToString(List<PHDataSet> objects){
        return new Gson().toJson(objects);
    }
}