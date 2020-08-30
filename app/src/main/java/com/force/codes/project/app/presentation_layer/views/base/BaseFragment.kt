/*
 * Created by Force Porquillo on 6/10/20 2:03 AM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */
package com.force.codes.project.app.presentation_layer.views.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.force.codes.project.app.R
import com.force.codes.project.app.R.anim
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkCallback
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkUtils
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.CountryListFragment
import dagger.android.support.DaggerFragment

abstract class BaseFragment :
    DaggerFragment(), NetworkCallback {
  protected val fragment: Fragment? = null
  protected var networkUtils: NetworkUtils? = null
    private set

  override fun onCreate(
    savedInstanceState: Bundle?,
  ) {
    super.onCreate(savedInstanceState)
    networkUtils = NetworkUtils(context)
  }

  protected fun setFragment(
    fragment: Fragment,
  ): FragmentTransaction {
    val fragmentManager = parentFragmentManager
    val transaction = fragmentManager.beginTransaction()

    return if (fragment is CountryListFragment) {
      transaction.setCustomAnimations(anim.slide_in_up, anim.slide_down_out)
      transaction.add(R.id.my_country_fragment, fragment)
          .addToBackStack(fragment.getTag())
    } else {
      transaction.setCustomAnimations(
          anim.enter_from_right, anim.exit_to_left,
          anim.enter_from_left, anim.exit_to_right
      )
      transaction.replace(R.id.fragment_container, fragment)
          .addToBackStack(fragment.tag)
    }
  }
}