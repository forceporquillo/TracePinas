/*
 * Created by Force Porquillo on 6/21/20 12:17 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/21/20 12:17 AM
 */
package com.force.codes.project.app.data_layer.resources.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.force.codes.project.app.app.constants.DatabaseConstants
import com.force.codes.project.app.data_layer.model.map_data.LocalData
import com.force.codes.project.app.data_layer.model.world.GlobalData
import io.reactivex.Flowable

@Dao
interface MapDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun savePhData(LocalData: LocalData?)

  @get:Query(DatabaseConstants.QUERY_ALL_PH_DATA)
  val pHDataFromDB: Flowable<LocalData?>?

  @get:Query(DatabaseConstants.QUERY_ALL_GLOBAL_DATA)
  val globalDataFromDB: Flowable<List<GlobalData?>?>?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun saveGlobalData(globalData: List<GlobalData?>?)
}