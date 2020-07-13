/*
 * Created by Force Porquillo on 7/1/20 6:45 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 3:36 AM
 */

package com.force.codes.project.app.app.di;

import com.force.codes.project.app.BaseApplication;
import com.force.codes.project.app.app.di.module.ActivityBuilderModule;
import com.force.codes.project.app.app.di.module.AppModule;
import com.force.codes.project.app.app.di.module.DatabaseModule;
import com.force.codes.project.app.app.di.module.NetworkModule;
import com.force.codes.project.app.app.di.module.RepositoryModule;
import com.force.codes.project.app.app.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ViewModelModule.class,
                AppModule.class,
                ActivityBuilderModule.class,
                DatabaseModule.class,
                NetworkModule.class,
                RepositoryModule.class
        }
)
public interface AppComponent{
    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(BaseApplication baseApplication);
        Builder appModule(AppModule appModule);
        AppComponent build();
    }
}
