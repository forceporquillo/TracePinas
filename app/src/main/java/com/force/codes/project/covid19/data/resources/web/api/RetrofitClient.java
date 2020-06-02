package com.force.codes.project.covid19.data.resources.web.api;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static RetrofitClient INSTANCE;
    private static Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(providesOkHttpClientBuilder())
                .build();
    }

    private static OkHttpClient providesOkHttpClientBuilder(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }

    public static RetrofitClient getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RetrofitClient();
        }
        return INSTANCE;
    }

    public ApiServiceAdapter providesApiServiceAdapter(){
        return retrofit.create(ApiServiceAdapter.class);
    }
}