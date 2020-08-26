/*
 * Created by Force Porquillo on 5/7/20 7:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 3:12 AM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.force.codes.project.app.app.constants.ApiConstantEndpoints;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.WorldwideRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import com.force.codes.project.app.data_layer.resources.database.WorldwideDao;
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WorldwideRepositoryImpl implements WorldwideRepository {
  private WorldwideDao worldwideDao;
  private ApiService serviceAdapter;
  private ThreadExecutor executors;

  @Inject
  public WorldwideRepositoryImpl(
      WorldwideDao worldwideDao,
      ApiService adapter,
      ThreadExecutor executors
  ) {
    this.worldwideDao = worldwideDao;
    this.serviceAdapter = adapter;
    this.executors = executors;
  }

  @Override
  public Flowable<List<CountryDetails>> getDataFromRemoteService() {
    return serviceAdapter.getSortedCases(ApiConstantEndpoints.getBaseUrlPath("countries?sort=cases"))
        .subscribeOn(Schedulers.computation());
  }

  @Override
  public LiveData<PagedList<CountryDetails>> getDataFromDatabase(PagedList.Config config) {
    DataSource.Factory<Integer, CountryDetails>
        detailsFactory = worldwideDao.getDataFromDatabase();
    return new LivePagedListBuilder<>(detailsFactory, config).build();
  }

  @Override
  public void saveDatabase(List<CountryDetails> detailsList) {
    executors.diskIO().execute(() -> worldwideDao.insertOrUpdate(detailsList));
  }
}