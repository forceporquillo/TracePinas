/*
 * Created by Force Porquillo on 7/9/20 5:36 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:36 AM
 */
package com.force.codes.project.app.data_layer.repositories.implementations

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedList.Config
import com.force.codes.project.app.app.constants.ApiConstants
import com.force.codes.project.app.app.constants.ApiConstants.getTwitterEndpoint
import com.force.codes.project.app.data_layer.model.news.ArticlesItem
import com.force.codes.project.app.data_layer.model.news.NewsData
import com.force.codes.project.app.data_layer.model.twitter.TwitterData
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository
import com.force.codes.project.app.data_layer.resources.api.ApiService
import com.force.codes.project.app.data_layer.resources.database.NewsDao
import com.force.codes.project.app.presentation_layer.controller.utils.AppExecutors
import io.reactivex.Flowable
import javax.inject.Inject

class NewsRepositoryImpl
@Inject internal constructor(
  private val newsDao: NewsDao,
  private val apiService: ApiService,
  private val executors: AppExecutors
) : NewsRepository {

  override fun getTwitterUser(
    userTimeline: String?
  ): Flowable<List<TwitterData?>?>? {
    return apiService.getTwitterResponse(
        userTimeline?.let {
          getTwitterEndpoint(it)
        }
    )
  }

  override val newsResponseFromServer:
      Flowable<NewsData?>?
    get() {
      return apiService.getNewsResponse(
          ApiConstants.NEWS_DATA
      )
    }

  override fun getPagedListTwitter(
    config: Config?
  ): LiveData<PagedList<TwitterData?>?>? {
    val dataFactory =
      newsDao.recentTweetsFromDatabase
    return config?.let {
      LivePagedListBuilder<Int?, TwitterData>(
          dataFactory!!, it
      )
          .build()
    }
  }

  override fun insertTwitterUser(
    twitterUser: List<TwitterData?>?
  ) {
    executors.diskIO()
        .execute {
          newsDao.insertOrUpdateTwitterUsers(
              twitterUser
          )
        }
  }

  override fun getPagedListArticle(
    config: Config?
  ): LiveData<PagedList<ArticlesItem?>?>? {
    val dataFactory =
      newsDao.newsDataFromDatabase
    return config?.let {
      LivePagedListBuilder<Int?, ArticlesItem>(
          dataFactory!!, it
      )
          .build()
    }
  }

  override fun insertArticleData(
    items: List<ArticlesItem?>?
  ) {
    executors.diskIO()
        .execute {
          newsDao.insertOrUpdateArticleItems(
              items
          )
        }
  }

}
