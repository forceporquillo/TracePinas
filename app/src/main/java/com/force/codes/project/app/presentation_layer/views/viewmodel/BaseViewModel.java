/*
 * Created by Force Porquillo on 7/1/20 3:47 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 3:47 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


abstract class BaseViewModel extends ViewModel{
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void addToUnsubscribed(Disposable disposables){
        compositeDisposable.add(disposables);
    }

    @Override
    protected void onCleared(){
        compositeDisposable.clear();
        super.onCleared();
    }
}
