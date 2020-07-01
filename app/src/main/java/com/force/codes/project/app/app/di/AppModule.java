/*
 * Created by Force Porquillo on 7/1/20 6:30 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:30 AM
 */

package com.force.codes.project.app.app.di;

import android.app.Application;

import com.force.codes.project.app.app.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule{
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }
}
