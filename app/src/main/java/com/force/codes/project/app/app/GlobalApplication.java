/*
 * Created by Force Porquillo on 6/20/20 4:10 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:10 PM
 */

package com.force.codes.project.app.app;

import android.app.Application;
import android.util.Log;

import com.force.codes.project.app.BuildConfig;
import com.force.codes.project.app.data_layer.resources.database.LocalDatabase;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.CustomCrashLibrary;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import leakcanary.AppWatcher;
import leakcanary.ObjectWatcher;
import timber.log.Timber;

public class GlobalApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();

        LocalDatabase.setInstance(this);

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            AppWatcher.getConfig().getWatchActivities();
            AppWatcher.getConfig().getWatchFragments();
            ObjectWatcher objectWatcher = AppWatcher.INSTANCE
                    .getObjectWatcher();
            objectWatcher.getRetainedObjectCount();
        } else{
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.Tree{
        @Override
        protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t){
            if(priority == Log.VERBOSE || priority == Log.DEBUG){
                return;
            }

            CustomCrashLibrary.log(priority, tag, message);

            if(t != null){
                if(priority == Log.ERROR){
                    CustomCrashLibrary.logError(t);
                }
            } else if(priority == Log.WARN){
                CustomCrashLibrary.logWarning(null);
            }
        }
    }
}
