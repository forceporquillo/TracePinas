/*
 * Created by Force Porquillo on 6/21/20 12:17 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/21/20 12:17 AM
 */

package com.force.codes.project.app.data_layer.resources.database.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.PHData;
import com.force.codes.project.app.data_layer.resources.database.DBModule;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MapDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePhData(PHData PHData);

    @Query(DBModule.QUERY_ALL_PH_DATA)
    Flowable <PHData> getPHDataFromDB();

    @Query(DBModule.QUERY_ALL_GLOBAL_DATA)
    Flowable <List <GlobalData>> getGlobalDataFromDB();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveGlobalData(List <GlobalData> globalData);
}
