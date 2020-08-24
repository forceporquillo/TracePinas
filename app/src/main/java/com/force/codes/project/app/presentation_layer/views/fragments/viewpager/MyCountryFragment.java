/*
 * Created by Force Porquillo on 7/17/20 2:16 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 2:16 PM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.FragmentMyCountryBinding;
import com.force.codes.project.app.presentation_layer.controller.service.AppExecutors;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import com.force.codes.project.app.presentation_layer.views.activity.ListViewActivity;
import com.force.codes.project.app.presentation_layer.views.base.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MyCountryViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCountryFragment extends BaseFragment
    implements StackEventListener.ListActivityListener {
  private static final String ARGS_KEY = "country";
  private static final String DEFAULT_ENDPOINT = "Philippines";
  private FragmentMyCountryBinding binding;
  private MyCountryViewModel viewModel;
  private String getArgsKey = null;

  public MyCountryFragment() {
    // Required empty public constructor
  }

  @Inject ViewModelProviderFactory factory;

  public static MyCountryFragment newInstance() {
    MyCountryFragment fragment = new MyCountryFragment();
    Bundle args = new Bundle();
    args.putString(ARGS_KEY, "");
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      viewModel = new ViewModelProvider(this, factory).get(MyCountryViewModel.class);
    }
  }

  @Override public View
  onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMyCountryBinding.inflate(inflater, container, false);
    binding.setCountryViewModel(viewModel);
    binding.setLifecycleOwner(this);
    binding.setListCallback(this);
    renderGridBoxAtRuntime(container.getLayoutParams());
    return binding.getRoot();
  }

  @Override public void onStart() {
    super.onStart();
    getArgsKey = getArgsKey == null ? DEFAULT_ENDPOINT : getArgsKey;
    viewModel.getPrimarySelected().observe(this, country ->
        Timber.i("LiveData auto update UI emits: %s", getArgsKey = country)
    );
    new AppExecutors(100).mainThread().execute(() ->
        viewModel.getCountryData(getArgsKey).observe(this, data ->
            setPieChart(data, getArgsKey.equals(DEFAULT_ENDPOINT))
        )
    );
    binding.invalidateAll();
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (binding.hasPendingBindings()) {
      binding.executePendingBindings();
    }
  }

  private void renderGridBoxAtRuntime(ViewGroup.LayoutParams params) {
    if (getContext() == null) {
      throw new NullPointerException();
    }
    final int deviceWidth = Utils.getDeviceWidth(getContext());
    params.resolveLayoutDirection(0);
    for (int i = 0; i < boxLayout(binding).length; ++i) {
      params = boxLayout(binding)[i].getLayoutParams();
      if (i % 2 != 0) {
        params.width = getBoxWidth(deviceWidth, 10);
      } else {
        params.width = getBoxWidth(deviceWidth, 20);
      }
      boxLayout(binding)[i].setLayoutParams(params);
    }
  }

  final int getBoxWidth(final int deviceWidth, final int width) {
    return (deviceWidth / 2) - Utils.dpToPx(getContext(),
        width, true);
  }

  @Override public void onStartListViewActivity() {
    startActivity(new Intent(getActivity(), ListViewActivity.class));
  }

  private void setPieChart(final CountryDetails details, boolean animate) {
    final AnimatedPieViewConfig config = new AnimatedPieViewConfig();
    config.strokeWidth(70);
    config.animatePie(animate);
    config.startAngle(-90)
        .addData(new SimplePieInfo(
            details.getCases(),
            Color.rgb(50, 120, 210),
            "Cases")
        )
        .addData(new SimplePieInfo(
            details.getDeaths(),
            Color.rgb(255, 93, 93),
            "Deaths")
        )
        .addData(new SimplePieInfo(
            details.getRecovered(),
            Color.rgb(88, 197, 30),
            "Recovered")
        );

    //binding.circlePie.start(config);
    binding.invalidateAll();
  }

  private static RelativeLayout[] boxLayout(FragmentMyCountryBinding binding) {
    return new RelativeLayout[] {
        binding.containerDeaths,
        binding.containerInfected,
        binding.containerRecovered,
        binding.containerTested
    };
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  private void calculatePercentage() {

  }

  @Override public void onNetworkConnectionChanged(Connectivity connectivity) {

  }

  @Override
  public void onInternetConnectionChanged(Boolean connected) {

  }
}