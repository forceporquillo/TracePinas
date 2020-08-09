package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.data_layer.model.PrimarySelected;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.ListViewRepository;
import com.force.codes.project.app.data_layer.resources.database.ListViewDao;
import com.force.codes.project.app.presentation_layer.controller.utils.threads.AppExecutors;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class ListViewRepositoryImpl implements ListViewRepository {
  private final ListViewDao listViewDao;
  private final AppExecutors executors;

  @Inject
  public ListViewRepositoryImpl(ListViewDao listViewDao,
      AppExecutors executors) {
    this.listViewDao = listViewDao;
    this.executors = executors;
  }

  @Override public Flowable<List<CountryDetails>> getCountryDetails() {
    return listViewDao.getCountryDetails();
  }

  @Override public void insertSelected(String country) {
    PrimarySelected selected = new PrimarySelected();
    executors.diskIO().execute(() -> {
      selected.setCountryKey(country);
      listViewDao.insertCountry(selected);
      Timber.e(country);
    });
  }
}
