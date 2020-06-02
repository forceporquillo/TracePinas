package com.force.codes.project.covid19.service.executors;

/*
 * Created by Force Porquillo on 6/1/20 2:29 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 5/29/20 1:34 PM
 *
 */

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors{
    private static final int THREAD_COUNT = 3;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread){
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public AppExecutors(){
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
    }

    public Executor diskIO(){
        return diskIO;
    }

    public Executor networkIO(){
        return networkIO;
    }

    public Executor mainThreadHelper(){
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NotNull Runnable command){
            mainThreadHandler.post(command);
        }
    }
}
