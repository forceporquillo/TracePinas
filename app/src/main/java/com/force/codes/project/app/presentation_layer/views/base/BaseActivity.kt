package com.force.codes.project.app.presentation_layer.views.base

import android.content.SharedPreferences
import android.os.Bundle
import com.force.codes.project.app.BuildConfig
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

const val SHARED_PREF: String = "SHARED_PREF"
const val PREF_VERSION_CODE_KEY = "VERSION_CODE"
const val NOT_EXIST = -1
const val VERSION_CODE = BuildConfig.VERSION_CODE

abstract class BaseActivity : DaggerAppCompatActivity() {

  companion object {
    @JvmStatic var isFreshInstalled: Boolean = false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    savedInstanceState?.let {
      checkIfFreshInstall()
    }
  }

  private fun checkIfFreshInstall() {
    ThreadExecutor(0).diskIO().execute {
      val pref: SharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
      val currentSaveVersion = pref.getInt(PREF_VERSION_CODE_KEY, NOT_EXIST)

      when {
        VERSION_CODE == currentSaveVersion -> {
          return@execute
        }
        currentSaveVersion == NOT_EXIST -> {
          Timber.e("First run")
          isFreshInstalled = true
        }
        VERSION_CODE > currentSaveVersion -> {
          Timber.e("Upgrade")
        }
      }
      pref.edit()
          .putInt(PREF_VERSION_CODE_KEY, VERSION_CODE)
          .apply()
    }
  }
}