/*
 * Created by Force Porquillo on 7/2/20 12:47 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 12:47 AM
 */

package com.force.codes.project.app.app.di.module;

import com.force.codes.project.app.presentation_layer.views.activity.FragmentContainerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule{
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract FragmentContainerActivity bindFragmentContainerActivity();
}
