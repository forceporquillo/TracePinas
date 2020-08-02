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

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.interfaces.BottomItemListener;
import com.force.codes.project.app.presentation_layer.controller.model.BottomItem;
import com.force.codes.project.app.presentation_layer.controller.support.BottomBar;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import com.force.codes.project.app.presentation_layer.views.fragments.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.StatisticsFragment;
import com.force.codes.project.app.presentation_layer.controller.utils.threads.AppExecutors;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class FragmentContainerActivity extends BaseActivity implements BottomItemListener {
  private static final String FRAGMENT_STATE = "save_fragment_state";
  private static final String LAST_NAV_INDEX = "KEY_INDEX";
  private static final int STATISTICS = 0;
  private static final int NEWS = 1;
  private static final int MAP = 2;
  private static final int HELP = 3;
  private static final int[] arrItemId = new int[2];
  @BindView(R.id.included)
  View includedView;
  private Unbinder unbinder;
  private Fragment fragment = getFragment();
  public FragmentContainerActivity() {
  }

  @NotNull
  private static BottomItem[] BottomItems() {
    final BottomItem[] bottomItems = new BottomItem[4];
    // region custom bottom navigation bar item instance
    bottomItems[0] = new BottomItem(STATISTICS, "Statistics", R.drawable.ic_outline_stats,
        R.drawable.ic_outline_colored_stats);
    bottomItems[1] = new BottomItem(NEWS, "News", R.drawable.ic_outline_news,
        R.drawable.ic_outline_colored_news);
    bottomItems[2] =
        new BottomItem(MAP, "Map", R.drawable.ic_outline_map, R.drawable.ic_outline_colored_map);
    bottomItems[3] = new BottomItem(HELP, "Help", R.drawable.ic_outline_phone,
        R.drawable.ic_outline_colored_phone);
    // endregion
    return bottomItems;
  }

  private static boolean checkIfActive() {
    if (arrItemId[0] != arrItemId[1]) {
      arrItemId[0] = arrItemId[1];
      return true;
    }
    return false;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment_manager);
    unbinder = ButterKnife.bind(this);

    Timber.e("SDK Version %s", String.valueOf(Utils.getSDKInt()));
    BottomBar bottomBar = new BottomBar(includedView, this, this);

    if (savedInstanceState != null) {
      fragment = getSupportFragmentManager()
          .getFragment(savedInstanceState, FRAGMENT_STATE);
    }

    // consumes 3 sec. delay when bundle is null, give way to load splash screen.
    new AppExecutors(savedInstanceState == null ? 3000 : 0).threadDelay().execute(() -> {
      setPrimaryFragment(savedInstanceState, fragment);
      setBottomBarItems(bottomBar, savedInstanceState);
    });
  }

  private void setBottomBarItems(BottomBar bottomBar, @Nullable Bundle savedInstanceState) {
    final boolean isStateChanged = savedInstanceState != null;
    for (BottomItem bottomItem : BottomItems())
      bottomBar.addBottomItem(bottomItem);
    bottomBar.setPrimary(isStateChanged ? savedInstanceState
        .getInt(LAST_NAV_INDEX) : STATISTICS);
  }

  final void setPrimaryFragment(Bundle savedInstanceState, Fragment fragment) {
    if (savedInstanceState == null) {
      arrItemId[0] = STATISTICS;
      super.setFragment(StatisticsFragment.newInstance()).commit();
      return;
    }
    super.setFragment(fragment).commit();
  }

  @Override
  public void itemSelect(int itemId) {
    arrItemId[1] = itemId;
    switch (itemId) {
      case STATISTICS:
        fragment = StatisticsFragment.newInstance();
        break;
      case NEWS:
        fragment = NewsFragment.newInstance();
        break;
      case MAP:
        fragment = MapFragment.newInstance();
        break;
      case HELP:
        fragment = LiveDataFragment.newInstance();
        break;
    }

    if (checkIfActive()) {
      setFragment(fragment).commit();
    }
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    if (fragment != null) {
      getSupportFragmentManager().putFragment(outState, FRAGMENT_STATE, fragment);
      outState.putInt(LAST_NAV_INDEX, arrItemId[0]);
    }
  }

  @Override
  public void onBackPressed() {
    int stackEntryCount = getSupportFragmentManager()
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
    unbinder.unbind();
  }
}