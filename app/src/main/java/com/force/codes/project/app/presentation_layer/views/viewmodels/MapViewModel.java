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
import com.force.codes.project.app.data_layer.model.map_data.LocalData;
import com.force.codes.project.app.data_layer.model.world.GlobalData;
import com.force.codes.project.app.data_layer.repositories.interfaces.MapRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public final class MapViewModel extends BaseViewModel {
  private final MapRepository mapRepository;
  private final MutableLiveData<LocalData> mutablePhData;
  private final MutableLiveData<List<GlobalData>> mutableGlobalData;
  private final List<GlobalData> tempData = new ArrayList<>();

  @Inject
  public MapViewModel(MapRepository mapRepository) {
    this.mapRepository = mapRepository;
    this.mutablePhData = new MutableLiveData<>();
    this.mutableGlobalData = new MutableLiveData<>();
  }

  public void getAllPhData() {
    final Disposable disposable = mapRepository.getAllPHData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(mutablePhData::setValue, Timber::e);
    addToUnsubscribed(disposable);
  }

  public LiveData<LocalData> getMutablePhData() {
    return mutablePhData;
  }

  public void getListGlobalData() {
    super.addToUnsubscribed(mapRepository.getAllGlobalData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(globalData -> {
          for (GlobalData data : globalData) {
            if (checkIfEmpty(data)) {
              tempData.add(data);
              mutableGlobalData.setValue(tempData);
            }
          }
        }, Timber::e));
  }

  private static boolean checkIfEmpty(GlobalData data) {
    return data.getLatitude() == 0.0 && data.getLongitude() == 0.0;
  }

  public LiveData<List<GlobalData>> getMutableGlobalData() {
    return mutableGlobalData;
  }
}
