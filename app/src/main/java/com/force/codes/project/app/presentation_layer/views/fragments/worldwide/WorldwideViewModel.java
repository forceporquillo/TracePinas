package com.force.codes.project.app.presentation_layer.views.fragments.worldwide;

/*
 * Created by Force Porquillo on 6/2/20 1:22 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import androidx.databinding.ObservableLong;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.worldwide.WorldwideRepository;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.OnRequestResponse;
import com.force.codes.project.app.service.executors.AppExecutors;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class WorldwideViewModel extends ViewModel{
    private final WorldwideRepository repository;
    private final AppExecutors appExecutors;
    private final CompositeDisposable compositeDisposable;

    private LiveData<PagedList<CountryDetails>> listLiveData;
    private OnRequestResponse requestResponse;
    private ObservableLong longDate;

    public WorldwideViewModel(
            final WorldwideRepository repository, final AppExecutors appExecutors,
            final CompositeDisposable disposable, final OnRequestResponse response,
            final ObservableLong observableLong){
        this.repository = repository;
        this.appExecutors = appExecutors;
        this.compositeDisposable = disposable;
        this.requestResponse = response;
        this.longDate = observableLong;
    }


    static final PagedList.Config config = new PagedList.Config.Builder()
            .setPageSize(11)
            .setMaxSize(215)
            .setEnablePlaceholders(false)
            .build();


    public LiveData<PagedList<CountryDetails>> getDataFromDatabase(){
        if(listLiveData == null){
            return listLiveData = repository.getDataFromDatabase(config);
        }

        return listLiveData;
    }

    public void getDataFromNetwork(){
        compositeDisposable.add(Flowable.just(1)
                .subscribeOn(Schedulers.computation())
                .flatMap(list -> {
                    Timber.d("fetching data from server...");
                    return repository.getDataFromRemoteService();
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(time -> {
                    Timber.d("time update successful...");
                    longDate.set(time.get(0).getUpdated());
                })
                .observeOn(Schedulers.computation())
                .doOnError(error -> {
                    requestResponse.onErrorResponse(true);
                    Timber.d("Error, Failed to attempt force update...");
                })
                .subscribe(list -> {
                    repository.saveDatabase(list);
                    Timber.d("inserting data to database...");
                }, Throwable::printStackTrace)
        );
    }

    public ObservableLong getTimeUpdate(){
        return longDate;
    }


    public void addOrRemoveFavorites(CountryDetails details){
        appExecutors.diskIO().execute(() ->
                repository.updateFavorites(details));
    }


    @Override
    protected void onCleared(){
        super.onCleared();
        if(compositeDisposable != null && !compositeDisposable.isDisposed()){
            compositeDisposable.clear();
        }
    }

    public void forceUpdate(){
        getDataFromNetwork();
    }
}

