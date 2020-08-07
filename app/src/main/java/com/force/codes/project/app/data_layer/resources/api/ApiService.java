package com.force.codes.project.app.data_layer.resources.api;

/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/12/20 3:22 PM
 *
 */

import com.force.codes.project.app.data_layer.model.CountryDayOne;
import com.force.codes.project.app.data_layer.model.overall.TotalByDate;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.model.map_data.LocalData;
import com.force.codes.project.app.data_layer.model.map_data.WorldData;
import com.force.codes.project.app.data_layer.model.news.NewsData;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.data_layer.model.world.GlobalData;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
  @GET
  Flowable<List<CountryDetails>>
  getSortedCases(@Url String url);

  @GET
  Flowable<List<GlobalData>>
  getGlobalData(@Url String url);

  @GET
  Flowable<LocalData>
  getPhData(@Url String url);

  @GET
  Flowable<WorldData>
  getWorldData(@Url String url);

  @GET
  Flowable<NewsData>
  getNewsResponse(@Url String url);

  @GET
  Flowable<List<TwitterData>>
  getTwitterResponse(@Url String url);

  @GET
  Flowable<List<TotalByDate>>
  getTotalByDate(@Url String url);

  @GET
  Flowable<List<CountryDayOne>>
  getCountryFromDayOne(@Url String url);

  @GET Single<CountryDetails>
  getCountryDetails(@Url String url);
}
