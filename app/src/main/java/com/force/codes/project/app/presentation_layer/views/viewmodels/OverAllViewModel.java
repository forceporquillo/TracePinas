/*
 * Created by Force Porquillo on 7/26/20 6:29 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/26/20 6:29 PM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.app.constants.GlobalConstants;
import com.force.codes.project.app.data_layer.model.overall.TotalByDate;
import com.force.codes.project.app.data_layer.repositories.interfaces.OverAllRepository;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import javax.inject.Inject;
import timber.log.Timber;

public class OverAllViewModel extends BaseViewModel {
  private final OverAllRepository repository;
  private final MutableLiveData<List<TotalByDate>> mutableLiveData;
  private final List<String> endpointList;
  private final List<Flowable<List<TotalByDate>>> totalByDateList;

  @Inject OverAllViewModel(final OverAllRepository repository) {
    this.repository = repository;
    this.mutableLiveData = new MutableLiveData<>();
    this.endpointList = new ArrayList<>();
    this.totalByDateList = new ArrayList<>();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void streamIterate() {
    final SimpleDateFormat formatter = Utils.formatDate(GlobalConstants.TIME_FORMAT);
    if (Utils.requiresSdkInt(android.os.Build.VERSION_CODES.O)) {
      final LocalDate start = LocalDate.of(2020, 1, 22);
      final LocalDate end = LocalDate.parse(formatter.format(Utils.getTodayDate()));
      Stream.iterate(start, d -> d.plusDays(1))
          .limit(ChronoUnit.DAYS.between(start, end))
          .forEach(x -> endpointList.add(ApiConstants.TOTAL_BY_DATE
              .concat(x.toString()))
          );
    }
  }

  final List<Flowable<List<TotalByDate>>> iterateTotalByDateEndpoint() {
    for (int i = 0; i < endpointList.size(); ++i) {
      totalByDateList.add(i, repository.getTotalByDateFromNetwork(
          endpointList.get(i)).observeOn(Schedulers.computation())
          .delay(500, TimeUnit.MILLISECONDS)
      );
    }
    return totalByDateList;
  }

  public void getTotalByDate() {
    final Disposable disposables = Flowable
        .fromIterable(iterateTotalByDateEndpoint())
        .observeOn(Schedulers.computation())
        .flatMap(x -> x)
        .delay(200, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .subscribe(mutableLiveData::setValue, Timber::e);
    addToUnsubscribed(disposables);
  }
}
