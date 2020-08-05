/*
 * Created by Force Porquillo on 6/11/20 9:04 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 * Last modified 7/30/20 5:00 PM
 */
package com.force.codes.project.app.presentation_layer.controller.utils.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.force.codes.project.app.presentation_layer.controller.utils.Utils.requiresSdkInt
import com.force.codes.project.app.presentation_layer.controller.utils.Utils.sDKInt
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ConnectivityPredicate
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.InternetObservingSettings
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.InternetObservingStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @Author Force Porquillo
 */
open class NetworkUtils
{
  private val disposable = CompositeDisposable()
  private var networkCallback: NetworkCallback? = null
  private var context: Context? = null

  /**
   * Singleton instance approach, if you're working on multiple Fragment
   * or Activity that requires both upstream to observe network changes.
   *
   * @param context instance NetworkUtils is linked to BaseFragment
   */
  constructor(context: Context?) {
    this.context = context
  }

  /**
   * if only need {@method startNetworkConnectivity} to listen to network changes.
   * You can directly instantiate an instance without requiring a context reference.
   * But, you have to manually remove connectivity reference linked from attached
   * Fragment into super class BaseFragment to avoid memory leaks.
   */
  constructor()

  /**
   * Observing network connectivity.
   * Checks only connectivity with the network (not Internet).
   */
  @RequiresApi(api = VERSION_CODES.M)
  fun startNetworkConnectivity() {
    disposable.add(
        ReactiveNetwork
            .observeNetworkConnectivity(context)
            .subscribeOn(Schedulers.io())
            .filter(
                ConnectivityPredicate.hasType(
                    getActiveNetwork(context)
                )
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { connectivity: Connectivity? ->
              setNetworkConnectivity(connectivity)
            }
    )
  }

  /**
   * This method is less efficient than {@method startNetworkConnectivity}.
   * Because it opens socket connection with remote host (https://github.com/forceporquillo)
   * every two seconds with two seconds of timeout and consumes data transfer.
   */
  fun startInternetConnectivity() {
    disposable.add(
        ReactiveNetwork.observeInternetConnectivity(settings)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ connectivity: Boolean ->
              setInternetConnectivity(connectivity)
            }) { obj: Throwable ->
              obj.printStackTrace()
            }
    )
  }

  /**
   * This disposed the attached subscription of observer from [ReactiveNetwork]
   * and [Context] object of parent host to avoid memory leaks.
   *
   * This must be called in onDestroy/onDestroyView method every time a Fragment
   * or Activity is destroyed.
   */
  fun stopConnection() {
    disposable.dispose()
    context = null
  }

  private fun setNetworkConnectivity(
    connectivity: Connectivity?
  ) {
    networkCallback!!.onNetworkConnectionChanged(
        connectivity
    )
  }

  private fun setInternetConnectivity(
    connectivity: Boolean
  ) {
    networkCallback!!.onInternetConnectionChanged(
        connectivity
    )
  }

  /**
   * Register a callback to be invoked when listening to network changes.
   *
   * @param networkCallback return callback reference from attached fragment.
   */
  fun setOnNetworkListener(
    networkCallback: NetworkCallback?
  ) {
    this.networkCallback = networkCallback
  }

  /**
   * Force both network upstreams to listen to network changes.
   *
   * Requires android version Marshmallow to emmit both upstreams.
   */

  @RequiresApi(VERSION_CODES.M)
  fun startConnection() {
    if (requiresSdkInt(VERSION_CODES.M)) {
      startInternetConnectivity()
      startNetworkConnectivity()
      return
    }
    startInternetConnectivity()
  }

  companion object {
    /**
     * Indicates there is no available network.
     */
    private const val NOT_CONNECTED = 0

    /**
     * Indicates there is available network.
     */
    private const val CONNECTED = 1

    /**
     * Indicates this network uses a Cellular transport.
     */
    private const val CELLULAR = 1

    /**
     * Indicates this network uses a Wi-Fi transport.
     */
    private const val WIFI = 2

    /**
     * When device is below Marshmallow, we get all active
     * network and assumes to return connected when one
     * of these 3 network cap. satisfies the condition.
     * Otherwise, get only active network and expect it
     * to return cellular or Wi-Fi transport.
     */
    @RequiresApi(api = VERSION_CODES.M)
    private fun getActiveNetwork(
      context: Context?
    ): Int {
      val service = context!!
          .getSystemService(
              Context.CONNECTIVITY_SERVICE
          ) as ConnectivityManager
      var capabilities: NetworkCapabilities? = null
      if (requiresSdkInt(
              VERSION_CODES.M
          )
      ) {
        val network = service.activeNetwork
        capabilities?.let {
          service.getNetworkCapabilities(network)
          return if (
              capabilities!!.hasTransport(
                  NetworkCapabilities.TRANSPORT_CELLULAR
              )
          ) CELLULAR else WIFI
        }
      } else {
        val allNetworks = service.allNetworks
        for (network in allNetworks) {
          capabilities.let {
            capabilities = service.getNetworkCapabilities(network)
            return if (
                capabilities!!.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || capabilities!!.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                ) || capabilities!!.hasTransport(
                    NetworkCapabilities.TRANSPORT_ETHERNET
                )
            ) CONNECTED else NOT_CONNECTED
          }
        }
      }
      return NOT_CONNECTED
    }

    private val settings = InternetObservingSettings
        .builder()
        .host("https://github.com/forceporquillo")
        .initialInterval(0)
        .interval(2)
        .timeout(2000)
        .build()
  }
}
