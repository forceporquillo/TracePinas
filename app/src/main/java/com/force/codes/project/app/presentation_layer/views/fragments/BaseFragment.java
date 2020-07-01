/*
 * Created by Force Porquillo on 6/10/20 2:03 AM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

package com.force.codes.project.app.presentation_layer.views.fragments;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.force.codes.project.app.R;

public abstract class BaseFragment extends Fragment{
    public FragmentTransaction setDelegateFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(fragment instanceof StatisticsFragment){
            transaction.setCustomAnimations(
                    R.anim.enter_from_right, R.anim.exit_to_left,
                    R.anim.enter_from_left, R.anim.exit_to_right);
        }

        return transaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(fragment.getTag());
    }
}
