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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.FragmentMyCountryBinding;
import com.force.codes.project.app.presentation_layer.views.activity.list_component.ListViewActivity;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MyCountryViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCountryFragment extends BaseFragment {
  private static final String ARGS_KEY = "country";

  private FragmentMyCountryBinding binding;
  private MyCountryViewModel viewModel;
  private String[] getArgsKey = new String[3];

  @Inject ViewModelProviderFactory factory;

  public MyCountryFragment() {
    // Required empty public constructor
  }

  public static MyCountryFragment setCallback(String country) {
    MyCountryFragment fragment = new MyCountryFragment();
    Bundle args = new Bundle();
    System.out.println("Item " + country);
    args.putString(ARGS_KEY, country);
    fragment.setArguments(args);
    return fragment;
  }

  public static MyCountryFragment newInstance() {
    return new MyCountryFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      getArgsKey[0] = "philippines";
      viewModel = new ViewModelProvider(this, factory).get(MyCountryViewModel.class);
    }
  }

  @Override public View
  onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMyCountryBinding.inflate(inflater, container, false);
    binding.setMyCountry(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.myCountry, this);
    binding.executePendingBindings();
    return binding.getRoot();
  }

  @Override public void onStart() {
    super.onStart();
    viewModel.getPrimarySelected().observe(this, country -> {
      if (!country.isEmpty()) {
        for (int i = 0; i < getArgsKey.length - 1; ++i) {
          if(i == 0) {
            getArgsKey[i] = country.replaceAll(
                "\\s+","")
                .toLowerCase();
          }
          getArgsKey[i] = country;
        }
      } else{
        getArgsKey[0] = "philippines";
        getArgsKey[1] = "Philippines";
      }
    });

    viewModel.getCountryData(getArgsKey[0]).observe(this, data ->
        setPieChart(data, getArgsKey[0].equals(getArgsKey[3])));

    binding.spinnerTitle.setText(getArgsKey[1]);
    binding.invalidateAll();
  }
  
  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel.getListOfCountries().observe(getViewLifecycleOwner(), this::setCustomSpinner);
  }

  public void setCustomSpinner(List<CountryDetails> countryDetails) {
    assert getActivity() != null;
    binding.customSpinner.setOnClickListener(v ->
        startActivity(new Intent(getActivity(), ListViewActivity.class))
    );
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  private void setPieChart(final CountryDetails countryDetails, boolean animate) {
    AnimatedPieViewConfig config = new AnimatedPieViewConfig();
    setStats(countryDetails);
    config.strokeWidth(70);
    config.animatePie(animate);
    config.startAngle(-90)
        .addData(new SimplePieInfo(countryDetails.getCases(), color(50, 120, 210), "Cases"))
        .addData(new SimplePieInfo(countryDetails.getDeaths(), color(255, 93, 93), "Deaths"))
        .addData(
            new SimplePieInfo(countryDetails.getRecovered(), color(88, 197, 30), "Recovered"));
    binding.circlePie.start(config);
    binding.invalidateAll();
  }

  private void setStats(CountryDetails data) {
  }

  private static int color(int r, int g, int b) {
    return Color.rgb(r, g, b);
  }

  @Override public void onNetworkConnectionChanged(Connectivity connectivity) {

  }

  @Override
  public void onInternetConnectionChanged(Boolean connected) {

  }
}