/*
 * Created by Force Porquillo on 7/9/20 5:35 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:35 AM
 */

package com.force.codes.project.app.data_layer.resources.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.force.codes.project.app.app.constants.DatabaseConstants;
import com.force.codes.project.app.data_layer.model.NewsData;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NewsDao{
//    @Transaction
//    @Query(DatabaseConstants.QUERY_NEWS_DATA)
//    Flowable<List<NewsData>> getResponseFromDB();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateNews(List<NewsData> responses);
}
