package com.force.codes.project.covid19.service.network;

/*
 * Created by Force Porquillo on 6/1/20 9:03 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 5/27/20 1:08 AM
 *
 */

public interface CallbackConnectionReceiver{
    void onNetworkConnectionChanged(boolean isConnected);
}