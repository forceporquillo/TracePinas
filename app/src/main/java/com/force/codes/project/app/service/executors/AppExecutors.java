/*
 * Created by Force Porquillo on 6/2/20 9:02 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/29/20 8:39 PM
 */

package com.force.codes.project.app.service.executors;


import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors{
    // get device processor count
    private static final int THREAD_COUNT = Runtime
            .getRuntime().availableProcessors() * 2;

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;
    private final Executor computationThread;


    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread,
                         Executor computationThread){
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
        this.computationThread = computationThread;
    }

    public AppExecutors(){
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT));
    }

    public Executor diskIO(){
        return diskIO;
    }

    public Executor networkIO(){
        return networkIO;
    }

    public Executor mainThread(){
        return mainThread;
    }

    public Executor computationIO(){
        return computationThread;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NotNull Runnable command){
            mainThreadHandler.post(command);
        }

        public void delay(Runnable runnable, int i){
            mainThreadHandler.postDelayed(runnable, i);
        }
    }
}