/*
 * Created by Force Porquillo on 7/10/20 8:54 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/2/20 7:39 PM
 */

package com.force.codes.project.app.data_layer.resources.database.converters

import androidx.room.TypeConverter
import com.force.codes.project.app.data_layer.model.twitter.TwitterMediaUrl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TwitterMediaConverter {
  @TypeConverter
  @JvmStatic fun fromString(
    tweets: String?
  ): List<TwitterMediaUrl>? = tweets?.let {
    val tweetsListType = object :
        TypeToken<List<TwitterMediaUrl?>?>() {
    }.type
    return Gson().fromJson(
        tweets, tweetsListType
    )
  }

  @TypeConverter
  @JvmStatic fun toArrayList(
    objects: List<TwitterMediaUrl?>?
  ): String {
    return Gson().toJson(
        objects
    )
  }
}