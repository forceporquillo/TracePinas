/*
 * Created by Force Porquillo on 6/20/20 4:16 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:16 PM
 */

package com.force.codes.project.app.presentation_layer.controller.custom.utils;

import timber.log.Timber;

public class CustomCrashLibrary{
    private CustomCrashLibrary(){
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
