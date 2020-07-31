/*
 * Created by Force Porquillo on 6/11/20 8:02 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */
package com.force.codes.project.app.presentation_layer.controller.utils.network

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity

interface NetworkCallback {
  fun onNetworkConnectionChange(connectivity: Connectivity?)
  fun onInternetConnectionChanged(connected: Boolean?)
}