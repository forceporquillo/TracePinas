/*
 * Created by Force Porquillo on 7/1/20 6:45 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 3:36 AM
 */
package com.force.codes.project.app.app.di

import com.force.codes.project.app.BaseApplication
import com.force.codes.project.app.app.di.module.ActivityBuilderModule
import com.force.codes.project.app.app.di.module.AppModule
import com.force.codes.project.app.app.di.module.DatabaseModule
import com.force.codes.project.app.app.di.module.FragmentBuilderModule
import com.force.codes.project.app.app.di.module.NetworkModule
import com.force.codes.project.app.app.di.module.RepositoryModule
import com.force.codes.project.app.app.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      AndroidSupportInjectionModule::class,
      AppModule::class,
      ViewModelModule::class,
      RepositoryModule::class,
      DatabaseModule::class,
      ActivityBuilderModule::class,
      NetworkModule::class,
      ActivityBuilderModule::class
    ]
)
interface AppComponent {
  fun inject(application: BaseApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: BaseApplication): Builder?
    fun appModule(appModule: AppModule): Builder?
    fun build(): AppComponent?
  }
}