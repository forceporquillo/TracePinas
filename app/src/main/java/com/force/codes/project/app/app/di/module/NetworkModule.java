/*
 * Created by Force Porquillo on 7/2/20 12:28 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 12:28 AM
 */

package com.force.codes.project.app.app.di.module;

import com.force.codes.project.app.data_layer.resources.api.ApiService;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.force.codes.project.app.app.constants.ApiConstants.TWITTER_BEARER_TOKEN;
import static com.force.codes.project.app.app.constants.ApiConstants.URL_PATH;
import static com.force.codes.project.app.app.constants.GlobalConstants.TIMEOUT_MILLIS;

@Module
public class NetworkModule {
  @Singleton
  static OkHttpClient providesOkHttpClient = new OkHttpClient.Builder()
      .connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
      .readTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
      .writeTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
      .addInterceptor(providesLoggingInterceptor())
      .addInterceptor(chain -> {
        final Request makeRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer "
                    .concat(TWITTER_BEARER_TOKEN)
            )
            .build();
        return chain.proceed(makeRequest);
      }).build();

  @Provides
  static HttpLoggingInterceptor providesLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
    return interceptor;
  }

  @Provides
  static Retrofit providesRetrofitInstance() {
    return new Retrofit.Builder()
        .baseUrl(URL_PATH)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(providesOkHttpClient)
        .build();
  }

  @Provides
  static ApiService providesRemoteApi(final Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }
}
