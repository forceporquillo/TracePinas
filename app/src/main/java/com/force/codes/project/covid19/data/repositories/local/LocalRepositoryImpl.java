package com.force.codes.project.covid19.data.repositories.local;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import com.force.codes.project.covid19.data.resources.database.CountryDao;
import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;
import com.force.codes.project.covid19.service.executors.AppExecutors;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class LocalRepositoryImpl implements LocalRepository{
    private CountryDao countryDao;
    private AppExecutors appExecutors;

    public LocalRepositoryImpl(CountryDao countryDao, AppExecutors appExecutors){
        this.countryDao = countryDao;
        this.appExecutors = appExecutors;
    }

    @Override
    public Flowable<List<CountryDetails>> getDataFromDatabase(){
        return countryDao.getLocalData().observeOn(Schedulers.io());
    }

    @Override
    public void saveDatabase(List<CountryDetails> details){
        appExecutors.diskIO().execute(() -> countryDao.insert(details));
    }

    @Override
    public void insertFavorites(CountryDetails countryDetails){
        appExecutors.diskIO().execute(() -> countryDao.insertFavorites(countryDetails));
    }
}
