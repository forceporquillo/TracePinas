package com.force.codes.project.app.presentation_layer.controller.navigation

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.force.codes.project.app.R.anim
import com.force.codes.project.app.R.drawable
import com.force.codes.project.app.R.id
import com.force.codes.project.app.R.string
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.HelpCenterFragment
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.LiveDataFragment
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.MapFragment
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.NewsFragment
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.StatisticsFragment.Companion.newInstance
import timber.log.Timber
import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap

class BottomNavigationView internal constructor() {
  class BottomBarDrawableArray internal constructor() {
    companion object {
      fun getFragmentIds(context: Context): Array<String> {
        val resources = context.resources
        return arrayOf(
            resources.getString(string.statistics),
            resources.getString(string.news),
            resources.getString(string.map),
            resources.getString(string.help),
            resources.getString(string.facilities))
      }

      val DRAWABLE_ICONS =
        arrayOf(intArrayOf(drawable.ic_stats, drawable.ic_fill_stats), intArrayOf(
            drawable.ic_news, drawable.ic_fill_news),
            intArrayOf(drawable.ic_map, drawable.ic_fill_map), intArrayOf(
            drawable.ic_heart, drawable.ic_fill_heart), intArrayOf(
            drawable.ic_phone, drawable.ic_fill_phone))
      val fragStackList: List<Fragment>
        get() = ArrayList(Arrays.asList(
            newInstance(),
            NewsFragment.newInstance(true),
            MapFragment.newInstance(),
            LiveDataFragment.newInstance(),
            HelpCenterFragment.newInstance()
        ))
    }

    init {
      throw AssertionError("No instance required")
    }
  }

  companion object {
    private val FRAGMENT_LOOKUP_MAP = HashMap<Int, Fragment>()
    private var SUPPORT_FRAGMENT_MANAGER: FragmentManager? = null
    private var FRAGMENT_INDEX = 0
    fun setSupportFragmentManager(supportFragmentManager: FragmentManager?) {
      SUPPORT_FRAGMENT_MANAGER = supportFragmentManager
    }

    fun setDelegateFragment(
      fragment: Fragment?,
      itemIndex: Int,
    ) {
      fragment?.let {
        val fragmentTag = it.javaClass.simpleName
        val fragmentTransaction = SUPPORT_FRAGMENT_MANAGER!!.beginTransaction()
        val delegateFragment = arrayOf(
            SUPPORT_FRAGMENT_MANAGER!!.primaryNavigationFragment,
            SUPPORT_FRAGMENT_MANAGER!!.findFragmentByTag(fragmentTag)
        )
        FRAGMENT_LOOKUP_MAP[itemIndex] = it
        if (FRAGMENT_INDEX > itemIndex) {
          fragmentTransaction.setCustomAnimations(anim.enter_from_left,
              anim.exit_to_right, anim.enter_from_right, anim.exit_to_left
          )
        } else {
          fragmentTransaction.setCustomAnimations(anim.enter_from_right,
              anim.exit_to_left, anim.enter_from_left, anim.exit_to_right
          )
        }
        FRAGMENT_INDEX = itemIndex
        if (delegateFragment[0] != null) {
          fragmentTransaction.hide(delegateFragment[0]!!)
        }
        if (delegateFragment[1] == null) {
          delegateFragment[1] = fragment
          fragmentTransaction.add(id.fragment_container,
              delegateFragment[1]!!, fragmentTag)
        } else fragmentTransaction.show(delegateFragment[1]!!)

        fragmentTransaction.apply {
          setPrimaryNavigationFragment(delegateFragment[1])
          setReorderingAllowed(true)
          commitAllowingStateLoss()
          return@apply
        }
      }
    }
  }

  init {
    throw AssertionError("No instance required")
  }
}