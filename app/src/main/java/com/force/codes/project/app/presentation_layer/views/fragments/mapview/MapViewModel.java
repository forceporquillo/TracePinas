package com.force.codes.project.app.presentation_layer.views.fragments.mapview;

/*
 * Created by Force Porquillo on 6/4/20 6:06 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/26/20 11:24 PM
 *
 */

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.ListData;
import com.force.codes.project.app.data_layer.repositories.map.MapRepository;

import org.reactivestreams.Publisher;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MapViewModel extends ViewModel{
    private MapRepository mapRepository;
    private CompositeDisposable compositeDisposable;
    private LiveData<ListData> liveData;
    private LiveData<List<GlobalData>> listLiveData;

    public MapViewModel(MapRepository mapRepository, CompositeDisposable disposable){
        this.mapRepository = mapRepository;
        this.compositeDisposable = disposable;
    }

    public LiveData<ListData> getLocalLiveData(){
        if(liveData == null){
            liveData = LiveDataReactiveStreams.fromPublisher(mapRepository
                    .getPHDataFromDatabase()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            );
        }
        return liveData;
    }

    public void getLocalDataFromNetwork(){
        System.out.println("updating...");
        compositeDisposable.add(mapRepository.getPHDataFromNetwork()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(data ->
                        mapRepository.insertOrUpdate(data), Timber::e)
        );
    }

    public LiveData<List<GlobalData>> getGlobalLiveData(){
        if(listLiveData == null){
            listLiveData = LiveDataReactiveStreams.fromPublisher(mapRepository
                    .getGlobalDataFromDatabase()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()));
        }
        return listLiveData;
    }

    public void getGlobalDataFromNetwork(){
        compositeDisposable.add(mapRepository.getGlobalDataFromNetwork()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(globalData ->
                        mapRepository.insertOrUpdate(globalData), Timber::e));
    }
    
    @Override
    protected void onCleared(){
        super.onCleared();
        compositeDisposable.dispose();
    }
}
