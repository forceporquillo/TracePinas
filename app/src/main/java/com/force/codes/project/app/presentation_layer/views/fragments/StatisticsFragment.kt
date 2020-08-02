/*
 * Created by Force Porquillo on 6/4/20 9:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/2/20 4:01 AM
 */
package com.force.codes.project.app.presentation_layer.views.fragments

import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.force.codes.project.app.databinding.FragmentCountryStatisticsBinding
import com.force.codes.project.app.presentation_layer.controller.utils.Utils.animate
import com.force.codes.project.app.presentation_layer.controller.utils.Utils.requiresSdkInt
import com.force.codes.project.app.presentation_layer.controller.utils.network.NetworkCallback
import com.force.codes.project.app.presentation_layer.controller.utils.network.NetworkUtils
import com.force.codes.project.app.presentation_layer.controller.utils.threads.AppExecutors
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
class StatisticsFragment : Fragment(), NetworkCallback {
  private var adapter: FragmentPagerItemAdapter? = null
  private var binding: FragmentCountryStatisticsBinding? = null
  private var networkUtils: NetworkUtils? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    savedInstanceState.let {
      Timber.e("bundle is not null")
      networkUtils = NetworkUtils(context!!)
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

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentCountryStatisticsBinding.inflate(
        inflater, container, false
    )
    binding!!.statistics = this
    binding!!.lifecycleOwner = this
    binding!!.invalidateAll()
    return binding!!.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    binding!!.viewpager.adapter = adapter
    binding!!.tablayout.setViewPager(binding!!.viewpager)
    networkUtils!!.setOnNetworkListener(this)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding!!.unbind()
    binding = null
    adapter = null
    networkUtils = null
  }

  @RequiresApi(api = VERSION_CODES.M)
  override fun onStart() {
    super.onStart()
    if (requiresSdkInt(
            VERSION_CODES.M
        )
    ) {
      networkUtils!!.startConnection()
      return
    }
    networkUtils!!.startInternetConnectivity()
  }

  override fun onDestroy() {
    super.onDestroy()
    networkUtils!!.stopConnection()
  }

  override fun onNetworkConnectionChanged(
    connectivity: Connectivity?
  ) {
    val available = connectivity?.available()

    Timber.i(
        "Connection status: %s",
        connectivity!!.state()
            .toString()
    )

    return if (available!!)
      showOrHideNetworkBanner(
          available
      ) else showOrHideNetworkBanner(
        available
    )
  }

  override fun onInternetConnectionChanged(
    connected: Boolean?
  ) {
    return if (connected!!)
      showOrHideNetworkBanner(
          connected
      ) else showOrHideNetworkBanner(
        connected
    )
  }

  private fun showOrHideNetworkBanner(
    available: Boolean?
  ) {
    val enable: Boolean? = null
    val banner = binding!!
        .network.networkParent
    if (!available!!) {
      enable.let {
        true
      }
      Timber.i("Network status: %s", available)
      AppExecutors(100)
          .threadDelay()
          .execute {
            banner.visibility = View.VISIBLE
          }
    } else {
      enable.let {
        false
      }
      banner.visibility = View.GONE
    }

    banner.animation = enable?.let {
      animate(it, context!!)
    }

    // refresh UI state
    binding!!.executePendingBindings()
    binding!!.invalidateAll()
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
