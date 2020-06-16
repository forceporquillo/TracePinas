package com.force.codes.project.app.factory;

/*
 * Created by Force Porquillo on 6/4/20 3:55 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 3:55 AM
 *
 */

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.data_layer.repositories.live.LiveOverviewRepository;
import com.force.codes.project.app.presentation_layer.views.fragments.live.LiveDataViewModel;

public class LiveDataViewModelFactory implements ViewModelProvider.Factory{
    private LiveOverviewRepository repository;

    public LiveDataViewModelFactory(LiveOverviewRepository repository){
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if(modelClass.isAssignableFrom(LiveDataViewModel.class)){
            return (T) new LiveDataViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
