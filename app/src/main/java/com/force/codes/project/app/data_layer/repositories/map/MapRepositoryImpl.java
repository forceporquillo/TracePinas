/*
 * Created by Force Porquillo on 6/20/20 8:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:30 PM
 */

package com.force.codes.project.app.data_layer.repositories.map;

import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.PHData;
import com.force.codes.project.app.data_layer.resources.api.ApiModule;
import com.force.codes.project.app.data_layer.resources.api.RemoteApiAdapter;
import com.force.codes.project.app.data_layer.resources.database.data.MapDao;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class MapRepositoryImpl implements MapRepository{
    private final MapDao mapDao;
    private final RemoteApiAdapter serviceAdapter;

    public MapRepositoryImpl(MapDao mapDao, RemoteApiAdapter serviceAdapter){
        this.mapDao = mapDao;
        this.serviceAdapter = serviceAdapter;
    }

    @Override
    public Flowable <PHData> getAllPHData(){
        return Flowable.mergeDelayError(serviceAdapter
                .getPhData(ApiModule.LOCAL_URL)
                .doOnNext(mapDao::savePhData)
                .subscribeOn(Schedulers.io()), mapDao.getPHDataFromDB()
                .subscribeOn(Schedulers.io()));
    }

    @Override
    public Flowable <List <GlobalData>> getAllGlobalData(){
        return Flowable.mergeDelayError(serviceAdapter
                .getGlobalData(ApiModule.GLOBAL_CASE)
                .doOnNext(mapDao::saveGlobalData)
                .subscribeOn(Schedulers.io()), mapDao.getGlobalDataFromDB()
                .subscribeOn(Schedulers.io()));
    }
}
