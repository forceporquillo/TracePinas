package com.force.codes.project.covid19.data.resources.database;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;

@Database(entities = {CountryDetails.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase{
    private static volatile LocalDatabase INSTANCE;

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
        }
    };

    public static LocalDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized(LocalDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDatabase.class, "WorldwideCases.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CountryDao dao();
}
