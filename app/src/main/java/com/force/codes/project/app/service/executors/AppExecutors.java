package com.force.codes.project.app.service.executors;

/*
 * Created by Force Porquillo on 6/2/20 6:15 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 6:18 AM
 *
 */

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppExecutors{
    private static final int THREAD_COUNT = 3;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;
    private final Executor computationThread;


    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread, Executor computationThread){
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

    public Executor mainHandler(){
        return mainThread;
    }

    public Executor computationalThread(){
        return computationThread;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NotNull Runnable command){
            mainThreadHandler.postDelayed(command, 150);
        }
    }
}
