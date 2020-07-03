/*
 * Created by Force Porquillo on 7/2/20 2:49 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 2:49 AM
 */

package com.force.codes.project.app.app.debug;

import timber.log.Timber;

public class CrashLibrary{

    private CrashLibrary(){
        throw new AssertionError("No instances.");
    }

    public static void log(int priority, String tag, String message){
        Timber.log(priority, message, tag);
    }

    public static void logWarning(Throwable t){
        Timber.e(t);
    }

    public static void logError(Throwable t){
        Timber.e(t);
    }
}