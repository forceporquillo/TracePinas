/*
 * Created by Force Porquillo on 6/4/20 7:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 7:47 AM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.data_layer.model.WorldData;
import com.force.codes.project.app.data_layer.repositories.interfaces.LiveOverviewRepository;
import com.force.codes.project.app.data_layer.resources.api.RemoteApiAdapter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class LiveOverviewRepositoryImpl implements LiveOverviewRepository{

    private final RemoteApiAdapter apiAdapter;

    @Inject
    public LiveOverviewRepositoryImpl(RemoteApiAdapter apiAdapter){
        this.apiAdapter = apiAdapter;
    }

    @Override
    public Flowable <WorldData> getWorldDataFromNetwork(){
        return apiAdapter.getWorldData(ApiConstants.CORONA_GLOBAL_CASES)
                .subscribeOn(Schedulers.io());
    }
}