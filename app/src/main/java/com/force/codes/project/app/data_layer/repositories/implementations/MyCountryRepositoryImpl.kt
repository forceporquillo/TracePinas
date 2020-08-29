/*
 * Created by Force Porquillo on 8/7/20 7:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/7/20 7:49 PM
 */
package com.force.codes.project.app.data_layer.repositories.implementations

import com.force.codes.project.app.app.constants.ApiConstantEndpoints
import com.force.codes.project.app.app.constants.ApiConstantEndpoints.getBaseUrlPath
import com.force.codes.project.app.data_layer.model.CountryDayOne
import com.force.codes.project.app.data_layer.model.country.CountryDetails
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.DOHDataDrop
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.TopRegions
import com.force.codes.project.app.data_layer.repositories.interfaces.MyCountryRepository
import com.force.codes.project.app.data_layer.resources.api.ApiService
import com.force.codes.project.app.data_layer.resources.database.MyCountryDao
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyCountryRepositoryImpl
@Inject internal constructor(
  private val apiService: ApiService,
  private val dao: MyCountryDao
) : MyCountryRepository {

  override fun getCountryDataFromDayOne(
    country: String
  ): Flowable<List<CountryDayOne>> {
    return apiService.getCountryFromDayOne(country)
        .subscribeOn(Schedulers.computation())
  }

  override fun getCountryDetails(
    country: String
  ): Single<CountryDetails> {
    return apiService.getCountryDetails(
        getBaseUrlPath("countries/$country")
    )
        .subscribeOn(Schedulers.computation())
  }

  override val country: Flowable<String>
    get() = dao.primarySelected

  override val phData: Flowable<DOHDataDrop>
    get() {
      return apiService.getPhDataSet(
          ApiConstantEndpoints.PH_FROM_DAY_ONE
              .plus("timeline")
      )
    }

  override val topRegions: Flowable<TopRegions>
    get() {
      return apiService.getPhTopAffectedRegions(
          ApiConstantEndpoints.PH_FROM_DAY_ONE
              .plus("top-regions")
      )
    }
}