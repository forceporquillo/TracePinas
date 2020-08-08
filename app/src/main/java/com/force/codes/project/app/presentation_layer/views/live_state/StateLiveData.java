/*
 * Created by Force Porquillo on 8/8/20 7:15 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 7:15 PM
 */

package com.force.codes.project.app.presentation_layer.views.live_state;

import androidx.lifecycle.MutableLiveData;

public class StateLiveData<T> extends MutableLiveData<StateData<T>> {
  public void postLoading() {
    postValue(new StateData<T>().loading());
  }

  public void postError(Throwable throwable) {
    postValue(new StateData<T>().error(throwable));
  }

  public void postSuccess(T data) {
    postValue(new StateData<T>().success(data));
  }

  public void postComplete() {
    postValue(new StateData<T>().complete());
  }
}
