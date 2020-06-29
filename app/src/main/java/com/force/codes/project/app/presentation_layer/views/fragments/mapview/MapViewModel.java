package com.force.codes.project.app.presentation_layer.views.fragments.mapview;

/*
 * Created by Force Porquillo on 6/4/20 6:06 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/26/20 11:24 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.PHData;
import com.force.codes.project.app.data_layer.repositories.map.MapRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class MapViewModel extends ViewModel{
    private MapRepository mapRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData <PHData> mutablePhData = new MutableLiveData <>();
    private MutableLiveData <List <GlobalData>> mutableGlobalData = new MutableLiveData <>();

    public MapViewModel(MapRepository mapRepository, CompositeDisposable disposable){
        this.mapRepository = mapRepository;
        this.compositeDisposable = disposable;
    }

    public void getAllPhData(){
        compositeDisposable.add(mapRepository.getAllPHData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(phData ->
                        mutablePhData.setValue(phData), Timber::e));
    }

    public LiveData <PHData> getMutablePhData(){
        return mutablePhData;
    }

    public void getListGlobalData(){
        compositeDisposable.add(mapRepository.getAllGlobalData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(globalData ->
                        mutableGlobalData.setValue(globalData), Timber::e));
    }

    public LiveData <List <GlobalData>> getMutableGlobalData(){
        return mutableGlobalData;
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        compositeDisposable.dispose();
    }
}
