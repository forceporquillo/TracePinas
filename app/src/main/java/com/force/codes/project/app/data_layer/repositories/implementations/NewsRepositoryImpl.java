/*
 * Created by Force Porquillo on 7/9/20 5:36 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:36 AM
 */

package com.force.codes.project.app.data_layer.repositories.implementations;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.news.NewsData;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository;
import com.force.codes.project.app.data_layer.resources.api.ApiService;
import com.force.codes.project.app.data_layer.resources.database.NewsDao;
import com.force.codes.project.app.service.executors.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class NewsRepositoryImpl implements NewsRepository{
    private NewsDao newsDao;
    private ApiService apiService;
    private AppExecutors executors;

    @Inject
    NewsRepositoryImpl(
            NewsDao newsDao,
            ApiService apiService,
            AppExecutors executors
    ){
        this.newsDao = newsDao;
        this.apiService = apiService;
        this.executors = executors;
    }

    @Override
    public Flowable<NewsData> getNewsResponseFromServer(){
        return apiService.getNewsResponse(ApiConstants.NEWS_DATA);
    }

    @Override
    public LiveData<PagedList<ArticlesItem>>
    getPagedListArticle(PagedList.Config config){
        DataSource.Factory<Integer, ArticlesItem>
                dataFactory = newsDao.getNewsDataFromDatabase();
        return new LivePagedListBuilder<>(dataFactory, config)
                .build();
    }

    @Override
    public void insertArticleData(List<ArticlesItem> items){
        executors.diskIO().execute(() ->
                newsDao.insertOrUpdateArticleItems(items));
    }

    @Override
    public Flowable<List<TwitterData>> getTwitterUser(String screenName){
        return apiService.getTwitterResponse(ApiConstants.getTwitterEndpoint(screenName));
    }

    @Override
    public LiveData<PagedList<TwitterData>>
    getPagedListTwitter(PagedList.Config config){
        DataSource.Factory<Integer, TwitterData>
                dataFactory = newsDao.getRecentTweetsFromDatabase();
        return new LivePagedListBuilder<>(dataFactory, config)
                .build();
    }

    @Override
    public void insertTwitterData(List<TwitterData> twitterResponse){
        executors.diskIO().execute(() ->
                newsDao.insertOrUpdateTwitterItems(twitterResponse));
    }
}
