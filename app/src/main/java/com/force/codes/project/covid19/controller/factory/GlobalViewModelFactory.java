package com.force.codes.project.covid19.controller.factory;

/*
 * Created by Force Porquillo on 6/2/20 1:49 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.covid19.data.repositories.mainrepository.DataSourceRepository;
import com.force.codes.project.covid19.controller.views.fragments.global.GlobalViewModel;
import com.force.codes.project.covid19.service.executors.AppExecutors;

import io.reactivex.disposables.CompositeDisposable;

public class GlobalViewModelFactory implements ViewModelProvider.Factory{

    private DataSourceRepository dataSourceRepository;
    private AppExecutors appExecutors;

    public GlobalViewModelFactory(DataSourceRepository dataSourceRepository, AppExecutors appExecutors){
        this.dataSourceRepository = dataSourceRepository;
        this.appExecutors = appExecutors;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if(modelClass.isAssignableFrom(GlobalViewModel.class)){
            return (T) new GlobalViewModel(dataSourceRepository, appExecutors,
                    new MutableLiveData<>(), new CompositeDisposable());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
