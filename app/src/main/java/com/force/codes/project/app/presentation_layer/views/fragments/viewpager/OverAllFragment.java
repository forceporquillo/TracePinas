/*
 * Created by Force Porquillo on 7/17/20 3:29 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 3:29 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.FragmentOverAllBinding;
import com.force.codes.project.app.presentation_layer.views.adapters.OverAllAdapter;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OverAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverAllFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OverAllFragment(){
        // Required empty public constructor
    }

    private FragmentOverAllBinding binding;

    public static OverAllFragment newInstance(String param1, String param2){
        OverAllFragment fragment = new OverAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView(view);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_over_all, container, false);
        binding.setOverall(this);
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.overall, this);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onStart(){
        super.onStart();
        binding.overAllRecyclerview.setAdapter(new OverAllAdapter());
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    private void setRecyclerView(View view){
        binding.overAllRecyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.overAllRecyclerview.setHasFixedSize(true);
    }
}