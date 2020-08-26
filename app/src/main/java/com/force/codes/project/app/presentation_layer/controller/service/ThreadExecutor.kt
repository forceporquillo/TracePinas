/*
 * Created by Force Porquillo on 6/2/20 9:02 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/29/20 8:39 PM
 */
package com.force.codes.project.app.presentation_layer.controller.service

import android.os.Handler
import android.os.Looper
import com.force.codes.project.app.presentation_layer.controller.utils.Utils.threadCount
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ThreadExecutor private constructor(
  private val diskIO: Executor,
  private val delay: Executor,
  private val mainThread: Executor,
  private val computation: Executor
) {

  constructor(delay: Int) : this(
      Executors.newSingleThreadExecutor(),
      ThreadExecutor(
          delay
      ),
      MainThreadExecutor(),
      Executors.newFixedThreadPool(
          threadCount
      )
  ) {
    // empty constructor
  }

  fun diskIO(): Executor {
    return diskIO
  }

  fun delayUIThread(): Executor {
    return delay
  }

  fun mainThread(): Executor {
    return mainThread
  }

  fun computationIO(): Executor {
    return computation
  }

  private class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable) {
      mainThreadHandler.post(command)
    }
  }

  private class ThreadExecutor(private val delay: Int) : Executor {
    private val handler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable) {
      handler.postDelayed(command, delay.toLong())
    }
  }
}