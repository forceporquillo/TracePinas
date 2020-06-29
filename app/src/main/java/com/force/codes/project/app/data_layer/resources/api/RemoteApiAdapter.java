package com.force.codes.project.app.data_layer.resources.api;

/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/12/20 3:22 PM
 *
 */

import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.PHData;
import com.force.codes.project.app.data_layer.model.WorldData;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RemoteApiAdapter{
    @GET
    Flowable <List <CountryDetails>>
    getSortedCases(@Url String url);

    @GET
    Flowable <List <GlobalData>>
    getGlobalData(@Url String url);

    @GET
    Flowable <PHData>
    getPhData(@Url String url);

    @GET
    Flowable <WorldData>
    getWorldData(@Url String url);
}
