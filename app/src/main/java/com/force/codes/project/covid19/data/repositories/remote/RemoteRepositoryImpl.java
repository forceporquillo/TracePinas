package com.force.codes.project.covid19.data.repositories.remote;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import com.force.codes.project.covid19.data.resources.web.api.ApiServiceAdapter;
import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;
import com.force.codes.project.covid19.service.executors.AppExecutors;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class RemoteRepositoryImpl implements RemoteRepository{
    private final ApiServiceAdapter apiServiceAdapter;
    private final AppExecutors appExecutors;

    public RemoteRepositoryImpl(ApiServiceAdapter apiServiceAdapter, AppExecutors appExecutors){
        this.apiServiceAdapter = apiServiceAdapter;
        this.appExecutors = appExecutors;
    }

    @Override
    public Flowable<List<CountryDetails>> getDataFromRemoteRepository(){
        return apiServiceAdapter.getSortedCases()
                .observeOn(Schedulers.from(appExecutors.networkIO()));
    }
}
