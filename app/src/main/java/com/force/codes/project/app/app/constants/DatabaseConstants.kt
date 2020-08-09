/*
 * Created by Force Porquillo on 7/2/20 2:47 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 11:28 PM
 */
package com.force.codes.project.app.app.constants

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */
object DatabaseConstants {
  const val QUERY_ALL_DATA = "SELECT * FROM CountryDetails"
  const val QUERY_ALL_PH_DATA = "SELECT * FROM LocalData"
  const val QUERY_ALL_GLOBAL_DATA = "SELECT * FROM GlobalData"
  const val QUERY_NEWS_DATA = "SELECT * FROM ArticlesItem LIMIT 100"
  const val DATABASE_VERSION = 34
  const val QUERY_TWITTER_DATA = "SELECT * FROM TwitterData"
}