/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

/*
 * Created by Force Porquillo on 6/3/20 10:47 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/3/20 10:47 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.force.codes.project.app.data_layer.model.WorldData;
import com.force.codes.project.app.data_layer.repositories.live.LiveOverviewRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LiveDataViewModel extends BaseViewModel{
    public MutableLiveData <WorldData> liveData = new MutableLiveData <>();

    private LiveOverviewRepository repository;

    public LiveDataViewModel(LiveOverviewRepository repository){
        this.repository = repository;
    }

    public LiveData <WorldData> getDataFromNetwork(){
        return LiveDataReactiveStreams.fromPublisher(
                repository.getWorldDataFromNetwork()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(list -> liveData.setValue(list))
                        .doOnError(Timber::e));

    }
}
