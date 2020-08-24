/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:04 AM
 */

package com.force.codes.project.app.presentation_layer.views.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {
  private final CompositeDisposable compositeDisposable;

  protected BaseViewModel() {
    compositeDisposable = new CompositeDisposable();
  }

  public void addToUnsubscribed(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  @Override
  protected void onCleared() {
    super.onCleared();

    if (compositeDisposable.isDisposed()) {
      throw new AssertionError(
              "Observer is already "
          + "dispose and is null"
      );
    }

    compositeDisposable.clear();
    compositeDisposable.dispose();
  }
}