/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/12/20 2:22 AM
 */
package com.force.codes.project.app.app.constants

import com.force.codes.project.app.data_layer.model.twitter.TwitterData
import io.reactivex.Flowable

object ApiConstants {
  // region endpoint URLS
  const val TOTAL_BY_DATE = "https://covid-api.com/api/reports/total?date="
  const val URL_PATH = "https://corona.lmao.ninja/v2/"
  const val LOCAL_URL = "https://coronavirus-ph-api.herokuapp.com/doh-data-drop"
  const val GLOBAL_CASE = "https://covid19.mathdro.id/api/confirmed"
  const val NEWS_DATA =
    "https://newsapi.org/v2/everything?q=COVID, Philippines&from=2020&sortBy=publishedAt&apiKey=0bf6fe738d0f4279a5c1169f11fe4c3f&pageSize=100&page=1"
  const val TWITTER_BEARER_TOKEN =
    "AAAAAAAAAAAAAAAAAAAAAA5SDwEAAAAAnF1q%2BmhNSAysxKDenHX4De2H7x0%3DyUJnEvO0oet43pc2mWNGTlNFyF6kXOeprKwF2wx5RRpixV3WBu"
  private const val TWITTER_USER_ENDPOINT =
    "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="
  private const val TWEET_MODE_EXTEND = "&count=25&tweet_mode=extended"
  private const val CORONA_LMAO_NINJA_V_2 = "https://corona.lmao.ninja/v2/"
  @JvmStatic val url = arrayOf(
      "DOHGOVPH",
      "rapplerdotcom",
      "ABSCBNNews"
  )

  // custom endpoint
  @JvmStatic fun getTwitterEndpoint(screenName: String): String {
    return TWITTER_USER_ENDPOINT + screenName
  }

  @JvmStatic fun getUserTimeline(timelineIndex: Int): String {
    return url[timelineIndex] + TWEET_MODE_EXTEND
  }

  @JvmStatic fun getBaseUrlPath(endpoint: String): String {
    return CORONA_LMAO_NINJA_V_2 + endpoint
  }
}