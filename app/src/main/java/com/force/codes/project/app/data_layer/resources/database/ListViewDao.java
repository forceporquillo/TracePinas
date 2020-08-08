package com.force.codes.project.app.data_layer.resources.database;

import androidx.room.Dao;
import androidx.room.Query;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import io.reactivex.Flowable;
import java.util.List;

@Dao public
interface ListViewDao {
  @Query("SELECT * FROM CountryDetails")
  Flowable<List<CountryDetails>> getCountryDetails();
}
