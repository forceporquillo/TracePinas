package com.force.codes.project.app.presentation_layer.views.fragments.favorites;

/*
 * Created by Force Porquillo on 6/11/20 12:22 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;

public class HelpCenterFragment extends BaseFragment{

    private HelpCenterViewModel mViewModel;

    public static HelpCenterFragment newInstance(){
        return new HelpCenterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.favorites_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HelpCenterViewModel.class);
        // TODO: Use the ViewModel
    }

}