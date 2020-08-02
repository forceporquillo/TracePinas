/*
 * Created by Force Porquillo on 6/10/20 2:03 AM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */
package com.force.codes.project.app.presentation_layer.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.force.codes.project.app.R
import com.force.codes.project.app.R.anim
import com.force.codes.project.app.presentation_layer.controller.utils.network.NetworkCallback
import com.force.codes.project.app.presentation_layer.controller.utils.network.NetworkUtils
import dagger.android.support.DaggerFragment

abstract class BaseFragment :
    DaggerFragment(), NetworkCallback {
  protected val fragment: Fragment? = null
  protected var networkUtils: NetworkUtils? = null

  override fun onCreate(
    savedInstanceState: Bundle?
  ) {
    super.onCreate(savedInstanceState)
    networkUtils = NetworkUtils(context)
  }

  protected fun setFragment(
    fragment: Fragment
  ): FragmentTransaction {
    val fragmentManager = parentFragmentManager
    val transaction = fragmentManager.beginTransaction()

    transaction.setCustomAnimations(
        anim.enter_from_right, anim.exit_to_left,
        anim.enter_from_left, anim.exit_to_right
    )
    return transaction.replace(
        R.id.fragment_container, fragment
    )
        .addToBackStack(fragment.tag)
  }
}