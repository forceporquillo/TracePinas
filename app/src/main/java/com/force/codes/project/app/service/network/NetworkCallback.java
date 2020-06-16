/*
 * Created by Force Porquillo on 6/11/20 8:02 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.service.network;

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;

public interface NetworkCallback{
    void onNetworkConnectionChange(Connectivity connectivity);
}