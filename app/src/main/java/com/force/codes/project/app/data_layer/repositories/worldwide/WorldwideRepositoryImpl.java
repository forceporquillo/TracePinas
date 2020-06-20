package com.force.codes.project.app.data_layer.repositories.worldwide;

/*
 * Created by Force Porquillo on 6/4/20 8:01 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 7:53 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.force.codes.project.app.data_layer.model.Response;
import com.force.codes.project.app.data_layer.resources.api.ApiServiceAdapter;
import com.force.codes.project.app.data_layer.resources.database.data.CountryDao;
import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.service.executors.AppExecutors;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class WorldwideRepositoryImpl implements WorldwideRepository{
    private CountryDao countryDao;
    private ApiServiceAdapter serviceAdapter;
    private AppExecutors executors;

    public WorldwideRepositoryImpl(
            final CountryDao countryDao,
            final ApiServiceAdapter adapter,
            final AppExecutors executors){
        this.countryDao = countryDao;
        this.serviceAdapter = adapter;
        this.executors = executors;
    }

    @Override
    public Flowable<List<CountryDetails>> getDataFromRemoteService(){
        return serviceAdapter.getSortedCases()
                .subscribeOn(Schedulers.computation());
    }

    @Override
    public LiveData<PagedList<CountryDetails>>
    getDataFromDatabase(PagedList.Config config){
        DataSource.Factory<Integer, CountryDetails>
                detailsFactory = countryDao.getDataFromDatabase();
        return new LivePagedListBuilder<>(detailsFactory, config).build();
    }

    /**
     * @param detailsList insert or update list to database.
     * @method saveDatabase() insert or update list in the background thread.
     */
    @Override
    public void saveDatabase(List<CountryDetails> detailsList){
        executors.diskIO().execute(() ->
                countryDao.insertOrUpdate(detailsList));
    }

    /**
     * @method addOrRemoveFavorites()
     * inserts favorite country to local database.
     */
    @Override
    public void updateFavorites(CountryDetails details){
        executors.diskIO().execute(() ->
                countryDao.insertOrRemoveFavorites(details));
    }

    @Override
    public Flowable<Response> getResponseFromNetwork(){
       return serviceAdapter.getResponse();
    }

    @Override
    public void insertResponse(Response response){
        executors.diskIO().execute(() ->
                countryDao.insertData(response));
    }
}