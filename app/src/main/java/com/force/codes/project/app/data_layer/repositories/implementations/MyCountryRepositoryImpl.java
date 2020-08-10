/*
 * Created by Force Porquillo on 8/7/20 7:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/7/20 7:49 PM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.data_layer.model.CountryDayOne;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.MyCountryRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import com.force.codes.project.app.data_layer.resources.database.MyCountryDao;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class MyCountryRepositoryImpl implements MyCountryRepository {
  private final ApiService apiService;
  private final MyCountryDao dao;

  @Inject MyCountryRepositoryImpl(ApiService apiService, MyCountryDao dao) {
    this.apiService = apiService;
    this.dao = dao;
  }

  @Override public Flowable<List<CountryDayOne>> getCountryDataFromDayOne(String country) {
    return apiService.getCountryFromDayOne(country)
        .subscribeOn(Schedulers.computation());
  }

  @Override public Single<CountryDetails> getCountryDetails(String country) {
    return apiService.getCountryDetails(ApiConstants
        .getBaseUrlPath("countries/".concat(country)))
        .subscribeOn(Schedulers.computation());
  }

  @Override public Flowable<String> getCountry() {
    return dao.getPrimarySelected();
  }
}
