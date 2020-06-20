package com.force.codes.project.app.data_layer.repositories.worldwide;

/*
 * Created by Force Porquillo on 6/4/20 8:01 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 7:53 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.force.codes.project.app.data_layer.model.CountryDetails;
import com.force.codes.project.app.data_layer.model.Response;

import java.util.List;

import io.reactivex.Flowable;

public interface WorldwideRepository{
    Flowable<List<CountryDetails>> getDataFromRemoteService();

    LiveData<PagedList<CountryDetails>> getDataFromDatabase(PagedList.Config config);

    void saveDatabase(List<CountryDetails> details);

    void updateFavorites(CountryDetails details);

    Flowable<Response> getResponseFromNetwork();

    void insertResponse(Response response);
}






























