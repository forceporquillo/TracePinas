package com.force.codes.project.covid19.data.repositories.mainrepository;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSourceRepository{
    Flowable<List<CountryDetails>> getDataFromRemoteService();

    Flowable<List<CountryDetails>> getDataResults();

    void insertCountry(CountryDetails details);
}
