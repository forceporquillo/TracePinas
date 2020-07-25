/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments;

/*
 * Created by Force Porquillo on 6/4/20 9:30 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 6:19 AM
 *
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.force.codes.project.app.databinding.FragmentCountryStatisticsBinding;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.MyCountryFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.OverAllFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.WorldwideFragment;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment{
    private FragmentPagerItemAdapter adapter;
    private FragmentCountryStatisticsBinding binding;

    public StatisticsFragment(){
        // Required empty public constructor
    }

    public static StatisticsFragment newInstance(){
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            adapter = new FragmentPagerItemAdapter(getChildFragmentManager(),
                    FragmentPagerItems.with(getContext())
                            .add("Overall Cases", OverAllFragment.class)
                            .add("My Country", MyCountryFragment.class)
                            .add("All Countries", WorldwideFragment.class)
                            .create()
            );
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentCountryStatisticsBinding.inflate(inflater, container, false);
        binding.setStatistics(this);
        binding.setLifecycleOwner(this);
        binding.invalidateAll();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.viewpager.setAdapter(adapter);
        binding.tablayout.setViewPager(binding.viewpager);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding.unbind();
        binding = null;
        adapter = null;
    }
}
