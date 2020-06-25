/*
 * Created by Force Porquillo on 6/20/20 8:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:30 PM
 */

package com.force.codes.project.app.data_layer.repositories.map;

import com.force.codes.project.app.data_layer.model.ListData;
import com.force.codes.project.app.data_layer.resources.api.ApiModule;
import com.force.codes.project.app.data_layer.resources.api.ApiServiceAdapter;
import com.force.codes.project.app.data_layer.resources.database.data.MapDao;
import com.force.codes.project.app.service.executors.AppExecutors;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class MapRepositoryImpl implements MapRepository{
    private MapDao mapDao;
    private ApiServiceAdapter serviceAdapter;
    private AppExecutors executors;

    public MapRepositoryImpl(MapDao mapDao, ApiServiceAdapter serviceAdapter, AppExecutors appExecutors){
        this.mapDao = mapDao;
        this.serviceAdapter = serviceAdapter;
        this.executors = appExecutors;
    }

    @Override
    public Flowable<ListData> getListDataFromNetwork(){
        return serviceAdapter.getResponse(ApiModule.LOCAL_URL);
    }

    @Override
    public Flowable<ListData> getDataFromDatabase(){
        return mapDao.getDataFromNetwork()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void insertOrUpdate(ListData listData){
        executors.diskIO().execute(() ->
                mapDao.savePhData(listData));
    }
}
