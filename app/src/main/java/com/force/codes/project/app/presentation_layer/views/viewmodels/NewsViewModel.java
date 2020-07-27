/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

/*
 * Created by Force Porquillo on 6/4/20 6:06 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 6:06 AM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository;
import com.force.codes.project.app.service.executors.AppExecutors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.force.codes.project.app.app.constants.ApiConstants.getUrl;
import static com.force.codes.project.app.app.constants.ApiConstants.getUserTimeline;

public class NewsViewModel extends BaseViewModel{
    private final NewsRepository newsRepository;
    private final AppExecutors executors;
    private LiveData<PagedList<ArticlesItem>> articleLiveData;
    private LiveData<PagedList<TwitterData>> twitterLiveData;

    @Inject
    public NewsViewModel(final NewsRepository newsRepository, final AppExecutors executors){
        this.newsRepository = newsRepository;
        this.executors = executors;
    }

    /**
     * iterates all available user timeline listed in {@link
     * com.force.codes.project.app.app.constants.ApiConstants constant array}.
     * @return list of flowable list observables {@link TwitterData}
     */
    final List<Flowable<List<TwitterData>>> flowableList(){
        final List<Flowable<List<TwitterData>>>
                listOfTwitterUsers = new ArrayList<>();
        for(int i = 0; i < getUrl().length; ++i)
            listOfTwitterUsers.add(i, newsRepository
                    .getTwitterUser(getUserTimeline(i))
                    .subscribeOn(Schedulers.from(executors.diskIO())));
        return listOfTwitterUsers;
    }

    public void getTwitterUserTimeline(){
        Disposable disposables = Flowable.fromIterable(flowableList())
                .flatMap(x -> x)
                .subscribeOn(Schedulers.from(executors.diskIO()))
                .observeOn(Schedulers.computation())
                .subscribe(this::insertTwitterToDB, Timber::e);
        super.addToUnsubscribed(disposables);
    }

    private void insertTwitterToDB(List<TwitterData> twitterDataList){
        newsRepository.insertTwitterData(twitterDataList);
    }

    public LiveData<PagedList<TwitterData>> pageListTwitterData(){
        if(twitterLiveData == null)
            twitterLiveData = newsRepository.getPagedListTwitter(config);
        return twitterLiveData;
    }

    static PagedList.Config config = new PagedList.Config.Builder()
            .setPageSize(10)
            .setPrefetchDistance(20)
            .setInitialLoadSizeHint(30)
            .setEnablePlaceholders(false)
            .build();

    public void getNewsData(){
        Disposable disposables = Flowable.fromPublisher(newsRepository
                .getNewsResponseFromServer())
                .subscribeOn(Schedulers.from(executors.diskIO()))
                .observeOn(Schedulers.computation())
                .subscribe(newsData -> insertArticleToDB(newsData.getArticles()), Timber::e);
        super.addToUnsubscribed(disposables);
    }

    private void insertArticleToDB(List<ArticlesItem> articlesItems){
        newsRepository.insertArticleData(articlesItems);
    }

    public LiveData<PagedList<ArticlesItem>> pagedListLiveData(){
        if(articleLiveData == null)
            return articleLiveData = newsRepository.getPagedListArticle(config);
        return articleLiveData;
    }

    public void forceUpdate(){
        getTwitterUserTimeline();
        getNewsData();
    }
}