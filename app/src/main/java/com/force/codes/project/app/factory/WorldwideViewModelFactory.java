/*
 * Created by Force Porquillo on 6/2/20 5:09 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.factory;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableLong;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.data_layer.repositories.worldwide.WorldwideRepository;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.OnRequestResponse;
import com.force.codes.project.app.presentation_layer.views.fragments.worldwide.WorldwideViewModel;
import com.force.codes.project.app.service.executors.AppExecutors;

import io.reactivex.disposables.CompositeDisposable;

public class WorldwideViewModelFactory implements ViewModelProvider.Factory{
    private WorldwideRepository worldwideRepository;
    private AppExecutors appExecutors;
    private OnRequestResponse response;

    public WorldwideViewModelFactory(
            WorldwideRepository worldwideRepository,
            AppExecutors appExecutors,
            OnRequestResponse response){
        this.worldwideRepository = worldwideRepository;
        this.appExecutors = appExecutors;
        this.response = response;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if(modelClass.isAssignableFrom(WorldwideViewModel.class)){
            return (T) new WorldwideViewModel(worldwideRepository,
                    appExecutors, new CompositeDisposable(), response, new ObservableLong());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
};

