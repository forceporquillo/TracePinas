/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:04 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract class BaseViewModel extends ViewModel{
    private CompositeDisposable compositeDisposable;

    public void addToUnsubscribed(Disposable disposable){
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared(){
        compositeDisposable.clear();
        super.onCleared();
    }
}
