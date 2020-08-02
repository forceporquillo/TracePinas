/*
 * Created by Force Porquillo on 6/20/20 8:37 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:37 PM
 */
package com.force.codes.project.app.data_layer.converters

import androidx.room.TypeConverter
import com.force.codes.project.app.data_layer.model.map_data.PHDataSet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LocalDataConverter {
  @TypeConverter
  @JvmStatic fun fromListOfStrings(
    value: String?
  ): List<PHDataSet>? = value.let {
    val listType = object :
        TypeToken<List<PHDataSet?>?>() {
    }.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  @JvmStatic fun toListOfStrings(
    objects: List<PHDataSet?>?
  ): String {
    return Gson().toJson(objects)
  }
}