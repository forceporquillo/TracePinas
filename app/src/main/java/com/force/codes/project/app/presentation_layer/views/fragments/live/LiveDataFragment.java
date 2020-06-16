package com.force.codes.project.app.presentation_layer.views.fragments.live;

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

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.app.Injection;
import com.force.codes.project.app.databinding.FragmentLiveDataBinding;
import com.force.codes.project.app.factory.LiveDataViewModelFactory;
import com.force.codes.project.app.service.executors.AppExecutors;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiveDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveDataFragment extends Fragment{

    private LiveDataViewModel dataViewModel;

    public LiveDataFragment(){
        // Required empty public constructor
    }

    protected FragmentLiveDataBinding binding;

    public static LiveDataFragment newInstance(){
        LiveDataFragment fragment = new LiveDataFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        LiveDataViewModelFactory modelFactory = Injection.providesViewModelFactory(new AppExecutors());

        dataViewModel = new ViewModelProvider(this, modelFactory)
                .get(LiveDataViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentLiveDataBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(dataViewModel);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding.unbind();
        binding = null;
    }

    @Override
    public void onStart(){
        super.onStart();
        dataViewModel.getDataFromNetwork().observe(this, worldData ->
                Timber.d(String.valueOf(worldData.getCases())));
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
