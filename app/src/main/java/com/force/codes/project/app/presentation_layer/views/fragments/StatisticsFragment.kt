/*
 * Created by Force Porquillo on 6/4/20 9:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/2/20 4:01 AM
 */
package com.force.codes.project.app.presentation_layer.views.fragments

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.force.codes.project.app.databinding.FragmentCountryStatisticsBinding
import com.force.codes.project.app.presentation_layer.controller.utils.Utils.animationUtils
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkCallback
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkUtils
import com.force.codes.project.app.presentation_layer.controller.utils.AppExecutors
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.MyCountryFragment
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.OverAllFragment
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.WorldwideFragment
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment(),
    NetworkCallback {
  private var adapter: FragmentPagerItemAdapter? = null
  private var binding: FragmentCountryStatisticsBinding? = null
  private var networkUtils: NetworkUtils? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    savedInstanceState.let {
      networkUtils = NetworkUtils(context)
      networkUtils!!.setOnNetworkListener(this)
      adapter = FragmentPagerItemAdapter(
          childFragmentManager,
          FragmentPagerItems.with(context!!)
              .apply {
                add(
                    "Overall Cases",
                    OverAllFragment::class.java
                )
                add(
                    "My Country",
                    MyCountryFragment::class.java
                )
                add(
                    "All Countries",
                    WorldwideFragment::class.java
                )
              }
              .create()
      )
    }
  }

  override fun onPause() {
    super.onPause()
    Timber.e("onPause called")
  }

  override fun onDestroy() {
    super.onDestroy()
    Timber.e("onDestroy called")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentCountryStatisticsBinding.inflate(
        inflater, container, false
    )
    binding!!.statistics = this
    return binding!!.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    savedInstanceState.let {
      binding!!.viewpager.adapter = adapter
      binding!!.tablayout.setViewPager(binding!!.viewpager)
      binding!!.invalidateAll()
      if (binding!!.hasPendingBindings()) {
        binding!!.executePendingBindings()
      }
    }
  }

  override fun onStart() {
    super.onStart()
    if (VERSION.SDK_INT >= VERSION_CODES.M) {
      networkUtils!!.startNetworkConnectivity()
    } else {
      Toast.makeText(context, "Your device must be Android Marshmallow " +
          "to apply these features", Toast.LENGTH_SHORT).show()
    }
  }

  override fun onNetworkConnectionChanged(
    connectivity: Connectivity?
  ) {
    Timber.i("Connection status: %s",
        connectivity?.state().toString()
    )
    showOrHideNetworkBanner(connectivity?.available())
  }

  override fun onInternetConnectionChanged(
    connected: Boolean?
  ) {
    return if (connected!!)
      showOrHideNetworkBanner(connected)
    else showOrHideNetworkBanner(connected)
  }

  private fun showOrHideNetworkBanner(
    available: Boolean?
  ) {
    var enable: Boolean? = null
    val banner = binding!!.network.relativeLayout
    Timber.i("Network status: %s", available)
    if (!available!!) {
      enable = true
      AppExecutors(
          100
      )
          .delayCurrentThread()
          .execute {
            banner.visibility = View.VISIBLE
          }
    } else {
      banner.visibility = View.GONE
    }
    banner.animation = enable?.let {
      animationUtils(it, context!!)
    }
    // refresh UI state
    binding!!.invalidateAll()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding!!.unbind()
    binding = null
    adapter = null
    networkUtils!!.stopConnection()
    networkUtils = null
  }

  companion object {
    @JvmStatic fun newInstance(): StatisticsFragment {
      val fragment = StatisticsFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}
