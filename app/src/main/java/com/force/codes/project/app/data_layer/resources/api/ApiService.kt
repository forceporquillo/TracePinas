package com.force.codes.project.app.data_layer.resources.api

import com.force.codes.project.app.data_layer.model.CountryDayOne
import com.force.codes.project.app.data_layer.model.country.CountryDetails
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.DOHDataDrop
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.TopRegions
import com.force.codes.project.app.data_layer.model.map_data.LocalData
import com.force.codes.project.app.data_layer.model.map_data.WorldData
import com.force.codes.project.app.data_layer.model.news.NewsData
import com.force.codes.project.app.data_layer.model.overall.TotalByDate
import com.force.codes.project.app.data_layer.model.twitter.TwitterData
import com.force.codes.project.app.data_layer.model.world.GlobalData
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

/*
* Created by Force Porquillo on 6/5/20 3:28 PM
* Copyright (c) 2020.  All rights reserved.
* Last modified 6/12/20 3:22 PM
*
*/

interface ApiService {
  @GET fun getSortedCases(
    @Url url: String?
  ): Flowable<List<CountryDetails?>?>

  @GET fun getGlobalData(
    @Url url: String?
  ): Flowable<List<GlobalData?>?>

  @GET fun getPhData(
    @Url url: String?
  ): Flowable<LocalData?>

  @GET fun getWorldData(
    @Url url: String?
  ): Flowable<WorldData?>

  @GET fun getNewsResponse(
    @Url url: String?
  ): Flowable<NewsData?>

  @GET fun getTwitterResponse(
    @Url url: String?
  ): Flowable<List<TwitterData?>?>

  @GET fun getTotalByDate(
    @Url url: String?
  ): Flowable<List<TotalByDate?>?>

  @GET fun getCountryFromDayOne(
    @Url url: String?
  ): Flowable<List<CountryDayOne?>?>

  @GET fun getCountryDetails(
    @Url url: String?
  ): Single<CountryDetails?>

  @GET fun getPhDataSet(
    @Url url: String?
  ): Flowable<DOHDataDrop?>

  @GET fun getPhTopAffectedRegions(
    @Url ur: String?
  ): Flowable<TopRegions?>
}