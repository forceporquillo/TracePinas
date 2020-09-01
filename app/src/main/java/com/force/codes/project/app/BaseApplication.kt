/*
 * Created by Force Porquillo on 6/20/20 4:10 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:10 PM
 */
package com.force.codes.project.app

import android.app.Application
import com.force.codes.project.app.app.debug.DebugTreeApp.CrashReportingTree
import com.force.codes.project.app.app.debug.DebugTreeApp.debug
import com.force.codes.project.app.app.debug.DetectLeak.enabledStrictMode
import com.force.codes.project.app.app.debug.DetectLeak.startLeak
import com.force.codes.project.app.app.di.AppComponent
import com.force.codes.project.app.app.di.DaggerAppComponent
import com.force.codes.project.app.app.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector {

  @Inject lateinit var injector: DispatchingAndroidInjector<Any>

  override fun onCreate() {
    super.onCreate()
    component = DaggerAppComponent.builder()
        .application(this)
        ?.appModule(AppModule(this))
        ?.build()!!
    component.inject(this)
    setDebugInstance()
  }

  val appComponent: AppComponent?
    get() = component

  override fun androidInjector(): AndroidInjector<Any> {
    return injector
  }

  companion object {
    lateinit var component: AppComponent
      private set

    private fun setDebugInstance() {
      if (BuildConfig.DEBUG) {
        debug()
        enabledStrictMode()
        startLeak()
      } else {
        Timber.plant(CrashReportingTree())
      }
    }
  }
}