/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

/*
 * Created by Force Porquillo on 6/4/20 6:06 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:59 AM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.force.codes.project.app.data_layer.model.world.GlobalData;
import com.force.codes.project.app.data_layer.model.map_data.LocalData;
import com.force.codes.project.app.data_layer.repositories.interfaces.MapRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MapViewModel extends BaseViewModel{
    private MapRepository mapRepository;
    private MutableLiveData <LocalData> mutablePhData = new MutableLiveData <>();
    private MutableLiveData <List <GlobalData>> mutableGlobalData = new MutableLiveData <>();

    @Inject
    public MapViewModel(MapRepository mapRepository){
        this.mapRepository = mapRepository;
    }

    public void getAllPhData(){
        Disposable disposable = mapRepository.getAllPHData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(phData ->
                        mutablePhData.setValue(phData), Timber::e);

        addToUnsubscribed(disposable);
    }

    public LiveData <LocalData> getMutablePhData(){
        return mutablePhData;
    }

    public void getListGlobalData(){
        Disposable disposable = mapRepository.getAllGlobalData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(globalData ->
                        mutableGlobalData.setValue(globalData), Timber::e);
        addToUnsubscribed(disposable);
    }

    public LiveData <List <GlobalData>> getMutableGlobalData(){
        return mutableGlobalData;
    }
}
