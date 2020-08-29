package com.force.codes.project.app.data_layer.resources.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.force.codes.project.app.data_layer.model.PrimarySelected

@Dao
interface NavHostDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCountry(primary: PrimarySelected)
}