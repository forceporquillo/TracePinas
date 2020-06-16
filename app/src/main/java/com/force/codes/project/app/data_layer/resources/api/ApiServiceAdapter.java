package com.force.codes.project.app.data_layer.resources.api;

/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/5/20 3:22 PM
 *
 */

import com.force.codes.project.app.model.CountryDetails;
import com.force.codes.project.app.model.WorldData;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiServiceAdapter{
    @GET("countries?sort=cases")
    Flowable<List<CountryDetails>> getSortedCases();

    @GET("all")
    Flowable<WorldData> getWorldData();
}
