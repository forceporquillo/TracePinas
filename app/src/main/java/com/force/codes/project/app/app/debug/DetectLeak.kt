/*
 * Created by Force Porquillo on 8/2/20 11:23 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/2/20 11:23 AM
 */

package com.force.codes.project.app.app.debug

import android.os.StrictMode
import leakcanary.AppWatcher

object DetectLeak{
  @JvmStatic fun startLeak() {
    AppWatcher.config.watchActivities
    AppWatcher.config.watchFragments
    AppWatcher.config.watchFragmentViews
    AppWatcher.config.watchViewModels

    val objectWatcher =
      AppWatcher.objectWatcher
    objectWatcher.retainedObjectCount
  }

  @JvmStatic fun enabledStrictMode() {
    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyDeath()
            .build()
    )
  }
}