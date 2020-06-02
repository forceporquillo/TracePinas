package com.force.codes.project.covid19.data.resources.database;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CountryDao{
    @Query(DBConstants.QUERY_ALL_DATA)
    Flowable<List<CountryDetails>> getLocalData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CountryDetails> details);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorites(CountryDetails details);
}
