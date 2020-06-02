package com.force.codes.project.covid19.data.repositories.local;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;

import java.util.List;

import io.reactivex.Flowable;

public interface LocalRepository{
    Flowable<List<CountryDetails>> getDataFromDatabase();

    void saveDatabase(List<CountryDetails> details);

    void insertFavorites(CountryDetails countryDetails);
}
