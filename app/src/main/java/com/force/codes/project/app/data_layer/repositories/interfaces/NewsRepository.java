/*
 * Created by Force Porquillo on 7/9/20 5:37 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:37 AM
 */

package com.force.codes.project.app.data_layer.repositories.interfaces;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.news.NewsData;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import io.reactivex.Flowable;
import java.util.List;

public interface NewsRepository {
  Flowable<NewsData> getNewsResponseFromServer();

  LiveData<PagedList<ArticlesItem>> getPagedListArticle(PagedList.Config config);

  void insertArticleData(List<ArticlesItem> items);

  Flowable<List<TwitterData>> getTwitterUser(String userTimeline);

  LiveData<PagedList<TwitterData>> getPagedListTwitter(PagedList.Config config);

  void insertTwitterData(List<TwitterData> twitterResponse);
}
