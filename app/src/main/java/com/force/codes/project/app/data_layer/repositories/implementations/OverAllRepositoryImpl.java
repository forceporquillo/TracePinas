/*
 * Created by Force Porquillo on 7/26/20 6:26 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/26/20 6:26 PM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.data_layer.model.overall.TotalByDate;
import com.force.codes.project.app.data_layer.repositories.interfaces.OverAllRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;

public class OverAllRepositoryImpl implements OverAllRepository {

  private final ApiService apiService;

  @Inject OverAllRepositoryImpl(final ApiService apiService) {
    this.apiService = apiService;
  }

  @Override
  public Flowable<List<TotalByDate>> getTotalByDateFromNetwork(String url) {
    return apiService.getTotalByDate(url);
  }
}
