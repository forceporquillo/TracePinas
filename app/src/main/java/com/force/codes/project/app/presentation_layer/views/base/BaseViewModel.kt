/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:04 AM
 */
package com.force.codes.project.app.presentation_layer.views.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel protected constructor() : ViewModel() {
  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  fun addToUnsubscribed(disposable: Disposable?) {
    compositeDisposable.add(disposable!!)
  }

  override fun onCleared() {
    super.onCleared()
    if (compositeDisposable.isDisposed) {
      throw AssertionError(
          "Observer is already "
              + "dispose and is null"
      )
    }
    compositeDisposable.clear()
    compositeDisposable.dispose()
  }

}