/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.presentation_layer.views.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.StatisticsFragment;
import dagger.android.support.DaggerAppCompatActivity;
import org.jetbrains.annotations.NotNull;

public abstract class BaseActivity extends DaggerAppCompatActivity {
  private Fragment fragment = null;
  private FragmentManager fragmentManager;
  protected FragmentTransaction transaction;

  public Fragment getFragment() {
    return fragment;
  }

  public FragmentManager getFragManager() {
    return fragmentManager;
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
}
