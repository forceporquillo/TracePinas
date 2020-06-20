/*
 * Created by Force Porquillo on 6/20/20 8:37 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:37 PM
 */

package com.force.codes.project.app.data_layer.model.Coverter;

import androidx.room.TypeConverter;


import com.force.codes.project.app.data_layer.model.DataItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converter {
    @TypeConverter
    public static List<DataItem> stringToObjectList(String value){
        if(value == null){
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<DataItem>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectListToString(List<DataItem> objects){
        return new Gson().toJson(objects);
    }
}