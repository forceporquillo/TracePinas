/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/12/20 2:22 AM
 */
package com.force.codes.project.app.app.constants

import com.force.codes.project.app.BaseApplication
import com.force.codes.project.app.R

object ApiConstants{
  const val NEWS_DATA = "https://newsapi.org/v2/everything?q=COVID, Philippines&" +
      "from=2020&sortBy=publishedAt&apiKey=0bf6fe738d0f4279a5c1169f11fe4c3f&pageSize=100&page=1"
  const val TWITTER_BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAA5SDwEAAAAAnF1q%2BmhNSAysxKDenHX4De2H7" +
      "x0%3DyUJnEvO0oet43pc2mWNGTlNFyF6kXOeprKwF2wx5RRpixV3WBu"
  private const val TWITTER_USER_ENDPOINT = "https://api.twitter.com/1.1/statuses/" +
      "user_timeline.json?screen_name="

  const val TOTAL_BY_DATE = "https://covid-api.com/api/reports/total?date="
  const val URL_PATH = "https://corona.lmao.ninja/v2/"
  const val LOCAL_URL = "https://coronavirus-ph-api.herokuapp.com/doh-data-drop"
  const val GLOBAL_CASE = "https://covid19.mathdro.id/api/confirmed"
  private const val TWEET_MODE_EXTEND = "&count=25&tweet_mode=extended"
  private const val API_COVID19 = "https://api.covid19api.com/total/dayone/country/"
  private const val API_KEY = ""
  @JvmStatic val twitterUrl = arrayOf(
      "DOHGOVPH",
      "rapplerdotcom",
      "ABSCBNNews"
  )

  // custom endpoint
  @JvmStatic fun getTwitterEndpoint(screenName: String): String {
    return TWITTER_USER_ENDPOINT + screenName
  }

  @JvmStatic fun getUserTimeline(timelineIndex: Int): String {
    return twitterUrl[timelineIndex].plus(TWEET_MODE_EXTEND)
  }

  @JvmStatic fun getBaseUrlPath(endpoint: String): String {
    return URL_PATH.plus(endpoint)
  }

  @JvmStatic fun getCountryDataFromDayOne(country: String?): String {
    return API_COVID19.plus(country)
  }
}