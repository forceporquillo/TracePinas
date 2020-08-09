/*
 * Created by Force Porquillo on 8/7/20 7:50 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/7/20 7:50 PM
 */

package com.force.codes.project.app.data_layer.repositories.interfaces;

import com.force.codes.project.app.data_layer.model.CountryDayOne;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;

public interface MyCountryRepository {
  Flowable<List<CountryDayOne>> getCountryDataFromDayOne(String country);
  Flowable<List<CountryDetails>> getAffectedCountryList();
  Single<CountryDetails> getCountryDetails(String country);
  Flowable<String> getCountry();
}
