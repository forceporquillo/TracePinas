/*
 * Created by Force Porquillo on 6/20/20 4:10 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:10 PM
 */

package com.force.codes.project.app;

import android.app.Application;
import android.os.Build;

import com.force.codes.project.app.app.debug.DebugTreeApplication;
import com.force.codes.project.app.app.di.AppComponent;
import com.force.codes.project.app.app.di.DaggerAppComponent;
import com.force.codes.project.app.app.di.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

import static com.force.codes.project.app.app.debug.LeakDetection.startLeakDetection;

public class BaseApplication extends Application implements HasAndroidInjector{
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    private AppComponent appComponent;

    public final static int SDK_INT = Build.VERSION.SDK_INT;

    private static void setDebugInstance(DebugTreeApplication debugInstance){
        if(BuildConfig.DEBUG){
            debugInstance.DebugTree();
            startLeakDetection();
        }else Timber.plant(new DebugTreeApplication.CrashReportingTree());
    }

    @Override
    public void onCreate(){
        super.onCreate();
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

    @Override
    public AndroidInjector<Object> androidInjector(){
        return dispatchingAndroidInjector;
    }
}
