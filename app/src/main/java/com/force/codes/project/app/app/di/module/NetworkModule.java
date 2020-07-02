/*
 * Created by Force Porquillo on 7/2/20 12:28 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 12:28 AM
 */

package com.force.codes.project.app.app.di.module;

import com.force.codes.project.app.app.constants.ApiConstants;
import com.force.codes.project.app.data_layer.resources.api.RemoteApiAdapter;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule{
    private static final int TIMEOUT_MILLIS = 1000;

    @Singleton
    static OkHttpClient providesOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .writeTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .addInterceptor(providesInterceptor())
            .build();

    @Provides
    static HttpLoggingInterceptor providesInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    @Provides
    static Retrofit providesRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(providesOkHttpClient)
                .build();
    }

    @Provides
    static RemoteApiAdapter providesRemoteApi(Retrofit retrofit){
        return retrofit.create(RemoteApiAdapter.class);
    }
}
