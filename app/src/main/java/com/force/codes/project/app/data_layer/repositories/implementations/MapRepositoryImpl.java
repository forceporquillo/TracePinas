/*
 * Created by Force Porquillo on 6/20/20 8:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 7:47 PM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.data_layer.model.map_data.LocalData;
import com.force.codes.project.app.data_layer.model.world.GlobalData;
import com.force.codes.project.app.data_layer.repositories.interfaces.MapRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import com.force.codes.project.app.data_layer.resources.database.MapDao;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MapRepositoryImpl implements MapRepository {
  private final MapDao mapDao;
  private final ApiService serviceAdapter;

  @Inject
  public MapRepositoryImpl(MapDao mapDao, ApiService serviceAdapter) {
    this.mapDao = mapDao;
    this.serviceAdapter = serviceAdapter;
  }

  @Override
  public Flowable<LocalData> getAllPHData() {
    return Flowable.mergeDelayError(serviceAdapter
        .getPhData(ApiConstants.LOCAL_URL)
        .doOnNext(mapDao::savePhData)
        .subscribeOn(Schedulers.io()), mapDao.getPHDataFromDB()
        .subscribeOn(Schedulers.io()));
  }

  @Override
  public Flowable<List<GlobalData>> getAllGlobalData() {
    return Flowable.mergeDelayError(serviceAdapter
        .getGlobalData(ApiConstants.GLOBAL_CASE)
        .doOnNext(mapDao::saveGlobalData)
        .subscribeOn(Schedulers.io()), mapDao.getGlobalDataFromDB()
        .subscribeOn(Schedulers.io()));
  }
}
