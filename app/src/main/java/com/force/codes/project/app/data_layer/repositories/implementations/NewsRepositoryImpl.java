/*
 * Created by Force Porquillo on 7/9/20 5:36 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:36 AM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;

import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.data_layer.model.NewsData;
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import com.force.codes.project.app.data_layer.resources.database.NewsDao;
import com.force.codes.project.app.service.executors.AppExecutors;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class NewsRepositoryImpl implements NewsRepository{
    private NewsDao newsDao;
    private ApiService apiService;
    private AppExecutors executors;

    @Inject
    NewsRepositoryImpl(NewsDao newsDao, ApiService apiService, AppExecutors executors){
        this.newsDao = newsDao;
        this.apiService = apiService;
        this.executors = executors;
    }

    @Override
    public Flowable<NewsData> getNewsResponse(){
        return apiService.getNewsResponse(ApiConstants.NEWS_DATA);
    }
}
