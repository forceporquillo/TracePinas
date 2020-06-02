package com.force.codes.project.covid19.module;

/*
 * Created by Force Porquillo on 6/2/20 1:51 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import android.content.Context;

import com.force.codes.project.covid19.data.repositories.local.LocalRepository;
import com.force.codes.project.covid19.data.repositories.local.LocalRepositoryImpl;
import com.force.codes.project.covid19.data.repositories.mainrepository.DataSourceRepository;
import com.force.codes.project.covid19.data.repositories.mainrepository.DataSourceRepositoryImpl;
import com.force.codes.project.covid19.data.repositories.remote.RemoteRepository;
import com.force.codes.project.covid19.data.repositories.remote.RemoteRepositoryImpl;
import com.force.codes.project.covid19.data.resources.database.LocalDatabase;
import com.force.codes.project.covid19.data.resources.web.api.RetrofitClient;
import com.force.codes.project.covid19.service.executors.AppExecutors;
import com.force.codes.project.covid19.controller.factory.GlobalViewModelFactory;

import javax.inject.Singleton;

/**
 * manually injecting data sources for flexibility in client side,
 * testing can be easily performed using mock up objects.
 * lessens the boilerplate code and module complexity.
 **/

public class Injection{
    @Singleton
    public static GlobalViewModelFactory providesViewModelFactory(Context context, AppExecutors appExecutors){
        DataSourceRepository dataSourceRepository = providesDataSource(context, appExecutors);
        return new GlobalViewModelFactory(dataSourceRepository, appExecutors);
    }

    @Singleton
    private static DataSourceRepositoryImpl providesDataSource(Context context, AppExecutors appExecutors){
        LocalRepository localRepository = providesLocalDataSource(context, appExecutors);
        RemoteRepository remoteRepository = providesRemoteDataSource(appExecutors);
        return new DataSourceRepositoryImpl(localRepository, remoteRepository, appExecutors);
    }

    @Singleton
    private static LocalRepositoryImpl providesLocalDataSource(Context context, AppExecutors appExecutors){
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        return new LocalRepositoryImpl(localDatabase.dao(), appExecutors);
    }

    @Singleton
    private static RemoteRepositoryImpl providesRemoteDataSource(AppExecutors appExecutors){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        return new RemoteRepositoryImpl(retrofitClient.providesApiServiceAdapter(), appExecutors);
    }
}












