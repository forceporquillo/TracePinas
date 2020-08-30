/*
 * Created by Force Porquillo on 7/1/20 6:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:08 AM
 */
package com.force.codes.project.app.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.force.codes.project.app.app.di.key.ViewModelKey
import com.force.codes.project.app.presentation_layer.views.viewmodels.ListViewModel
import com.force.codes.project.app.presentation_layer.views.viewmodels.LiveDataViewModel
import com.force.codes.project.app.presentation_layer.views.viewmodels.MapViewModel
import com.force.codes.project.app.presentation_layer.views.viewmodels.MyCountryViewModel
import com.force.codes.project.app.presentation_layer.views.viewmodels.NavHostViewModel
import com.force.codes.project.app.presentation_layer.views.viewmodels.NewsViewModel
import com.force.codes.project.app.presentation_layer.views.viewmodels.OverAllViewModel
import com.force.codes.project.app.presentation_layer.views.viewmodels.WorldwideViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
  @Binds
  abstract fun bindViewModelFactory(
    factory: ViewModelProviderFactory?,
  ): Factory?

  @Binds @IntoMap
  @ViewModelKey(LiveDataViewModel::class)
  abstract fun providesLiveDataViewModel(
    liveDataViewModel: LiveDataViewModel?,
  ): ViewModel?

  @Binds @IntoMap
  @ViewModelKey(WorldwideViewModel::class)
  abstract fun providesWorldwideViewModel(
    worldwideViewModel: WorldwideViewModel?,
  ): ViewModel?

  @Binds @IntoMap @ViewModelKey(MapViewModel::class)
  abstract fun providesMapViewModel(
    mapViewModel: MapViewModel?,
  ): ViewModel?

  @Binds @IntoMap @ViewModelKey(NewsViewModel::class)
  abstract fun providesNewsViewModel(
    newsViewModel: NewsViewModel?,
  ): ViewModel?

  @Binds @IntoMap @ViewModelKey(OverAllViewModel::class)
  abstract fun providesOverAllViewModel(
    overAllViewModel: OverAllViewModel?,
  ): ViewModel?

  @Binds @IntoMap @ViewModelKey(MyCountryViewModel::class)
  abstract fun providesMyCountryViewModel(
    myCountryViewModel: MyCountryViewModel?,
  ): ViewModel?

  @Binds @IntoMap @ViewModelKey(ListViewModel::class)
  abstract fun providesListViewModel(
    listViewModel: ListViewModel?,
  ): ViewModel?

  @Binds @IntoMap @ViewModelKey(NavHostViewModel::class)
  abstract fun providesNavHostViewModel(
    navHostViewModel: NavHostViewModel?,
  ): ViewModel?
}