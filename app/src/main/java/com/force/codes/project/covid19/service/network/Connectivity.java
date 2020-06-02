package com.force.codes.project.covid19.service.network;

/*
 * Created by Force Porquillo on 6/1/20 2:30 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 5/30/20 1:03 AM
 *
 */


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;


public class Connectivity{
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null){
            throw new TypeCastException("null cannot be cast to non-null type");
        } else{
            if(Build.VERSION.SDK_INT >= 29){
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(
                        connectivityManager.getActiveNetwork());

                if(networkCapabilities != null){
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
                } else{
                    return false;
                }
            } else{
                if(connectivityManager.getActiveNetwork() != null){
                    NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                    if(info == null){
                        Intrinsics.throwNpe();
                    }

                    return info.isConnectedOrConnecting();
                }
            }
        }
        return false;
    }
}
