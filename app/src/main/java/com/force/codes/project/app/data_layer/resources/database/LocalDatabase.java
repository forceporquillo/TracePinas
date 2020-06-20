/*
 * Created by Force Porquillo on 6/14/20 4:32 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.data_layer.resources.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.force.codes.project.app.data_layer.resources.database.data.CountryDao;
import com.force.codes.project.app.data_layer.resources.database.data.LiveDataDao;
import com.force.codes.project.app.data_layer.model.CountryDetails;

@Database(
        entities = {
                CountryDetails.class
        }, version = 18)

public abstract class LocalDatabase extends RoomDatabase{
    private static volatile LocalDatabase INSTANCE;

    static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase sqLiteDatabase){
            super.onCreate(sqLiteDatabase);
        }
    };

    public static LocalDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized(LocalDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context
                                    .getApplicationContext(),
                            LocalDatabase.class, "LocalDatabase")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CountryDao countryDao();

    public abstract LiveDataDao dataDao();
}