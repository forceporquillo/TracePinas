/*
 * Created by Force Porquillo on 6/20/20 4:10 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:10 PM
 */

package com.force.codes.project.app;

import android.app.Application;
import com.force.codes.project.app.app.debug.DebugTreeApp;
import com.force.codes.project.app.app.debug.DetectLeak;
import com.force.codes.project.app.app.di.AppComponent;
import com.force.codes.project.app.app.di.DaggerAppComponent;
import com.force.codes.project.app.app.di.module.AppModule;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import javax.inject.Inject;
import timber.log.Timber;

public class BaseApplication extends Application implements HasAndroidInjector {
  @Inject
  DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

  private AppComponent appComponent;

  private static void setDebugInstance() {
    if (BuildConfig.DEBUG) {
      DebugTreeApp.debug();
      DetectLeak.enabledStrictMode();
      DetectLeak.startLeak();
    } else {
      Timber.plant(new DebugTreeApp.CrashReportingTree());
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = DaggerAppComponent.builder()
        .application(this)
        .appModule(new AppModule(this))
        .build();

    appComponent.inject(this);
    setDebugInstance();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  public AndroidInjector<Object> androidInjector() {
    return dispatchingAndroidInjector;
  }
}
