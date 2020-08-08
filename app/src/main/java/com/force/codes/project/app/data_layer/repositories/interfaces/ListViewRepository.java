package com.force.codes.project.app.data_layer.repositories.interfaces;

import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import io.reactivex.Flowable;
import java.util.List;

public interface ListViewRepository {
  Flowable<List<CountryDetails>> getCountryDetails();
}
