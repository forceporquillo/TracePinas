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

import static com.force.codes.project.app.BaseApplication.THREAD_COUNT;

public class AppExecutors{
    private final Executor diskIO;
    private final Executor threadDelay;
    private final Executor mainThread;
    private final Executor computationThread;

    private AppExecutors(Executor diskIO, Executor threadDelay, Executor mainThread,
                         Executor computationThread){
        this.diskIO = diskIO;
        this.threadDelay = threadDelay;
        this.mainThread = mainThread;
        this.computationThread = computationThread;
    }

    public AppExecutors(int delay){ this(
            Executors.newSingleThreadExecutor(),
            new ThreadExecutor(delay),
            new MainThreadExecutor(),
            Executors.newFixedThreadPool(THREAD_COUNT));
    }

    public Executor diskIO(){
        return diskIO;
    }

    public Executor threadDelay(){
        return threadDelay;
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
    }

    private static class ThreadExecutor implements Executor{
        private Handler handler = new Handler(Looper.getMainLooper());
        private int delay;

        public ThreadExecutor(int delay){
            this.delay = delay;
        }

        @Override
        public void execute(Runnable command){
            handler.postDelayed(command, delay);
        }
    }
}