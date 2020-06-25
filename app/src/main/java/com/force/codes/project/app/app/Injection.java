/*
 * Created by Force Porquillo on 6/8/20 4:44 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.app;


import android.content.Context;

import com.force.codes.project.app.data_layer.repositories.live.LiveOverviewRepository;
import com.force.codes.project.app.data_layer.repositories.live.LiveOverviewRepositoryImpl;
import com.force.codes.project.app.data_layer.repositories.map.MapRepository;
import com.force.codes.project.app.data_layer.repositories.map.MapRepositoryImpl;
import com.force.codes.project.app.data_layer.repositories.worldwide.WorldwideRepository;
import com.force.codes.project.app.data_layer.repositories.worldwide.WorldwideRepositoryImpl;
import com.force.codes.project.app.data_layer.resources.api.ApiModule;
import com.force.codes.project.app.data_layer.resources.api.RetrofitClient;
import com.force.codes.project.app.data_layer.resources.database.LocalDatabase;
import com.force.codes.project.app.factory.LiveDataViewModelFactory;
import com.force.codes.project.app.factory.MapViewModelFactory;
import com.force.codes.project.app.factory.WorldwideViewModelFactory;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.OnRequestResponse;
import com.force.codes.project.app.service.executors.AppExecutors;

public class Injection{
    public static WorldwideViewModelFactory providesViewModelFactory(OnRequestResponse response, Context context, AppExecutors appExecutors){
        WorldwideRepository worldwideRepository = providesWorldwideDataSource(context, appExecutors);
        return new WorldwideViewModelFactory(worldwideRepository, appExecutors, response);
    }

    private static WorldwideRepositoryImpl providesWorldwideDataSource(Context context, AppExecutors appExecutors){
        LocalDatabase localDatabase = LocalDatabase.getInstance();
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        return new WorldwideRepositoryImpl(localDatabase.countryDao(), retrofitClient.providesApiServiceAdapter(), appExecutors);
    }

    public static LiveDataViewModelFactory providesViewModelFactory(AppExecutors appExecutors){
        LiveOverviewRepository liveOverviewRepository = providesWorldwideDataSource();
        return new LiveDataViewModelFactory(liveOverviewRepository);
    }

    private static LiveOverviewRepositoryImpl providesWorldwideDataSource(){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        return new LiveOverviewRepositoryImpl(retrofitClient.providesApiServiceAdapter());
    }

    public static MapViewModelFactory providesMapViewModelFactory(){
        MapRepository mapRepository = providesMapDataSource();
        return new MapViewModelFactory(mapRepository);
    }

    private static MapRepositoryImpl providesMapDataSource(){
        LocalDatabase localDatabase = LocalDatabase.getInstance();
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        return new MapRepositoryImpl(localDatabase.mapDao(), retrofitClient.providesApiServiceAdapter(), new AppExecutors());
    }
}












