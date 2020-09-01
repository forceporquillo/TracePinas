package com.force.codes.project.app.presentation_layer.views.base

import android.content.SharedPreferences
import android.os.Bundle
import com.force.codes.project.app.BuildConfig
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

abstract class BaseActivity : DaggerAppCompatActivity() {

  companion object {
    private var isFreshInstalled: Boolean = false
    private const val SHARED_PREF: String = "SHARED_PREF"
    private const val PREF_VERSION_CODE_KEY = "VERSION_CODE_START"
    private const val NOT_EXIST = -1

    @JvmStatic val checkIfFirstRun
      get() = isFreshInstalled
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    checkIfFreshInstalled()
  }

  private fun checkIfFreshInstalled() {
    ThreadExecutor(0).diskIO()
        .execute {
          val pref: SharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
          val saveVersionCode = pref.getInt(PREF_VERSION_CODE_KEY, NOT_EXIST)

          when {
            BuildConfig.VERSION_CODE == saveVersionCode -> {
              Timber.e("normal run")
              return@execute
            }
            saveVersionCode == NOT_EXIST -> {
              Timber.e("app is freshly installed")
              isFreshInstalled = true
            }
            BuildConfig.VERSION_CODE > saveVersionCode -> {
              Timber.e("app upgraded")
            }
          }
          pref.edit()
              .putInt(PREF_VERSION_CODE_KEY, BuildConfig.VERSION_CODE)
              .apply()
        }
  }

}