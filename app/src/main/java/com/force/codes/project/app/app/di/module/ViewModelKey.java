/*
 * Created by Force Porquillo on 7/1/20 6:24 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:24 AM
 */

package com.force.codes.project.app.app.di.module;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

@Target({
        ElementType.METHOD
})

@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKey{
    Class <? extends ViewModel> value();
}
