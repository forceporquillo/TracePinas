/*
 * Created by Force Porquillo on 7/2/20 1:45 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 1:45 PM
 */

package com.force.codes.project.app.app.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.force.codes.project.app.data_layer.resources.database.AppDatabase;
import com.force.codes.project.app.data_layer.resources.database.CountryDao;
import com.force.codes.project.app.data_layer.resources.database.LiveDataDao;
import com.force.codes.project.app.data_layer.resources.database.MapDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule{
    static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase sqLiteDatabase){
            super.onCreate(sqLiteDatabase);
        }
    };

    @Singleton
    @Provides
    static AppDatabase providesLocalDatabase(Application application){
        return Room.databaseBuilder(application, AppDatabase.class, "Covid19_Data.db")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build();
    }

    @Singleton
    @Provides
    static MapDao provideMapAccess(AppDatabase database){
        return database.mapDao();
    }

    @Singleton
    @Provides
    static CountryDao provideCountryAccess(AppDatabase database){
        return database.countryDao();
    }

    @Singleton
    @Provides
    static LiveDataDao provideLiveDataAccess(AppDatabase database){
        return database.liveDataDao();
    }
}
