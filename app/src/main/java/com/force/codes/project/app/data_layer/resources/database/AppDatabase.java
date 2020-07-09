/*
 * Created by Force Porquillo on 7/2/20 1:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 1:49 PM
 */

package com.force.codes.project.app.data_layer.resources.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.force.codes.project.app.app.constants.DatabaseConstants;
import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.LocalData;
import com.force.codes.project.app.data_layer.model.NewsData;

@Database(
        entities = {
                CountryDetails.class,
                LocalData.class,
                GlobalData.class,
                NewsData.class
        }, version = DatabaseConstants.DATABASE_VERSION,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase{
    public abstract MapDao mapDao();

    public abstract CountryDao countryDao();

    public abstract LiveDataDao liveDataDao();

    public abstract NewsDao newsDao();
}
