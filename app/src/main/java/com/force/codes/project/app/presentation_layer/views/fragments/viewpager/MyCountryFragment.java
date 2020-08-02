/*
 * Created by Force Porquillo on 7/17/20 2:16 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 2:16 PM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

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
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.FragmentMyCountryBinding;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MyCountryViewModel;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCountryFragment extends DaggerFragment {
  private FragmentMyCountryBinding binding;
  private MyCountryViewModel viewModel;

  @Inject ViewModelProviderFactory factory;

  public MyCountryFragment() {
    // Required empty public constructor
  }

  public static MyCountryFragment newInstance(String param1, String param2) {
    MyCountryFragment fragment = new MyCountryFragment();
    Bundle args = new Bundle();
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
    binding.setMyCountry(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.myCountry, this);
    binding.executePendingBindings();
    return binding.getRoot();
  }

  @Override public void onStart() {
    super.onStart();
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setSpinner();
    setPieChart();
  }

  private List<String> spinnerList= new ArrayList<>(Arrays.asList(
      "Philippines", "China", "USA", "Philippines", "China", "USA", "Philippines", "China", "USA",
      "Philippines", "China", "USA", "Philippines", "China", "USA", "Philippines", "China", "USA",
      "Philippines", "China", "USA", "Philippines", "China", "USA", "Philippines", "China", "USA",
      "Philippines", "China", "USA", "Philippines", "China", "USA", "Philippines", "China", "USA",
      "Philippines", "China", "USA", "Philippines", "China", "USA", "Philippines", "China", "USA"));

  private void setSpinner(){
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  private void setPieChart() {
    AnimatedPieViewConfig config = new AnimatedPieViewConfig();

    config.strokeWidth(70);
    config.startAngle(-90)
        .addData(new SimplePieInfo(500, color(50, 120, 210), "Cases"))
        .addData(new SimplePieInfo(370, color(255, 93, 93), "Deaths"))
        .addData(new SimplePieInfo(200, color(88, 197, 30), "Recovered"));
    binding.circlePie.start(config);
    binding.invalidateAll();
  }

  private static int color(int r, int g, int b){
    return Color.rgb(r, g, b);
  }
}