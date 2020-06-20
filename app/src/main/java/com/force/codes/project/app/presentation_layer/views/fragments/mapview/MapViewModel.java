package com.force.codes.project.app.presentation_layer.views.fragments.mapview;

/*
 * Created by Force Porquillo on 6/4/20 6:06 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 6:06 AM
 *
 */

import androidx.lifecycle.ViewModel;

import com.force.codes.project.app.data_layer.repositories.map.MapRepository;

import io.reactivex.disposables.CompositeDisposable;

public class MapViewModel extends ViewModel{
    private MapRepository mapRepository;
    private CompositeDisposable compositeDisposable;

    public MapViewModel(MapRepository mapRepository, CompositeDisposable disposable){
        this.mapRepository = mapRepository;
        this.compositeDisposable = disposable;
    }


}
