/*
 * Created by Force Porquillo on 6/16/20 11:57 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/15/20 6:06 PM
 */

package com.force.codes.project.app.data_layer.resources.database.data;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */


import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.force.codes.project.app.data_layer.resources.database.DBConstants;
import com.force.codes.project.app.model.CountryDetails;

import java.util.List;

@Dao
public interface CountryDao{
    @Transaction
    @Query(DBConstants.QUERY_ALL_DATA)
    DataSource.Factory<Integer, CountryDetails> getDataFromDatabase();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(List<CountryDetails> details);

    @Update
    void insertOrRemoveFavorites(CountryDetails details);
}
