/*
 * Created by Force Porquillo on 6/20/20 4:16 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:16 PM
 */

package com.force.codes.project.app.presentation_layer.controller.support;

public class FakeCrashLibrary{
    public static void log(int priority, String tag, String message) {
        // TODO add log entry to circular buffer.
    }

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }
}
