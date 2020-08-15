package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.data_layer.model.PrimarySelected;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.ListViewRepository;
import com.force.codes.project.app.data_layer.resources.database.ListViewDao;
import com.force.codes.project.app.presentation_layer.controller.utils.AppExecutors;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class ListViewRepositoryImpl implements ListViewRepository {
  private final ListViewDao listViewDao;
  private final AppExecutors executors;

  @Inject
  public ListViewRepositoryImpl(
      ListViewDao listViewDao,
      AppExecutors executors
  ) {
    this.listViewDao = listViewDao;
    this.executors = executors;
  }

  @Override public Flowable<List<CountryDetails>> getCountryDetails(boolean order) {
    return listViewDao.getCountryDetails(order);
  }

  /**
   * Run database insertion in the background thread.
   * @param country item to be inserted from DB.
   */
  @Override public void insertSelected(String country) {
    executors.diskIO().execute(() -> {
      listViewDao.insertSelected(
          new PrimarySelected(1, country)
      );
      Timber.e(country);
    });
  }
}
