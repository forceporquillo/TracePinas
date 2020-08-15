/*
 * Created by Force Porquillo on 7/1/20 6:30 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:30 AM
 */

package com.force.codes.project.app.app.di.module;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.force.codes.project.app.data_layer.model.map_data.WorldData;
import com.force.codes.project.app.presentation_layer.controller.model.BottomItem;
import com.force.codes.project.app.presentation_layer.controller.utils.AppExecutors;
import com.google.android.gms.maps.model.Marker;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class AppModule {
  private Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Named("LiveDataVM")
  static MutableLiveData<WorldData> provideMutableLiveData() {
    return new MutableLiveData<>();
  }

  @Provides
  @Singleton
  static AppExecutors providesAppExecutor() {
    return new AppExecutors(0);
  }

  @Provides
  @Named("MapListDataSet")
  static List<String> providesDataSet() {
    return new LinkedList<>(Arrays.asList("Philippines", "Global Cases"));
  }

  @Provides
  static Runnable[] providesRunnableThread() {
    return new Runnable[1];
  }

  @Provides
  static Marker[] providesMarker() {
    return new Marker[1];
  }

  @Provides
  @Singleton
  static ArrayList<BottomItem> providesBottomItem() {
    return new ArrayList<>();
  }

  @Provides
  @Singleton
  public Application providesApplication() {
    return application;
  }
}
