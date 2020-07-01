/*
 * Created by Force Porquillo on 7/1/20 7:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/30/20 3:12 AM
 */

package com.force.codes.project.app.data_layer.repositories.implementation;

/*
 * Created by Force Porquillo on 5/7/20 8:01 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 7:53 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.WorldwideRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiModule;
import com.force.codes.project.app.data_layer.resources.api.RemoteApiAdapter;
import com.force.codes.project.app.data_layer.resources.database.data.CountryDao;
import com.force.codes.project.app.service.executors.AppExecutors;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class WorldwideRepositoryImpl implements WorldwideRepository{
    private CountryDao countryDao;
    private RemoteApiAdapter serviceAdapter;
    private AppExecutors executors;

    public WorldwideRepositoryImpl(
            final CountryDao countryDao,
            final RemoteApiAdapter adapter,
            final AppExecutors executors){
        this.countryDao = countryDao;
        this.serviceAdapter = adapter;
        this.executors = executors;
    }

    @Override
    public Flowable <List <CountryDetails>> getDataFromRemoteService(){
        return serviceAdapter.getSortedCases(ApiModule.CORONA_SORTED)
                .subscribeOn(Schedulers.computation());
    }

    @Override
    public LiveData <PagedList <CountryDetails>> getDataFromDatabase(PagedList.Config config){
        DataSource.Factory <Integer, CountryDetails>
                detailsFactory = countryDao.getDataFromDatabase();
        return new LivePagedListBuilder <>(detailsFactory, config).build();
    }

    @Override
    public void saveDatabase(List <CountryDetails> detailsList){
        executors.diskIO().execute(() -> countryDao.insertOrUpdate(detailsList));
    }
}