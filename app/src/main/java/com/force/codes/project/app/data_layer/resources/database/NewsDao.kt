/*
 * Created by Force Porquillo on 7/9/20 5:35 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/4/20 4:48 AM
 */

package com.force.codes.project.app.data_layer.resources.database

import androidx.paging.DataSource.Factory
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import com.force.codes.project.app.data_layer.model.news.ArticlesItem
import com.force.codes.project.app.data_layer.model.twitter.TwitterData
import com.force.codes.project.app.data_layer.resources.database.converters.TwitterMediaConverter

@Dao
interface NewsDao {
  @get:Query("SELECT * FROM TwitterData ORDER BY id DESC"
  )
  @get:TypeConverters(TwitterMediaConverter::class)
  @get:Transaction
  val recentTweetsFromDatabase: Factory<Int?, TwitterData?>?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdateTwitterUsers(
    userData: List<TwitterData?>?
  )

  @get:Transaction
  @get:Query(
      "SELECT * FROM ArticlesItem ORDER BY publishedAt DESC LIMIT 100"
  )
  val newsDataFromDatabase: Factory<Int?, ArticlesItem?>?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdateArticleItems(
    items: List<ArticlesItem?>?
  )
}