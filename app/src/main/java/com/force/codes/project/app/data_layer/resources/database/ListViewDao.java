package com.force.codes.project.app.data_layer.resources.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.force.codes.project.app.app.constants.DatabaseConstants;
import com.force.codes.project.app.data_layer.model.PrimarySelected;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public interface ListViewDao {
  @Query(DatabaseConstants.QUERY_LIST_VIEW_DATA)
  Flowable<List<CountryDetails>>
  getCountryDetails(boolean cases);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertSelected(PrimarySelected selected);
}
