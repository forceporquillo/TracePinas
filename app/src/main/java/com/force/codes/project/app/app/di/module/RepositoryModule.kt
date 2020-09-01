/*
 * Created by Force Porquillo on 7/2/20 3:47 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/28/20 1:53 PM
 */
package com.force.codes.project.app.app.di.module

import com.force.codes.project.app.data_layer.repositories.implementations.LiveOverviewRepositoryImpl
import com.force.codes.project.app.data_layer.repositories.implementations.MapRepositoryImpl
import com.force.codes.project.app.data_layer.repositories.implementations.MyCountryRepositoryImpl
import com.force.codes.project.app.data_layer.repositories.implementations.NewsRepositoryImpl
import com.force.codes.project.app.data_layer.repositories.implementations.OverAllRepositoryImpl
import com.force.codes.project.app.data_layer.repositories.implementations.WorldwideRepositoryImpl
import com.force.codes.project.app.data_layer.repositories.interfaces.LiveOverviewRepository
import com.force.codes.project.app.data_layer.repositories.interfaces.MapRepository
import com.force.codes.project.app.data_layer.repositories.interfaces.MyCountryRepository
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository
import com.force.codes.project.app.data_layer.repositories.interfaces.OverAllRepository
import com.force.codes.project.app.data_layer.repositories.interfaces.WorldwideRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
  @Binds
  abstract fun liveOverviewRepository(
    repository: LiveOverviewRepositoryImpl
  ): LiveOverviewRepository

  @Binds
  abstract fun worldwideRepository(
    repository: WorldwideRepositoryImpl
  ): WorldwideRepository?

  @Binds abstract fun mapRepository(
    repository: MapRepositoryImpl
  ): MapRepository
  @Binds abstract fun newsRepository(
    repository: NewsRepositoryImpl
  ): NewsRepository

  @Binds abstract fun overAllRepository(
    repository: OverAllRepositoryImpl
  ): OverAllRepository

  @Binds
  abstract fun myCountryRepository(
    repository: MyCountryRepositoryImpl
  ): MyCountryRepository
}