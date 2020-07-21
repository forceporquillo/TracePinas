/*
 * Created by Force Porquillo on 7/22/20 7:06 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/22/20 7:06 AM
 */

package com.force.codes.project.app.app.debug;

import leakcanary.AppWatcher;
import leakcanary.ObjectWatcher;

public class LeakDetection{
    public static void startLeakDetection(){
        AppWatcher.getConfig().getWatchActivities();
        AppWatcher.getConfig().getWatchFragments();
        AppWatcher.getConfig().getWatchFragmentViews();
        AppWatcher.getConfig().getWatchViewModels();
        ObjectWatcher watcher = AppWatcher.INSTANCE.getObjectWatcher();
        watcher.getRetainedObjectCount();
    }
}
