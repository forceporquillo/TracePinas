package com.force.codes.project.covid19.service.network;

/*
 * Created by Force Porquillo on 6/1/20 2:30 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 5/30/20 1:03 AM
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import kotlin.TypeCastException;


public class ConnectivityReceiver extends BroadcastReceiver{
    private static final String TYPE_CAST_NULL = "null cannot be cast to non-null type";
    public static CallbackConnectionReceiver receiver;

    public ConnectivityReceiver(){
        super();
    }

    public static boolean isConnected(){
        ConnectivityManager manager = (ConnectivityManager)
                NetworkConnectivityHelper.getInstance().getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        if(manager != null){
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            return activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    @Override
    public void onReceive(Context context, Intent intent){
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager == null){
            throw new TypeCastException(TYPE_CAST_NULL);
        } else{
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();

            if(receiver != null){
                receiver.onNetworkConnectionChanged(isConnected);
            }
        }
    }
}

