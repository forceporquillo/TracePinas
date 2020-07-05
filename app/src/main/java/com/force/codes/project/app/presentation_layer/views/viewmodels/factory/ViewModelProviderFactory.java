/*
 * Created by Force Porquillo on 7/1/20 6:07 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 6:07 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory implements ViewModelProvider.Factory{
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators){
        this.creators = creators;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        Provider<? extends ViewModel> creator = creators.get(modelClass);

        if(creator == null){
            for(Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()){
                creator = entry.getValue();
                break;
            }
        }

        if(creator == null){
            throw new IllegalArgumentException("unknown model class: " + modelClass);
        }

        try{
            return (T) creator.get();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
