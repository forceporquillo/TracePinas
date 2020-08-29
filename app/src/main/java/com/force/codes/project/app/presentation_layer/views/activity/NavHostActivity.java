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

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.ActivityNavHostBinding;
import com.force.codes.project.app.databinding.BottombarLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.navigation.BottomBar;
import com.force.codes.project.app.presentation_layer.controller.navigation.BottomBarItem;
import com.force.codes.project.app.presentation_layer.controller.navigation.BottomNavigationView;
import com.force.codes.project.app.presentation_layer.controller.navigation.BottomNavigationView.BottomBarDrawableArray;
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.views.base.BaseActivity;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.HelpCenterFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.StatisticsFragment;
import com.force.codes.project.app.presentation_layer.views.viewmodels.NavHostViewModel;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

import static com.force.codes.project.app.presentation_layer.controller.navigation.BottomNavigationView.BottomBarDrawableArray.DRAWABLE_ICONS;
import static com.force.codes.project.app.presentation_layer.controller.navigation.BottomNavigationView.setDelegateFragment;

public class NavHostActivity extends BaseActivity
    implements StackEventListener.BottomItemListener {
  private static final String SAVE_FRAGMENT_STATE = "SAVE_FRAGMENT_STATE";

  private static final String LAST_NAV_INDEX = "KEY_INDEX";

  private static int START_MATRIX = 0;

  private static final int STATISTICS = 0;

  private static final int NEWS = 1;

  private static final int MAP = 2;

  private static final int HELP = 3;

  private static final int CAUTIONS = 4;

  private static final int[] FRAGMENT_HASHCODE = new int[2];

  private Fragment fragment = getFragment();

  private ActivityNavHostBinding binding;

  @Inject ViewModelProviderFactory factory;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_nav_host);
    final BottombarLayoutBinding layoutBinding = binding.bottomBar;
    final BottomBar bottomBar = new BottomBar(layoutBinding.recyclerView, this, this);

    if (savedInstanceState != null) {
      fragment = getSupportFragmentManager()
          .getFragment(savedInstanceState, SAVE_FRAGMENT_STATE);
    }

    if (IS_FRESH_INSTALL) {
      NavHostViewModel viewModel = new ViewModelProvider(this, factory).get(NavHostViewModel.class);
      viewModel.insertDefaultCountryAtFirstInstall(getString(R.string.default_country));
    }

    new ThreadExecutor(savedInstanceState == null ? 3000 : 0).thisUIThread().execute(() -> {
      BottomNavigationView.setSupportFragmentManager(getSupportFragmentManager());
      setPrimaryFragment(savedInstanceState);
      setBottomBarItems(bottomBar, savedInstanceState);
      layoutBinding.bottomParentContainer.setVisibility(View.VISIBLE);
    });
    if (binding.hasPendingBindings()) {
      binding.executePendingBindings();
    }
  }

  private static boolean isFragmentReselected(final int index, final Fragment fragment) {
    if (!isVisibleInStackList(index, fragment)) {
      throw new IllegalArgumentException(fragment.getClass().getSimpleName()
          .concat(" is not present in BottomDrawableArray.ListStack index " + index));
    }
    if (FRAGMENT_HASHCODE[0] != FRAGMENT_HASHCODE[1]) {
      FRAGMENT_HASHCODE[0] = FRAGMENT_HASHCODE[1];
      return true;
    }
    return false;
  }

  private static BottomBarItem[] bottomItems(final Context context) {
    final BottomBarItem[] bottomBarItems = new BottomBarItem[5];
    for (int i = 0; i < DRAWABLE_ICONS.length; ++i) {
      for (int j = 0; j < DRAWABLE_ICONS[0].length; ++j) {
        if (j == 0) {
          bottomBarItems[i] = new BottomBarItem(i, BottomNavigationView
              .BottomBarDrawableArray.getFragmentIds(context)[i],
              DRAWABLE_ICONS[i][j], DRAWABLE_ICONS[i][START_MATRIX + 1]
          );
        }
      }
    }
    return bottomBarItems;
  }

  final void setBottomBarItems(final BottomBar bottomBar, final Bundle savedInstanceState) {
    for (BottomBarItem item : bottomItems(getApplicationContext())) {
      bottomBar.addBottomItem(item);
    }
    final boolean isStateChanged = savedInstanceState == null;
    bottomBar.setPrimary(!isStateChanged ? savedInstanceState
        .getInt(LAST_NAV_INDEX) : START_MATRIX
    );
  }

  private static void changeFragment(final Fragment fragment, final int itemId) {
    if (isFragmentReselected(itemId, fragment)) {
      setDelegateFragment(fragment, itemId);
    }
  }

  @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    if (fragment != null) {
      if (fragment instanceof StatisticsFragment) {
        // We don't want to save the state of StatisticsFragment since it's a holder class
        // for FragmentViewPager. It'll automatically remove in stack when we navigate to
        // different fragments in ViewPager. When the device rotates or change its
        // configurations it returns the current state of fragment in ViewPager, not the
        // StatisticFragment class itself which is not listed in fragment manager stack,
        // and this throws an IllegalStateException error.
        return;
      }
      getSupportFragmentManager().putFragment(outState, SAVE_FRAGMENT_STATE, fragment);
      outState.putInt(LAST_NAV_INDEX, FRAGMENT_HASHCODE[0]);
    }
  }

  private static boolean isVisibleInStackList(final int itemId, @NotNull final Fragment fragment) {
    return BottomBarDrawableArray.getFragStackList()
        .get(itemId)
        .getClass()
        .getSimpleName()
        .equals((fragment.getClass()
            .getSimpleName())
        );
  }

  final void setPrimaryFragment(final Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      setDelegateFragment(StatisticsFragment.newInstance(),
          FRAGMENT_HASHCODE[0] = STATISTICS);
    }
  }

  @Override protected void onResume() {
    super.onResume();
    if (binding != null) {
      if (binding.hasPendingBindings()) {
        binding.invalidateAll();
      }
      return;
    }
    throw new NullPointerException(getResources()
        .getString(R.string.layout_binding_npe)
        .concat(binding.toString())
    );
  }

  @Override
  public void onBottomBarItemSelected(final int itemId) {
    switch (FRAGMENT_HASHCODE[1] = itemId) {
      case STATISTICS:
        fragment = StatisticsFragment.newInstance();
        break;
      case NEWS:
        fragment = NewsFragment.newInstance(true);
        break;
      case MAP:
        fragment = MapFragment.newInstance();
        break;
      case HELP:
        fragment = LiveDataFragment.newInstance();
        break;
      case CAUTIONS:
        fragment = HelpCenterFragment.newInstance();
        break;
      default:
        throw new IllegalArgumentException();
    }
    changeFragment(fragment, itemId);
  }

  @Override
  public void onBackPressed() {
    final int stackEntryCount = getSupportFragmentManager()
        .getBackStackEntryCount();
    if (stackEntryCount > 0) {
      super.onBackPressed();
      return;
    }
    finish();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (binding != null) {
      binding.unbind();
    }
  }
}