/*
 * Created by Force Porquillo on 6/20/20 8:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:30 PM
 */

package com.force.codes.project.app.data_layer.repositories.map;

import com.force.codes.project.app.data_layer.model.ListData;
import com.force.codes.project.app.data_layer.resources.api.ApiServiceAdapter;
import com.force.codes.project.app.data_layer.resources.database.data.MapDao;

import io.reactivex.Flowable;

public class MapRepositoryImpl implements MapRepository{
    private MapDao mapDao;
    private ApiServiceAdapter serviceAdapter;

    public MapRepositoryImpl(MapDao mapDao, ApiServiceAdapter serviceAdapter){
        this.mapDao = mapDao;
        this.serviceAdapter = serviceAdapter;
    }

    @Override
    public Flowable<ListData> getListDataFromNetwork(){
        return null;
    }

    @Override
    public void insertOrUpdate(ListData listData){

    }
}
