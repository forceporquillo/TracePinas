/*
 * Created by Force Porquillo on 6/20/20 4:10 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:10 PM
 */

package com.force.codes.project.app.app;

import android.app.Application;

import com.force.codes.project.app.BuildConfig;
import com.force.codes.project.app.app.di.AppModule;
import com.force.codes.project.app.data_layer.resources.database.LocalDatabase;

import timber.log.Timber;

public class MyApplication extends Application{
    private AppComponent appComponent;

    private static void setDebugInstance(DebugTreeApplication debugInstance){
        if(BuildConfig.DEBUG){
            debugInstance.DebugTree();
        }else{
            Timber.plant(new DebugTreeApplication.CrashReportingTree());
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();

        LocalDatabase.setInstance(this);

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);

        setDebugInstance(new DebugTreeApplication(this));
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
