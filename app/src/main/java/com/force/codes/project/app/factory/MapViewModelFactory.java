/*
 * Created by Force Porquillo on 6/21/20 12:52 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/21/20 12:52 AM
 */

package com.force.codes.project.app.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.data_layer.repositories.map.MapRepository;
import com.force.codes.project.app.presentation_layer.views.fragments.mapview.MapViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MapViewModelFactory implements ViewModelProvider.Factory{
    private MapRepository mapRepository;

    @Inject
    CompositeDisposable disposable;

    public MapViewModelFactory(MapRepository mapRepository){
        this.mapRepository = mapRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> viewModelClass){
        if(viewModelClass.isAssignableFrom(MapViewModel.class)){
            return (T) new MapViewModel(mapRepository, disposable);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
