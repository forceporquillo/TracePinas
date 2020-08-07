/*
 * Created by Force Porquillo on 8/8/20 1:33 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 1:33 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryListFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public CountryListFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment CountryListFragment.
   * @param countryDetails
   */
  // TODO: Rename and change types and number of parameters
  public static CountryListFragment newInstance(
      List<CountryDetails> countryDetails) {
    CountryListFragment fragment = new CountryListFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_country_list, container, false);
    return view;
  }
}