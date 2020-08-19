/*
 * Created by Force Porquillo on 6/4/20 6:06 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/20/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.app.constants.PageListConstants;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository;
import com.force.codes.project.app.presentation_layer.controller.utils.AppExecutors;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public final class NewsViewModel extends BaseViewModel {
  private final NewsRepository newsRepository;
  private final AppExecutors executors;
  private final ObservableBoolean onError = new ObservableBoolean();
  private LiveData<PagedList<ArticlesItem>> articleLiveData;
  private LiveData<PagedList<TwitterData>> twitterLiveData;

  @Inject
  public NewsViewModel(final NewsRepository newsRepository,
      final AppExecutors executors
  ) {
    this.newsRepository = newsRepository;
    this.executors = executors;
  }

  private static final PagedList.Config config = new PagedList.Config.Builder()
      .setPageSize(PageListConstants.PAGE_SIZE)
      .setPrefetchDistance(PageListConstants.FETCH_DISTANCE)
      .setInitialLoadSizeHint(PageListConstants.PAGE_INITIAL_LOAD_SIZE_HINT)
      .setEnablePlaceholders(PageListConstants.isEnable)
      .build();

  @NotNull final List<Flowable<List<TwitterData>>> getListOfTwitterUsers() {
    final List<Flowable<List<TwitterData>>> listOfTwitterUsers = new ArrayList<>();
    for (int i = 0; i < ApiConstants.getTwitterUrl().length; ++i) {
      listOfTwitterUsers.add(i, newsRepository
          .getTwitterUser(ApiConstants.getUserTimeline(i))
          .subscribeOn(Schedulers.computation()));
    }
    return listOfTwitterUsers;
  }

  /**
   * This method is run and observe from custom background thread to avoid
   * queue blocking. Since, we emmit two separate observable list from
   * network and we want both observable to asynchronously run in parallel.
   */
  public void getTwitterUserTimeline() {
    super.addToUnsubscribed(Flowable.fromIterable(
        getListOfTwitterUsers())
        .flatMap(x -> x)
        .subscribeOn(Schedulers.from(executors.computationIO()))
        .subscribe(this::insertTwitterDataToDB, t -> {
          if (t == null) {
            throw new AssertionError(
                "This throwable must not be null");
          }
          onError.set(true);
          Timber.e(t);
        }));
  }

  final void insertTwitterDataToDB(final List<TwitterData> twitterUser) {
    newsRepository.insertTwitterUser(twitterUser);
  }

  public LiveData<PagedList<TwitterData>> getTwitterLiveData() {
    if (twitterLiveData == null) {
      twitterLiveData = newsRepository.getPagedListTwitter(config);
    }
    return twitterLiveData;
  }

  /**
   * run on RxThreadScheduler
   */
  public void getNewsData() {
    super.addToUnsubscribed(Flowable.fromPublisher(newsRepository
        .getNewsResponseFromServer())
        .doOnError(e -> onError.set(true))
        .subscribeOn(Schedulers.io())
        .subscribe(newsData -> insertArticleToDB(
            newsData.getArticles()
        ), Timber::e));
  }

  final void insertArticleToDB(final List<ArticlesItem> articlesItems) {
    newsRepository.insertArticleData(articlesItems);
  }

  public LiveData<PagedList<ArticlesItem>> getNewsLiveData() {
    if (articleLiveData == null) {
      return articleLiveData = newsRepository.getPagedListArticle(config);
    }
    return articleLiveData;
  }

  public void forceUpdate() {
    getTwitterUserTimeline();
    getNewsData();
  }

  public ObservableBoolean getOnError() {
    return onError;
  }
}