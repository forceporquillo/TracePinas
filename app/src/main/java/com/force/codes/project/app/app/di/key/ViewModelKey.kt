/*
 * Created by Force Porquillo on 7/1/20 6:24 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:24 AM
 */
package com.force.codes.project.app.app.di.key

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER
import kotlin.reflect.KClass

@MustBeDocumented
@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)