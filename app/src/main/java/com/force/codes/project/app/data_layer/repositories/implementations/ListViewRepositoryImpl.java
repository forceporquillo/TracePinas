package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.ListViewRepository;
import com.force.codes.project.app.data_layer.resources.database.ListViewDao;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;

public class ListViewRepositoryImpl implements ListViewRepository {
  private final ListViewDao listViewDao;

  @Inject
  public ListViewRepositoryImpl(ListViewDao listViewDao) {
    this.listViewDao = listViewDao;
  }

  @Override public Flowable<List<CountryDetails>> getCountryDetails() {
    return listViewDao.getCountryDetails();
  }
}
