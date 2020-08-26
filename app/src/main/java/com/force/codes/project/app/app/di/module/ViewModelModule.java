/*
 * Created by Force Porquillo on 7/1/20 6:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:08 AM
 */

package com.force.codes.project.app.app.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.force.codes.project.app.app.di.key.ViewModelKey;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.ListViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.LiveDataViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MapViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MyCountryViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.NewsViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.OverAllViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.WorldwideViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

  @Binds
  @IntoMap
  @ViewModelKey(LiveDataViewModel.class)
  abstract ViewModel providesLiveDataViewModel(LiveDataViewModel liveDataViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(WorldwideViewModel.class)
  abstract ViewModel providesWorldwideViewModel(WorldwideViewModel worldwideViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(MapViewModel.class)
  abstract ViewModel providesMapViewModel(MapViewModel mapViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(NewsViewModel.class)
  abstract ViewModel providesNewsViewModel(NewsViewModel newsViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(OverAllViewModel.class)
  abstract ViewModel providesOverAllViewModel(OverAllViewModel overAllViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(MyCountryViewModel.class)
  abstract ViewModel providesMyCountryViewModel(MyCountryViewModel myCountryViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(ListViewModel.class)
  abstract ViewModel providesListViewModel(ListViewModel listViewModel);
}
