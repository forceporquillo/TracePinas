/*
 * Created by Force Porquillo on 6/28/20 6:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/27/20 5:07 AM
 */

package com.force.codes.project.app.presentation_layer.views.activity;

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
import com.force.codes.project.app.presentation_layer.views.fragments.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.StatisticsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.WorldwideFragment;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FragmentContainerActivity extends BaseActivity implements BottomItemListener{
    private static final String FRAGMENT_STATE = "save_fragment_state";
    private static final String LAST_NAV_INDEX = "KEY_INDEX";
    private static final int STATISTICS = 0;
    private static final int NEWS = 1;
    private static final int WORLDWIDE = 2;
    private static final int MAP = 3;
    private static final int HELP = 4;
    private static final int VERSION_CODE = Build.VERSION.SDK_INT;

    private int lastNavIndex = STATISTICS;

    private FragmentManager fragmentManager;
    private Fragment fragment = null;

    public FragmentContainerActivity(){

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        if(fragment != null){
            getSupportFragmentManager()
                    .putFragment(outState, FRAGMENT_STATE, fragment);
            outState.putInt(LAST_NAV_INDEX, lastNavIndex);
        }
    }

    @BindView(R.id.bottom_bar)
    View view;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);
        ButterKnife.bind(this);

        CustomBottomBar bottomBar = new CustomBottomBar(view, this, this);

        if(VERSION_CODE >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setPrimaryFragment(savedInstanceState);

        if(savedInstanceState != null){
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_STATE);
            setBottomBarItems(bottomBar, true, savedInstanceState.getInt(LAST_NAV_INDEX));
            return;
        }
        setBottomBarItems(bottomBar, false, STATISTICS);
    }

    final void setPrimaryFragment(Bundle savedInstanceState){
        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, StatisticsFragment.newInstance())
                    .addToBackStack(StatisticsFragment.class.getSimpleName())
                    .commit();
        }
    }

    final void setBottomBarItems(CustomBottomBar bottomBar, boolean isStateChanged, int saveSelected){
        final BottomItem[] bottomItems = new BottomItem[5];
        // region custom bottom navigation bar item instance
        bottomItems[0] = new BottomItem(STATISTICS, "Statistics", R.drawable.ic_outline_stats, R.drawable.ic_outline_colored_stats);
        bottomItems[1] = new BottomItem(NEWS, "News", R.drawable.ic_outline_news, R.drawable.ic_outline_colored_news);
        bottomItems[2] = new BottomItem(WORLDWIDE, "Worldwide", R.drawable.ic_outline_worldwide, R.drawable.ic_outline_colored_worldwide);
        bottomItems[3] = new BottomItem(MAP, "Map", R.drawable.ic_outline_map, R.drawable.ic_outline_colored_map);
        bottomItems[4] = new BottomItem(HELP, "Help", R.drawable.ic_outline_phone, R.drawable.ic_outline_colored_phone);
        // endregion
        bottomBar.addBottomItem(bottomItems);
        if(isStateChanged) bottomBar.setPrimary(saveSelected);
        else bottomBar.setPrimary(STATISTICS);
    }

    @Override
    public void itemSelect(int itemId){
        switch(itemId){
            case STATISTICS:
                fragment = StatisticsFragment.newInstance();
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
                fragment = LiveDataFragment.newInstance();
                break;
        }

        lastNavIndex = itemId;
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
        // count fragment available in backStack.
        int count = getSupportFragmentManager()
                .getBackStackEntryCount();

        if(count > 1){
            super.onBackPressed();
            return;
        }

        finish();
    }
}