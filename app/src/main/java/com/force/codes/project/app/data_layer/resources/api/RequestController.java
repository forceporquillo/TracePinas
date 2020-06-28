/*
 * Created by Force Porquillo on 6/5/20 4:55 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.data_layer.resources.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static final int TIMEOUT_MILLIS = 1000;

    private static RetrofitClient INSTANCE;
    private static Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiModule.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(providesOkHttpClient)
                .build();
    }

    final static OkHttpClient providesOkHttpClient =
            new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                    .readTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                    .writeTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                    .addInterceptor(providesInterceptor())
                    .build();

    private static HttpLoggingInterceptor providesInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.HEADERS);

        return interceptor;
    }

    public static RetrofitClient getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RetrofitClient();
        }
        return INSTANCE;
    }

    public RemoteApiAdapter providesApiServiceAdapter(){
        return retrofit.create(RemoteApiAdapter.class);
    }
}


