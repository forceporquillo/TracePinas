/*
 * Created by Force Porquillo on 6/14/20 4:32 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.data_layer.resources.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.force.codes.project.app.app.MyApplication;
import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.PHData;
import com.force.codes.project.app.data_layer.model.TypeConverter.RoomTypeConverter;
import com.force.codes.project.app.data_layer.resources.database.data.CountryDao;
import com.force.codes.project.app.data_layer.resources.database.data.LiveDataDao;
import com.force.codes.project.app.data_layer.resources.database.data.MapDao;

@Database(
        entities = {
                CountryDetails.class,
                PHData.class,
                GlobalData.class
        }, version = 37)

@TypeConverters({
        RoomTypeConverter.class
})

public abstract class LocalDatabase extends RoomDatabase{
    static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase sqLiteDatabase){
            super.onCreate(sqLiteDatabase);
        }
    };

    private static volatile LocalDatabase INSTANCE;

    public static LocalDatabase getInstance(){
        return INSTANCE;
    }

    public static void setInstance(MyApplication application){
        if(INSTANCE == null){
            synchronized(LocalDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(application,
                            LocalDatabase.class, "COVID19DATA.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
    }

    public abstract CountryDao countryDao();

    public abstract LiveDataDao dataDao();

    public abstract MapDao mapDao();
}