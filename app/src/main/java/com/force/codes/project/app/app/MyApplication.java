/*
 * Created by Force Porquillo on 6/20/20 4:10 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:10 PM
 */

package com.force.codes.project.app.app;

import android.app.Application;

import com.force.codes.project.app.BuildConfig;
import com.force.codes.project.app.data_layer.resources.database.LocalDatabase;

import timber.log.Timber;

public class MyApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();

        LocalDatabase.setInstance(this);

        setDebugInstance(new DebugTreeApplication(this));
    }

    private static void setDebugInstance(DebugTreeApplication debugInstance){
        if(BuildConfig.DEBUG){
            debugInstance.DebugTree();
        }else{
            Timber.plant(new DebugTreeApplication.CrashReportingTree());
        }
    }
}