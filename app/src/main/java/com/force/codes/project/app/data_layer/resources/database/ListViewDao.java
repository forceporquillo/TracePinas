package com.force.codes.project.app.data_layer.resources.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.force.codes.project.app.data_layer.model.PrimarySelected;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public interface ListViewDao {
  @Query("SELECT * FROM CountryDetails")
  Flowable<List<CountryDetails>> getCountryDetails();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertCountry(PrimarySelected selected);

}
