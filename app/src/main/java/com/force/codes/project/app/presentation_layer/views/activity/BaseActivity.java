/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.presentation_layer.views.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.fragments.StatisticsFragment;

import org.jetbrains.annotations.NotNull;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity{
    private Fragment fragment = null;
    private FragmentManager fragmentManager;

    public Fragment getFragment(){
        return fragment;
    }

    public FragmentManager getFragManager(){
        return fragmentManager;
    }

    @NotNull
    public  FragmentTransaction setFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(fragment == null){
            fragment = StatisticsFragment.newInstance();
            return transaction.replace(R.id.fragment_container, fragment,
                    fragment.getClass().getSimpleName());
        }

        return transaction.replace(R.id.fragment_container, fragment)
                .hide(fragment).show(fragment);
    }
}
