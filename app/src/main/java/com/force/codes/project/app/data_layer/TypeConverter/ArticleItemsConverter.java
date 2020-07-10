/*
 * Created by Force Porquillo on 7/9/20 5:20 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:20 PM
 */

package com.force.codes.project.app.data_layer.TypeConverter;

import androidx.room.TypeConverter;

import com.force.codes.project.app.data_layer.model.ArticlesItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ArticleItemsConverter{
    @TypeConverter
    public static List<ArticlesItem> stringToObjectList(String value){
        if(value == null)
            return Collections.emptyList();
        Type listType = new TypeToken<List<ArticlesItem>>(){
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectListToString(List<ArticlesItem> objects){
        return new Gson().toJson(objects);
    }
}
