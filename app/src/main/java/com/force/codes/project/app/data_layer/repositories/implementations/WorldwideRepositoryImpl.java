/*
 * Created by Force Porquillo on 5/7/20 7:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 3:12 AM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;


import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.WorldwideRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import com.force.codes.project.app.data_layer.resources.database.CountryDao;
import com.force.codes.project.app.service.executors.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class WorldwideRepositoryImpl implements WorldwideRepository{
    private CountryDao countryDao;
    private ApiService serviceAdapter;
    private AppExecutors executors;

    @Inject
    public WorldwideRepositoryImpl(CountryDao countryDao, ApiService adapter, AppExecutors executors){
        this.countryDao = countryDao;
        this.serviceAdapter = adapter;
        this.executors = executors;
    }

    @Override
    public Flowable<List<CountryDetails>> getDataFromRemoteService(){
        return serviceAdapter.getSortedCases(ApiConstants.CORONA_SORTED)
                .subscribeOn(Schedulers.computation());
    }

    @Override
    public LiveData<PagedList<CountryDetails>> getDataFromDatabase(PagedList.Config config){
        DataSource.Factory<Integer, CountryDetails>
                detailsFactory = countryDao.getDataFromDatabase();
        return new LivePagedListBuilder<>(detailsFactory, config).build();
    }

    @Override
    public void saveDatabase(List<CountryDetails> detailsList){
        executors.diskIO().execute(() -> countryDao.insertOrUpdate(detailsList));
    }
}