/*
 * Created by Force Porquillo on 8/8/20 1:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 1:04 AM
 */

package com.force.codes.project.app.data_layer.resources.database;

import androidx.room.Dao;
import androidx.room.Query;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public interface MyCountryDao {
  @Query("SELECT * FROM CountryDetails")
  Flowable<List<CountryDetails>> getCountryDetails();

  @Query("SELECT countryKey FROM PrimarySelected")
  Flowable<String> getPrimarySelected();
}
