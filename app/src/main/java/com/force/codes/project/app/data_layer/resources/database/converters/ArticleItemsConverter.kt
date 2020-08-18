/*
 * Created by Force Porquillo on 7/9/20 5:20 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:20 PM
 */
package com.force.codes.project.app.data_layer.resources.database.converters

import androidx.room.TypeConverter
import com.force.codes.project.app.data_layer.model.news.ArticlesItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ArticleItemsConverter {
  @TypeConverter
  @JvmStatic fun fromListOfStrings(
    value: String?
  ): List<ArticlesItem>? = value.let{
    val listType =
      object : TypeToken<List<ArticlesItem?>?>() {
      }.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  @JvmStatic fun toListOfStrings(
    objects: List<ArticlesItem?>?
  ): String {
    return Gson().toJson(objects)
  }
}