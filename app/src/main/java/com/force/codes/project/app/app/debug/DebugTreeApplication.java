/*
 * Created by Force Porquillo on 6/30/20 3:02 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/30/20 3:02 AM
 */

package com.force.codes.project.app.app.debug;

import android.app.Application;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import leakcanary.AppWatcher;
import leakcanary.ObjectWatcher;
import timber.log.Timber;

public class DebugTreeApplication{
    Application application;

    public DebugTreeApplication(Application application){
        this.application = application;
    }

    public void DebugTree(){
        Timber.plant(new Timber.DebugTree());
        AppWatcher.getConfig().getWatchActivities();
        AppWatcher.getConfig().getWatchFragments();
        AppWatcher.getConfig().getWatchFragmentViews();
        AppWatcher.getConfig().getWatchViewModels();
        ObjectWatcher watcher = AppWatcher.INSTANCE.getObjectWatcher();
        watcher.getRetainedObjectCount();
    }

    public static class CrashReportingTree extends Timber.Tree{
        @Override
        protected void log(
                int priority, @Nullable String tag,
                @NotNull String message, @Nullable Throwable t){
            if(priority == Log.VERBOSE || priority == Log.DEBUG){
                return;
            }

            CrashLibrary.log(priority, tag, message);

            if(t != null){
                if(priority == Log.ERROR){
                    CrashLibrary.logError(t);
                }else if(priority == Log.WARN){
                    CrashLibrary.logWarning(t);
                }
            }
        }
    }


}
