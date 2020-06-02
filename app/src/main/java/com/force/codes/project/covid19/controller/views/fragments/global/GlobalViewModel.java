package com.force.codes.project.covid19.controller.views.fragments.global;

/*
 * Created by Force Porquillo on 6/2/20 1:57 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.force.codes.project.covid19.data.repositories.mainrepository.DataSourceRepository;
import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;
import com.force.codes.project.covid19.service.executors.AppExecutors;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GlobalViewModel extends ViewModel{
    private final DataSourceRepository dataSourceRepository;
    private final AppExecutors appExecutors;
    private final MutableLiveData<List<CountryDetails>> mutableLiveData;
    private final CompositeDisposable compositeDisposable;

    public GlobalViewModel(DataSourceRepository dataSourceRepository, AppExecutors appExecutors, MutableLiveData<List<CountryDetails>> listMutableLiveData, CompositeDisposable compositeDisposable){
        this.dataSourceRepository = dataSourceRepository;
        this.appExecutors = appExecutors;
        this.mutableLiveData = listMutableLiveData;
        this.compositeDisposable = compositeDisposable;
    }

    private Flowable<List<CountryDetails>> getData(){
        Flowable<List<CountryDetails>> localDatabase = dataSourceRepository
                .getDataResults()
                .subscribeOn(Schedulers.io());

        Flowable<List<CountryDetails>> remoteRepository = dataSourceRepository
                .getDataFromRemoteService()
                .subscribeOn(Schedulers.io());

        return localDatabase
                .filter(details -> !details.isEmpty())
                .mergeWith(remoteRepository);
    }

    public void provideObservableData(){
        compositeDisposable.add(getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableLiveData::setValue)
        );
    }

    public LiveData<List<CountryDetails>> getModelLiveData(){
        provideObservableData();
        return mutableLiveData;
    }

    public void insertCountryFavorite(CountryDetails details){
        appExecutors.diskIO().execute(() ->
                dataSourceRepository.insertCountry(details));
    }

    @Override
    protected void onCleared(){
        boolean isDisposed = compositeDisposable != null && !compositeDisposable.isDisposed();
        if(isDisposed){
            compositeDisposable.clear();
            compositeDisposable.dispose();
        }
        super.onCleared();
    }
}

