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
import com.force.codes.project.app.app.constants.DatabaseConstants.QUERY_ARTICLES_ITEM_LIMIT
import com.force.codes.project.app.app.constants.DatabaseConstants.QUERY_TWITTER_DATA
import com.force.codes.project.app.data_layer.resources.database.converters.TwitterMediaConverter
import com.force.codes.project.app.data_layer.model.news.ArticlesItem
import com.force.codes.project.app.data_layer.model.twitter.TwitterData

@Dao
interface NewsDao {
  @get:Transaction
  @get:Query(QUERY_ARTICLES_ITEM_LIMIT)
  val newsDataFromDatabase: Factory<Int?, ArticlesItem?>?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdateArticleItems(
    items: List<ArticlesItem?>?
  )

  @get:Query(QUERY_TWITTER_DATA)
  @get:TypeConverters(TwitterMediaConverter::class)
  @get:Transaction
  val recentTweetsFromDatabase: Factory<Int?, TwitterData?>?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdateTwitterUsers(
    userData: List<TwitterData?>?
  )
}