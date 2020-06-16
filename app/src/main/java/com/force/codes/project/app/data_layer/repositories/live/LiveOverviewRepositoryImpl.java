package com.force.codes.project.app.data_layer.repositories.live;

/*
 * Created by Force Porquillo on 6/4/20 3:33 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 3:33 AM
 *
 */

import com.force.codes.project.app.data_layer.resources.api.ApiServiceAdapter;
import com.force.codes.project.app.model.WorldData;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class LiveOverviewRepositoryImpl implements LiveOverviewRepository{
    private final ApiServiceAdapter apiServiceAdapter;

    public LiveOverviewRepositoryImpl(ApiServiceAdapter apiServiceAdapter){
        this.apiServiceAdapter = apiServiceAdapter;
    }

    @Override
    public Flowable<WorldData> getWorldDataFromNetwork(){
       return apiServiceAdapter.getWorldData()
               .subscribeOn(Schedulers.io());

    }
}
