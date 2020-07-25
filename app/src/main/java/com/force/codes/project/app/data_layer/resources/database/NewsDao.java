/*
 * Created by Force Porquillo on 7/9/20 5:35 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:35 AM
 */

package com.force.codes.project.app.data_layer.resources.database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.TypeConverters;

import com.force.codes.project.app.data_layer.converters.TwitterMediaConverter;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;

import java.util.List;

@Dao
public interface NewsDao{
    @Transaction
    @Query("SELECT * FROM ArticlesItem ORDER BY publishedAt DESC LIMIT 100")
    DataSource.Factory<Integer, ArticlesItem> getNewsDataFromDatabase();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateArticleItems(List<ArticlesItem> items);

    @Transaction
    @TypeConverters(TwitterMediaConverter.class)
    @Query("SELECT * FROM TwitterData ORDER BY id DESC LIMIT 50")
    DataSource.Factory<Integer, TwitterData> getRecentTweetsFromDatabase();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateTwitterItems(List<TwitterData> items);
}
