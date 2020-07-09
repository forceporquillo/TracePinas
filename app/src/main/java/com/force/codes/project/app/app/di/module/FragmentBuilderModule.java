/*
 * Created by Force Porquillo on 7/2/20 12:55 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 12:55 AM
 */

package com.force.codes.project.app.app.di.module;

import com.force.codes.project.app.presentation_layer.views.fragments.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.WorldwideFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule{
    @ContributesAndroidInjector
    abstract LiveDataFragment contributeLiveDataFragment();

    @ContributesAndroidInjector
    abstract MapFragment contributeMapFragment();

    @ContributesAndroidInjector
    abstract WorldwideFragment contributeWorldwideFragment();

    @ContributesAndroidInjector
    abstract NewsFragment contributeNewsFragment();
}
