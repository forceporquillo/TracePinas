package com.force.codes.project.covid19.service.network;

/*
 * Created by Force Porquillo on 6/1/20 2:30 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 5/30/20 1:03 AM
 *
 */

import android.app.Application;

public class NetworkConnectivityHelper extends Application{
    private static NetworkConnectivityHelper mInstance;

    public static synchronized NetworkConnectivityHelper getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public void setConnectivityListener(CallbackConnectionReceiver listener){
        ConnectivityReceiver.receiver = listener;
    }
}
