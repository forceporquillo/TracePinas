/*
 * Created by Force Porquillo on 7/2/20 12:28 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 12:28 AM
 */
package com.force.codes.project.app.app.di.module

import com.force.codes.project.app.app.constants.ApiConstantEndpoints.TWITTER_BEARER_TOKEN
import com.force.codes.project.app.app.constants.ApiConstantEndpoints.URL_PATH
import com.force.codes.project.app.app.constants.GlobalConstants.TIMEOUT_MILLIS
import com.force.codes.project.app.data_layer.resources.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Singleton

@Module
object NetworkModule {
  @Singleton
  var providesOkHttpClient = Builder()
      .connectTimeout(TIMEOUT_MILLIS.toLong(), MILLISECONDS)
      .readTimeout(TIMEOUT_MILLIS.toLong(), MILLISECONDS)
      .writeTimeout(TIMEOUT_MILLIS.toLong(), MILLISECONDS)
      .addInterceptor(providesLoggingInterceptor())
      .addInterceptor { chain: Chain ->
        val makeRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer "
                + TWITTER_BEARER_TOKEN)
            .build()
        chain.proceed(makeRequest)
      }
      .build()

  @JvmStatic @Provides fun providesLoggingInterceptor(
  ): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HEADERS
    return interceptor
  }

  @JvmStatic @Provides fun providesRetrofitInstance(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(URL_PATH)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(providesOkHttpClient)
        .build()
  }

  @JvmStatic @Provides fun providesRemoteApi(
    retrofit: Retrofit,
  ): ApiService {
    return retrofit.create(ApiService::class.java)
  }
}

