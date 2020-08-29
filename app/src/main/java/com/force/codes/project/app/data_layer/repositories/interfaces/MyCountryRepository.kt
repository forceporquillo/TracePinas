/*
 * Created by Force Porquillo on 8/7/20 7:50 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/7/20 7:50 PM
 */
package com.force.codes.project.app.data_layer.repositories.interfaces

import com.force.codes.project.app.data_layer.model.CountryDayOne
import com.force.codes.project.app.data_layer.model.country.CountryDetails
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.DOHDataDrop
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.TopRegions
import io.reactivex.Flowable
import io.reactivex.Single

interface MyCountryRepository {
  fun getCountryDataFromDayOne(country: String): Flowable<List<CountryDayOne>>
  fun getCountryDetails(country: String): Single<CountryDetails>
  val country: Flowable<String>
  val phData: Flowable<DOHDataDrop>
  val topRegions: Flowable<TopRegions>
}