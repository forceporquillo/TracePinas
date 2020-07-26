/*
 * Created by Force Porquillo on 7/2/20 3:47 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 3:47 PM
 */

package com.force.codes.project.app.app.di.module;

import com.force.codes.project.app.data_layer.repositories.implementations.LiveOverviewRepositoryImpl;
import com.force.codes.project.app.data_layer.repositories.implementations.MapRepositoryImpl;
import com.force.codes.project.app.data_layer.repositories.implementations.NewsRepositoryImpl;
import com.force.codes.project.app.data_layer.repositories.implementations.OverAllRepositoryImpl;
import com.force.codes.project.app.data_layer.repositories.implementations.WorldwideRepositoryImpl;
import com.force.codes.project.app.data_layer.repositories.interfaces.LiveOverviewRepository;
import com.force.codes.project.app.data_layer.repositories.interfaces.MapRepository;
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository;
import com.force.codes.project.app.data_layer.repositories.interfaces.OverAllRepository;
import com.force.codes.project.app.data_layer.repositories.interfaces.WorldwideRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule{
    @Binds
    abstract LiveOverviewRepository
    LiveOverviewRepository(LiveOverviewRepositoryImpl repository);

    @Binds
    abstract WorldwideRepository
    WorldwideRepository(WorldwideRepositoryImpl repository);

    @Binds
    abstract MapRepository
    MapRepository(MapRepositoryImpl repository);

    @Binds
    abstract NewsRepository
    NewsRepository(NewsRepositoryImpl repository);

    @Binds
    abstract OverAllRepository
    OverAllRepository(OverAllRepositoryImpl repository);
}
