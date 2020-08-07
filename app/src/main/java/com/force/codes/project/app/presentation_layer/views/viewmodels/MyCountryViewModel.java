/*
 * Created by Force Porquillo on 8/1/20 6:16 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/1/20 6:16 PM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.MyCountryRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import timber.log.Timber;

public class MyCountryViewModel extends BaseViewModel {
  private final MyCountryRepository repository;
  private MutableLiveData<CountryDetails> mutableLiveData;

  @Inject
  public MyCountryViewModel(MyCountryRepository repository){
   this.repository = repository;
    mutableLiveData = new MutableLiveData<>();
  }

  public LiveData<CountryDetails> getCountryData(String country){
    return LiveDataReactiveStreams.fromPublisher(
        repository.getCountryDetails(country)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(countryDetails -> mutableLiveData.setValue(countryDetails))

    );
  }
}
