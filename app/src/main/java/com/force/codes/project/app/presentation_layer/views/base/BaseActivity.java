/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.presentation_layer.views.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.force.codes.project.app.BuildConfig;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.StatisticsFragment;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public abstract class BaseActivity extends DaggerAppCompatActivity {
  private Fragment fragment = null;

  private FragmentManager fragmentManager;

  protected FragmentTransaction transaction;

  protected static boolean IS_FRESH_INSTALL = false;


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final String SHARED_PREF = "Shared_Pref";
    final String PREF_VERSION_CODE_KEY = "version_code";
    final int NOT_EXIST = -1;
    final int versionCode = BuildConfig.VERSION_CODE;

    Schedulers.io().createWorker().schedule(() -> {
      final SharedPreferences pref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
      int saveVersionCode = pref.getInt(PREF_VERSION_CODE_KEY, NOT_EXIST);

      if (versionCode == saveVersionCode) {
        return;
      } else if (saveVersionCode == NOT_EXIST) {
        Timber.e("First run");
        IS_FRESH_INSTALL = true;
      } else if (versionCode > saveVersionCode) {
        Timber.e("Upgrade");
      }
      pref.edit().putInt(PREF_VERSION_CODE_KEY, versionCode).apply();
    });
  }

  @NotNull
  public FragmentTransaction setFragment(Fragment fragment) {
    fragmentManager = getSupportFragmentManager();
    transaction = fragmentManager.beginTransaction();

    if (fragment == null) {
      fragment = StatisticsFragment.newInstance();
      return transaction.replace(R.id.fragment_container, fragment,
          fragment.getClass().getSimpleName());
    }

    return transaction.replace(R.id.fragment_container, fragment, fragment.getTag())
        .hide(fragment).show(fragment);
  }

  public Fragment getFragment() {
    return fragment;
  }

  public FragmentManager getFragManager() {
    return fragmentManager;
  }

}
