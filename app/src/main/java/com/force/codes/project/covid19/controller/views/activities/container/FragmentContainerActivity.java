package com.force.codes.project.covid19.controller.views.activities.container;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.force.codes.project.covid19.BuildConfig;
import com.force.codes.project.covid19.R;
import com.force.codes.project.covid19.controller.views.fragments.global.GlobalFragment;
import com.force.codes.project.covid19.controller.views.fragments.home.HomeFragment;
import com.force.codes.project.covid19.controller.views.fragments.live.LiveDataFragment;
import com.force.codes.project.covid19.controller.views.fragments.map.MapFragment;
import com.force.codes.project.covid19.controller.views.fragments.newsfeed.NewsFragment;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.TypeCastException;
import nl.joery.animatedbottombar.AnimatedBottomBar;
import timber.log.Timber;

public class FragmentContainerActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{
    private static final String SAVE_FRAGMENT_STATE = "fragment";

    @BindView(R.id.animatedBottomBar)
    AnimatedBottomBar animatedBottomBar;

    private FragmentManager fragmentManager;
    private Fragment fragment = null;

    @Override
    protected void onStart(){
        super.onStart();
        setBottomBarNavigation();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        if(fragment != null)
            getSupportFragmentManager().putFragment(outState, SAVE_FRAGMENT_STATE, fragment);
    }

    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);
        ButterKnife.bind(this);

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

        if(Build.VERSION.SDK_INT <= 23){
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }

        setDefaultFragment(savedInstanceState);

        if(savedInstanceState != null){
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, SAVE_FRAGMENT_STATE);
        }
    }


    private void setDefaultFragment(Bundle savedInstanceState){
        if(savedInstanceState == null){
            animatedBottomBar.selectTabById(R.id.tab_home, true);
            mapOfFragments.add("Home");
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment.newInstance())
                    .addToBackStack("HomeFragment")
                    .commit();
        }
    }

    HashSet<String> mapOfFragments = new HashSet<>();
    int fragId;

    private void setBottomBarNavigation(){
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener(){
            @Override
            public void onTabSelected(int lastIndex, AnimatedBottomBar.Tab lastTab, int newIndex, @NotNull AnimatedBottomBar.Tab newTab){
                switch(newTab.getId()){
                    case R.id.tab_home:
                        fragment = HomeFragment.newInstance();
                        fragId = newTab.getId();
                        break;
                    case R.id.tab_corona:
                        fragment = LiveDataFragment.newInstance();
                        fragId = newTab.getId();
                        break;
                    case R.id.tab_global:
                        fragment = GlobalFragment.newInstance();
                        fragId = newTab.getId();
                        break;
                    case R.id.tab_news:
                        fragment = NewsFragment.newInstance();
                        fragId = newTab.getId();
                        break;
                    case R.id.tab_map:
                        fragment = MapFragment.newInstance();
                        fragId = newTab.getId();
                        break;
                }

                // HashMap => test for fragment instance state
                if(!(fragment instanceof HomeFragment)){
                    mapOfFragments.add(newTab.getTitle());
                }

                assert fragment.getTag() != null;
                setDelegateFragment(fragment, fragId, lastIndex, newIndex);
            }

            @Override
            public void onTabReselected(int lastIndex, @NotNull AnimatedBottomBar.Tab lastTab){
                Timber.d("Reselected tab: %s", lastIndex);
            }
        });
    }

    private void setDelegateFragment(Fragment fragment, int tab, int lastIndex, int newIndex){
        String fragmentTag = fragment.getClass().getName();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.findFragmentByTag(fragmentTag);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(lastIndex > newIndex)
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        else if(lastIndex > lastIndex - 1)
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        else
            throw new TypeCastException("cannot instantiate fragment transition");
        
        if(fragmentManager.findFragmentByTag(fragmentTag) == null){
            transaction.add(R.id.fragment_container, fragment, fragmentTag).addToBackStack(fragmentTag);
        } else{
            transaction.show(Objects.requireNonNull(fragmentManager.findFragmentByTag(fragmentTag)));
        }

        animatedBottomBar.selectTabById(tab, true);
        transaction.commit();
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
        fragment.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onBackStackChanged(){

    }

}