/*
 * Created by Force Porquillo on 6/2/20 11:57 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/31/20 6:06 PM
 */

package com.force.codes.project.app.data_layer.resources.database

import androidx.paging.DataSource.Factory
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.force.codes.project.app.app.constants.DatabaseConstants
import com.force.codes.project.app.data_layer.model.country.CountryDetails


@Dao
interface WorldwideDao {
  @get:Transaction
  @get:Query(DatabaseConstants.QUERY_COUNTRY_DETAILS)
  val dataFromDatabase: Factory<Int?, CountryDetails?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdate(details: List<CountryDetails?>?)
}