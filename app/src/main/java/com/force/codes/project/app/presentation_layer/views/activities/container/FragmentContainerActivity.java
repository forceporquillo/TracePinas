package com.force.codes.project.app.presentation_layer.views.activities.container;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.BottomItemListener;
import com.force.codes.project.app.presentation_layer.controller.custom.model.BottomItem;
import com.force.codes.project.app.presentation_layer.controller.support.CustomBottomBar;
import com.force.codes.project.app.presentation_layer.views.activities.BaseActivity;
import com.force.codes.project.app.presentation_layer.views.fragments.favorites.HelpCenterFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.home.HomeFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.mapview.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.newsfeed.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.worldwide.WorldwideFragment;

import org.jetbrains.annotations.NotNull;

import leakcanary.AppWatcher;
import leakcanary.ObjectWatcher;
import timber.log.Timber;

public class FragmentContainerActivity extends BaseActivity implements BottomItemListener{
    private static final String SAVE_FRAGMENT_STATE = "save_fragment_state";
    private static final int STATISTICS = 0;
    private static final int NEWS = 1;
    private static final int WORLDWIDE = 2;
    private static final int MAP = 3;
    private static final int HELP = 4;

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private Fragment delegateFrag = null;

    public FragmentContainerActivity(){

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        if(fragment != null)
            getSupportFragmentManager()
                    .putFragment(outState, SAVE_FRAGMENT_STATE, fragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);
        CustomBottomBar bottomBar = new CustomBottomBar(findViewById(R.id.bottom_bar), this, this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }



        if(savedInstanceState != null)
            fragment =

                    getSupportFragmentManager()
                            .

                                    getFragment(savedInstanceState, SAVE_FRAGMENT_STATE);

        setPrimaryFragment(savedInstanceState);

        setBottomBarItems(bottomBar);

    }

    final void setPrimaryFragment(Bundle savedInstanceState){
        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment.newInstance())
                    .addToBackStack(HomeFragment.class.getSimpleName())
                    .commit();
        }
    }

    final void setBottomBarItems(CustomBottomBar bottomBar){
        final BottomItem[] bottomItems = new BottomItem[5];

        bottomItems[0] = new BottomItem(STATISTICS, "Statistics", R.drawable.ic_outline_stats, R.drawable.ic_outline_colored_stats);
        bottomItems[1] = new BottomItem(NEWS, "News", R.drawable.ic_outline_news, R.drawable.ic_outline_colored_news);
        bottomItems[2] = new BottomItem(WORLDWIDE, "Worldwide", R.drawable.ic_outline_worldwide, R.drawable.ic_outline_colored_worldwide);
        bottomItems[3] = new BottomItem(MAP, "Map", R.drawable.ic_outline_map, R.drawable.ic_outline_colored_map);
        bottomItems[4] = new BottomItem(HELP, "Help", R.drawable.ic_outline_phone, R.drawable.ic_outline_colored_phone);

        bottomBar.addBottomItem(bottomItems);
        bottomBar.setPrimary(STATISTICS);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void itemSelect(int itemId){
        switch(itemId){
            case STATISTICS:
                fragment = HomeFragment.newInstance();
                break;
            case NEWS:
                fragment = NewsFragment.newInstance();
                break;
            case WORLDWIDE:
                fragment = WorldwideFragment.newInstance();
                break;
            case MAP:
                fragment = MapFragment.newInstance();
                break;
            case HELP:
                fragment = HelpCenterFragment.newInstance();
                break;
        }

        delegateFrag = fragment;

        setDelegateFragment(fragment).commit();
    }


    @NotNull
    private FragmentTransaction setDelegateFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        return transaction.replace(R.id.fragment_container, fragment).addToBackStack(null);
    }

    @Override
    public void onBackPressed(){
        if(getSupportFragmentManager().getBackStackEntryCount() > 1)
            super.onBackPressed();
        else
            finish();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}