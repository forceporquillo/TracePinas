/*
 * Created by Force Porquillo on 5/9/20 8:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/29/20 6:37 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.force.codes.project.app.R;
import com.force.codes.project.app.app.di.module.ViewModelProviderFactory;
import com.force.codes.project.app.databinding.FragmentWorldwideBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor;
import com.force.codes.project.app.presentation_layer.views.adapters.CountryAdapter;
import com.force.codes.project.app.presentation_layer.views.base.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.HelpCenterFragment;
import com.force.codes.project.app.presentation_layer.views.viewmodels.WorldwideViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class WorldwideFragment extends BaseFragment implements
    StackEventListener.OnGetAdapterPosition, View.OnClickListener,
    StackEventListener.LiveStateResponse {

  @Inject
  ViewModelProviderFactory factory;
  @Inject
  ThreadExecutor threadExecutor;

  private FragmentWorldwideBinding binding;

  private WorldwideViewModel viewModel;

  private CountryAdapter countryAdapter;

  public WorldwideFragment() {

  }

  public static Fragment newInstance() {
    return new WorldwideFragment();
  }

  @Override
  public void onInternetConnectionChanged(Boolean isConnected) {
    if (!isConnected) {
      swipeRefreshLayout().setEnabled(false);
    } else {
      swipeRefreshLayout().setEnabled(true);
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    viewModel.getDataFromDatabase().observe(this, countryDetails -> {
      if (countryDetails.isEmpty()) {
        viewModel.getDataFromNetwork();
      } else {
        countryAdapter.submitList(countryDetails);
        binding.recyclerView.setEnabled(true);
        binding.swipeFresh.setRefreshing(false);
        binding.shimmerLayout.stopShimmer();
        binding.recyclerView.setAdapter(countryAdapter);
      }
    });
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //connectivity = new NetworkConnectivity(this);
    countryAdapter = new CountryAdapter(this);
    viewModel = new ViewModelProvider(this, factory).get(WorldwideViewModel.class);
    viewModel.getDataFromNetwork();
  }

  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup
      container, Bundle savedInstanceState) {
    binding = FragmentWorldwideBinding.inflate(inflater, container, false);
    binding.invalidateAll();
    binding.setViewModel(viewModel);
    binding.setLifecycleOwner(this);
    setRetainInstance(true);
    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    swipeRefreshLayout().setEnabled(false);
    setRecyclerView();
    binding.shimmerLayout.startShimmer();
  }

  private SwipeRefreshLayout swipeRefreshLayout() {
    binding.swipeFresh.setColorSchemeResources(R.color.blue, R.color.blue, R.color.blue);
    binding.swipeFresh.setOnRefreshListener(() -> viewModel.getDataFromNetwork());
    return binding.swipeFresh;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  @Override
  public void onClick(View v) {
    Fragment fragment = HelpCenterFragment.newInstance();
    super.setFragment(fragment).commit();
  }

  final void setRecyclerView() {
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.recyclerView.addItemDecoration(new DividerItemDecoration(
        binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    binding.recyclerView.setHasFixedSize(true);
    binding.recyclerView.setItemViewCacheSize(20);
    binding.recyclerView.setAdapter(countryAdapter);
  }

  @Override
  public void onNetworkConnectionChanged(Connectivity connectivity) {

  }

  @Override public void onItemClicked(int index) {
    //Fragment fragment = StatisticsFragment.newInstance();
  }

  @Override public void onErrorResponse(boolean isError) {
    if (isError) {
      swipeRefreshLayout().setRefreshing(false);
    }
  }
}