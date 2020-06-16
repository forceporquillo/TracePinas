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
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.activities.BaseActivity;
import com.force.codes.project.app.presentation_layer.views.fragments.home.HomeFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.live.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.mapview.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.worldwide.WorldwideFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.TypeCastException;
import leakcanary.AppWatcher;
import leakcanary.ObjectWatcher;
import nl.joery.animatedbottombar.AnimatedBottomBar;
import timber.log.Timber;

public class FragmentContainerActivity extends BaseActivity{
    private static final String SAVE_FRAGMENT_STATE = "save_fragment_state";

    @BindView(R.id.animatedBottomBar)
    AnimatedBottomBar animatedBottomBar;

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private CompositeDisposable compositeDisposable;

    private Unbinder unbinder;

    public FragmentContainerActivity(){
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onStart(){
        super.onStart();
        setBottomBarNavigation();
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

        unbinder = ButterKnife.bind(this);

        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Timber.plant(new Timber.DebugTree());

        final int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion <= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        AppWatcher.getConfig().getWatchActivities();
        AppWatcher.getConfig().getWatchFragments();

        ObjectWatcher objectWatcher = AppWatcher.INSTANCE.getObjectWatcher();
        objectWatcher.getRetainedObjectCount();

        if(savedInstanceState != null)
            fragment = getSupportFragmentManager()
                    .getFragment(savedInstanceState, SAVE_FRAGMENT_STATE);

        setDefaultFragment(savedInstanceState);
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();

        animatedBottomBar = null;

        if(compositeDisposable != null && compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }

        unbinder.unbind();
    }

    private FragmentTransaction setDelegateFragment(Fragment fragment, int lastIndex, int newIndex){
        String fragmentTag = fragment.getClass().getName();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(lastIndex > newIndex)
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        else if(lastIndex > lastIndex - 1) // pref. to use this
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        else throw new TypeCastException("cannot instantiate fragment transition");

        if(fragmentManager.findFragmentByTag(fragmentTag) == null){
            return transaction.add(R.id.fragment_container, fragment, fragmentTag).addToBackStack(fragmentTag);
        } else{
            return transaction.show(Objects.requireNonNull(fragmentManager.findFragmentByTag(fragmentTag)));
        }
    }

    private void setDefaultFragment(Bundle savedInstanceState){
        if(savedInstanceState == null){
            animatedBottomBar.selectTabById(R.id.tab_home, true);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment.newInstance())
                    .addToBackStack("HomeFragment")
                    .commit();
        }
    }

    private void setBottomBarNavigation(){
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener(){
            @Override
            public void onTabSelected(int lastIndex, AnimatedBottomBar.Tab lastTab, int newIndex, @NotNull AnimatedBottomBar.Tab newTab){
                switch(newTab.getId()){
                    case R.id.tab_home:
                        fragment = HomeFragment.newInstance();
                        break;
                    case R.id.tab_corona:
                        fragment = LiveDataFragment.newInstance();
                        break;
                    case R.id.tab_global:
                        fragment = WorldwideFragment.newInstance();
                        break;

                    case R.id.tab_map:
                        fragment = MapFragment.newInstance();
                        break;
                }

                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

               /*if(lastIndex > newIndex)
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                else if(lastIndex > lastIndex - 1) // pref. to use this
                    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);*/

                transaction.replace(R.id.fragment_container, fragment).addToBackStack(lastTab
                        .getTitle()).commit();
            }

            @Override
            public void onTabReselected(int lastIndex, @NotNull AnimatedBottomBar.Tab lastTab){
                Timber.d("Reselected tab: %s", lastIndex);
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(getSupportFragmentManager().getBackStackEntryCount() > 1)
            super.onBackPressed();
        else
            finish();

    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}