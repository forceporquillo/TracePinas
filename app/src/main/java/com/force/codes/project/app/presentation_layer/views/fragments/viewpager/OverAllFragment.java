/*
 * Created by Force Porquillo on 7/17/20 3:29 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 3:29 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.app.di.module.ViewModelProviderFactory;
import com.force.codes.project.app.databinding.FragmentOverAllBinding;
import com.force.codes.project.app.presentation_layer.views.adapters.OverAllAdapter;
import com.force.codes.project.app.presentation_layer.views.base.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.viewmodels.OverAllViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OverAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverAllFragment extends BaseFragment {


  @Inject
  ViewModelProviderFactory factory;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private OverAllViewModel viewModel;
  private RecyclerView recyclerView;

  public OverAllFragment() {
    // Required empty public constructor
  }

  public static OverAllFragment newInstance(String param1, String param2) {
    OverAllFragment fragment = new OverAllFragment();
    Bundle args = new Bundle();

    fragment.setArguments(args);
    return fragment;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = new ViewModelProvider(this, factory).get(OverAllViewModel.class);
    //viewModel.streamIterate();
    //viewModel.getTotalByDate();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setRecyclerView(view);
  }

  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final FragmentOverAllBinding binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_over_all, container, false);
    binding.setOverall(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.overall, this);
    binding.executePendingBindings();
    attachViewBindings(binding);
    return binding.getRoot();
  }

  @Override
  public void onStart() {
    super.onStart();
    recyclerView.setAdapter(new OverAllAdapter());
  }

  private void setRecyclerView(View view) {
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemViewCacheSize(20);
  }

  /**
   * we bind to new separate object view to avoid writing long boilerplate code.
   */

  private void attachViewBindings(final FragmentOverAllBinding binding) {
    recyclerView = binding.overAllRecyclerview;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    recyclerView = null;
  }

  @Override
  public void onNetworkConnectionChanged(Connectivity connectivity) {

  }

  @Override public void onInternetConnectionChanged(Boolean isConnected) {

  }
}