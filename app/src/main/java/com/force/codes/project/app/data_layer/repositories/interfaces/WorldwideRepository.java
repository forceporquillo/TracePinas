/*
 * Created by Force Porquillo on 7/1/20 7:09 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/30/20 3:12 AM
 */

package com.force.codes.project.app.data_layer.repositories.interfaces;

/*
 * Created by Force Porquillo on 6/4/20 8:01 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 7:53 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.force.codes.project.app.data_layer.model.CountryDetails;

import java.util.List;

import io.reactivex.Flowable;

public interface WorldwideRepository{
    Flowable <List <CountryDetails>> getDataFromRemoteService();

    LiveData <PagedList <CountryDetails>> getDataFromDatabase(PagedList.Config config);

    void saveDatabase(List <CountryDetails> details);
}






























