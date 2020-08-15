/*
 * Created by Force Porquillo on 6/10/20 2:03 AM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.presentation_layer.views.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkCallback;
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkUtils;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.CountryListFragment;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment implements NetworkCallback {
  private Fragment fragment = null;
  private NetworkUtils networkUtils;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    networkUtils = new NetworkUtils(getContext());
  }

  protected NetworkUtils getNetworkUtils() {
    return networkUtils;
  }

  protected Fragment getFragment(){
    return fragment;
  }

  protected FragmentTransaction setFragment(Fragment fragment) {
    FragmentManager fragmentManager = getParentFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();

    if (fragment instanceof CountryListFragment) {
      transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_down_out);
      return transaction.add(R.id.my_country_fragment, fragment).
          addToBackStack(fragment.getTag());
    } else {
      transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
          R.anim.enter_from_left, R.anim.exit_to_right);

      return transaction.replace(R.id.fragment_container, fragment)
          .addToBackStack(fragment.getTag());
    }
  }
}
