/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

/*
 * Created by Force Porquillo on 6/2/20 1:22 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.force.codes.project.app.app.constants.PageListConstants;
import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.WorldwideRepository;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.OnRequestResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WorldwideViewModel extends BaseViewModel{
    static final PagedList.Config config = new PagedList.Config.Builder()
            .setPageSize(PageListConstants.PAGE_SIZE)
            .setMaxSize(PageListConstants.PAGE_MAX_SIZE)
            .setEnablePlaceholders(false)
            .build();

    private LiveData<PagedList<CountryDetails>> listLiveData;
    private final WorldwideRepository repository;

    @Inject
    public WorldwideViewModel(WorldwideRepository repository){
        this.repository = repository;
    }

    public LiveData <PagedList <CountryDetails>> getDataFromDatabase(){
        if(listLiveData == null){
            return listLiveData = repository.getDataFromDatabase(config);
        }
        return listLiveData;
    }

    public void getDataFromNetwork(){
        Disposable disposable = Flowable.just(1)
                .subscribeOn(Schedulers.computation())
                .flatMap(list -> repository.getDataFromRemoteService())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(repository::saveDatabase, Throwable::printStackTrace);

        addToUnsubscribed(disposable);
    }
}

