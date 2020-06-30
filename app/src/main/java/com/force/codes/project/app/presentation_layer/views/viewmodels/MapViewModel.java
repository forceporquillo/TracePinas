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
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MapViewModel extends BaseViewModel{
    private MapRepository mapRepository;
    private MutableLiveData <PHData> mutablePhData = new MutableLiveData <>();
    private MutableLiveData <List <GlobalData>> mutableGlobalData = new MutableLiveData <>();

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

    public LiveData <PHData> getMutablePhData(){
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
