/*
 * Created by Force Porquillo on 7/2/20 1:45 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 1:45 PM
 */
package com.force.codes.project.app.app.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import com.force.codes.project.app.data_layer.resources.database.AppDatabase
import com.force.codes.project.app.data_layer.resources.database.ListViewDao
import com.force.codes.project.app.data_layer.resources.database.LiveDataDao
import com.force.codes.project.app.data_layer.resources.database.MapDao
import com.force.codes.project.app.data_layer.resources.database.MyCountryDao
import com.force.codes.project.app.data_layer.resources.database.NavHostDao
import com.force.codes.project.app.data_layer.resources.database.NewsDao
import com.force.codes.project.app.data_layer.resources.database.WorldwideDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
  var roomCallback = object : Callback() {}

  @JvmStatic @Singleton @Provides
  fun providesLocalDatabase(
    application: Application?,
  ): AppDatabase {
    return Room.databaseBuilder(application!!, AppDatabase::class.java,
        "AppDatabase.db")
        .fallbackToDestructiveMigration()
        .addCallback(roomCallback)
        .build()
  }

  @JvmStatic @Singleton @Provides
  fun provideMapDao(database: AppDatabase): MapDao {
    return database.mapDao()
  }

  @JvmStatic @Singleton @Provides
  fun provideWorldwideDao(database: AppDatabase): WorldwideDao {
    return database.worldwideDao()
  }

  @JvmStatic @Singleton @Provides
  fun provideLiveDataDao(database: AppDatabase): LiveDataDao {
    return database.liveDataDao()
  }

  @JvmStatic @Singleton @Provides
  fun provideNewsDao(database: AppDatabase): NewsDao {
    return database.newsDao()
  }

  @JvmStatic @Singleton @Provides
  fun providesMyCountryDao(database: AppDatabase): MyCountryDao {
    return database.myCountryDao()
  }

  @JvmStatic @Singleton @Provides
  fun providesListViewDao(database: AppDatabase): ListViewDao {
    return database.listViewDao()
  }

  @JvmStatic @Singleton @Provides
  fun providesNavHostDao(database: AppDatabase): NavHostDao {
    return database.navHostDao()
  }
}