/*
 * Created by Force Porquillo on 7/2/20 1:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 1:49 PM
 */

package com.force.codes.project.app.data_layer.resources.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.force.codes.project.app.app.constants.DatabaseConstants;
import com.force.codes.project.app.data_layer.converters.LocalDataConverter;
import com.force.codes.project.app.data_layer.converters.TwitterMediaConverter;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.model.map_data.LocalData;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.data_layer.model.world.GlobalData;

@Database(
    entities = {
        CountryDetails.class,
        LocalData.class,
        GlobalData.class,
        ArticlesItem.class,
        TwitterData.class
    }, version = DatabaseConstants.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters({
    LocalDataConverter.class,
    TwitterMediaConverter.class
})
public abstract class AppDatabase extends RoomDatabase {
  public abstract MapDao mapDao();

  public abstract WorldwideDao worldwideDao();

  public abstract LiveDataDao liveDataDao();

  public abstract NewsDao newsDao();
}
