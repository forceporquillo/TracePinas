/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.bottombar;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 11:33 AM
 *
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.force.codes.project.app.app.di.module.ViewModelProviderFactory;
import com.force.codes.project.app.databinding.FragmentLiveDataBinding;
import com.force.codes.project.app.presentation_layer.views.base.BaseFragment;

import com.force.codes.project.app.presentation_layer.views.viewmodels.LiveDataViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class LiveDataFragment extends BaseFragment {

  protected FragmentLiveDataBinding binding;

  @Inject
  ViewModelProviderFactory factory;

  private LiveDataViewModel viewModel;

  public LiveDataFragment() {
    // Required empty public constructor
  }

  public static LiveDataFragment newInstance() {
    LiveDataFragment fragment = new LiveDataFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = new ViewModelProvider(this, factory).get(LiveDataViewModel.class);
  }

  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentLiveDataBinding.inflate(inflater, container, false);
    binding.setLifecycleOwner(this);
    binding.setViewModel(viewModel);
    return binding.getRoot();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  @Override
  public void onStart() {
    super.onStart();
    //        viewModel.getDataFromNetwork().observe(this, worldData ->
    //                Timber.d(String.valueOf(worldData.getCases())));
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onNetworkConnectionChanged(Connectivity connectivity) {

  }

  @Override public void onInternetConnectionChanged(Boolean isConnected) {

  }
}
