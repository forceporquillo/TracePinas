/*
 * Created by Force Porquillo on 7/2/20 12:55 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 12:55 AM
 */

package com.force.codes.project.app.app.di.module;

import com.force.codes.project.app.app.di.scope.AppScope;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.MyCountryFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.OverAllFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.WorldwideFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
  @AppScope
  @ContributesAndroidInjector
  abstract LiveDataFragment contributeLiveDataFragment();

  @AppScope
  @ContributesAndroidInjector
  abstract MapFragment contributeMapFragment();

  @AppScope
  @ContributesAndroidInjector
  abstract WorldwideFragment contributeWorldwideFragment();

  @AppScope
  @ContributesAndroidInjector
  abstract NewsFragment contributeNewsFragment();

  @AppScope
  @ContributesAndroidInjector
  abstract OverAllFragment contributeOverAllFragment();

  @AppScope
  @ContributesAndroidInjector
  abstract MyCountryFragment contributeMyFragment();
}
