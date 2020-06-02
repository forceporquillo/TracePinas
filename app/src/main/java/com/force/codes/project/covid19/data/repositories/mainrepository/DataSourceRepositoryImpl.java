package com.force.codes.project.covid19.data.repositories.mainrepository;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import com.force.codes.project.covid19.data.repositories.local.LocalRepository;
import com.force.codes.project.covid19.data.repositories.remote.RemoteRepository;
import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;
import com.force.codes.project.covid19.service.executors.AppExecutors;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class DataSourceRepositoryImpl implements DataSourceRepository{
    private static final int INITIAL_DELAY = 0;
    private static final int CALL_INTERVAL = 5;

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;
    private AppExecutors appExecutors;

    public DataSourceRepositoryImpl(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors){
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    // automatically emits data from remote repository -> cache to database and update UI.
    @Override
    public Flowable<List<CountryDetails>> getDataFromRemoteService(){
        return Flowable.interval(INITIAL_DELAY, CALL_INTERVAL, TimeUnit.MINUTES)
                .flatMap(map -> remoteRepository.getDataFromRemoteRepository())
                .subscribeOn(Schedulers.computation())
                .doOnNext(details -> localRepository.saveDatabase(details))
                .onErrorResumeNext(throwable -> {
                    appExecutors.diskIO().execute(() ->
                            localRepository.getDataFromDatabase());
                    return Flowable.error(throwable);
                }).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<CountryDetails>> getDataResults(){
        return localRepository.getDataFromDatabase()
                .filter(list -> !list.isEmpty())
                .switchIfEmpty(Flowable.defer(() ->
                        remoteRepository.getDataFromRemoteRepository()))
                .subscribeOn(Schedulers.io());
    }

    public void insertCountry(CountryDetails details){
        appExecutors.diskIO().execute(() ->
                localRepository.insertFavorites(details));

    }
}
