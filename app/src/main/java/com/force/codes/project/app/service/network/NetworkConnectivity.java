package com.force.codes.project.app.service.network;

/*
 * Created by Force Porquillo on 6/11/20 9:04 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.content.Context;

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.InternetObservingSettings;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NetworkConnectivity{
    private static final int initialInterval = 0;
    private static final int interval = 2;
    private static final int timeout = 2000;
    private NetworkCallback networkCallback;
    private ConnectionCallback connectionCallback;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Context context;
    private Boolean connectionState = null; // check connection if idle or not.
    private int networkOptions;

    public NetworkConnectivity(Context context, NetworkCallback callback){
        networkOptions = 1;
        this.context = context.getApplicationContext();
        this.networkCallback = callback;
    }

    public NetworkConnectivity(ConnectionCallback callback){
        networkOptions = 2;
        this.connectionCallback = callback;
    }

    private InternetObservingSettings observingSettings(){
        return InternetObservingSettings.builder()
                .initialInterval(initialInterval)
                .interval(interval)
                .host("www.google.com")
                .timeout(timeout)
                .build();
    }

    /**
     * Observing network connectivity.
     * Checks only connectivity with the network
     * (not Internet).
     */
    final void checkNetworkConnectivity(){
        disposable.add(ReactiveNetwork
                .observeNetworkConnectivity(context.getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setNetworkConnectivity)
        );
    }

    /**
     * This method is less efficient than {@method checkNetworkConnectivity}.
     * Because it opens socket connection with remote host (www.google.com)
     * every two seconds with two seconds of timeout and consumes data transfer.
     */
    final void checkInternetConnectivity(){
        disposable.add(ReactiveNetwork.observeInternetConnectivity(observingSettings())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectionState -> {
                    Timber.d("Successfully establish connection with WalledGardenStrategy.");
                    setConnectionConnectivity(connectionState);
                }, Throwable::printStackTrace)
        );

    }

    /**
     * This disposed the attached {@param Context} of
     * parent host to avoid memory leaks.
     */
    public void destroyConnection(){
        connectionCallback = null;
        networkCallback = null;
        context = null;
        disposable.dispose();
    }

    final void setNetworkConnectivity(Connectivity connectivity){
        networkCallback.onNetworkConnectionChange(connectivity);
    }

    final void setConnectionConnectivity(Boolean connectivity){
        connectionCallback.onInternetConnectionChanged(connectivity);
    }

    public void startConnection(){
        if(networkOptions == 1){ // check network only (not internet)
            if(networkCallback != null){
                connectionState = true;
                this.checkInternetConnectivity();
            } else{
                throw new NullPointerException("You must implement and pass an instance " +
                        "of ConnectionCallback interface.");
            }
        } else if(networkOptions == 2){ // ping connection every 2 seconds (google)
            if(connectionCallback != null){
                connectionState = true;
                this.checkInternetConnectivity();
            } else{
                throw new NullPointerException("You must implement and pass an instance " +
                        "of NetworkCallback interface.");
            }
        }
    }

    public InternetObservingSettings pauseConnection(){
        if(connectionState){
            return observingSettings();
        }

        return pauseConnection();
    }
}
