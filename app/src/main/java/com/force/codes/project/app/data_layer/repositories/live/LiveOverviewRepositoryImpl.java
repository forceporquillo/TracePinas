package com.force.codes.project.app.data_layer.repositories.live;

/*
 * Created by Force Porquillo on 6/4/20 3:33 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 3:33 AM
 *
 */

import com.force.codes.project.app.data_layer.model.WorldData;
import com.force.codes.project.app.data_layer.resources.api.ApiModule;
import com.force.codes.project.app.data_layer.resources.api.RemoteApiAdapter;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class LiveOverviewRepositoryImpl implements LiveOverviewRepository{
    private final RemoteApiAdapter remoteApiAdapter;

    public LiveOverviewRepositoryImpl(RemoteApiAdapter remoteApiAdapter){
        this.remoteApiAdapter = remoteApiAdapter;
    }

    @Override
    public Flowable <WorldData> getWorldDataFromNetwork(){
        return remoteApiAdapter.getWorldData(ApiModule.CORONA_GLOBAL_CASES)
                .subscribeOn(Schedulers.io());
    }
}
