/*
 * Created by Force Porquillo on 7/10/20 8:54 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/10/20 8:54 AM
 */

package com.force.codes.project.app.data_layer.converters;

import androidx.room.TypeConverter;

import com.force.codes.project.app.data_layer.model.twitter.TwitterMediaUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TwitterMediaConverter{
    @TypeConverter
    public static List<TwitterMediaUrl> tweetStringToObjectList(String tweets){
        if(tweets == null)
            return Collections.emptyList();
        Type tweetsListType = new TypeToken<List<TwitterMediaUrl>>(){}.getType();
        return new Gson().fromJson(tweets, tweetsListType);
    }

    @TypeConverter
    public static String objToListStringTweets(List<TwitterMediaUrl> objects){
        return new Gson().toJson(objects);
    }
}
