/*
 * Created by Force Porquillo on 7/9/20 5:37 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:37 AM
 */
package com.force.codes.project.app.data_layer.repositories.interfaces

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagedList.Config
import com.force.codes.project.app.data_layer.model.news.ArticlesItem
import com.force.codes.project.app.data_layer.model.news.NewsData
import com.force.codes.project.app.data_layer.model.twitter.TwitterData
import io.reactivex.Flowable

interface NewsRepository {
  val newsResponseFromServer: Flowable<NewsData?>
  fun getPagedListArticle(config: Config?): LiveData<PagedList<ArticlesItem?>?>?
  fun insertArticleData(items: List<ArticlesItem?>?)
  fun getTwitterUser(userTimeline: String?): Flowable<List<TwitterData?>?>
  fun getPagedListTwitter(config: Config?): LiveData<PagedList<TwitterData?>?>?
  fun insertTwitterUser(twitterUser: List<TwitterData?>?)
}