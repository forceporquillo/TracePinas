/*
 * Created by Force Porquillo on 6/2/20 6:45 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:35 AM
 */

package com.force.codes.project.app.app;

import com.force.codes.project.app.app.di.AppModule;
import com.force.codes.project.app.presentation_layer.views.viewmodels.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        ViewModelModule.class,
        AppModule.class
})

public interface AppComponent{
    void inject(MyApplication myApplication);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(MyApplication myApplication);
        Builder appModule(AppModule appModule);
        AppComponent build();
    }
}
