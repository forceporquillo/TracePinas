package com.force.codes.project.app.presentation_layer.controller.navigation;

import android.content.Context;
import android.content.res.Resources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.HelpCenterFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.bottombar.StatisticsFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BottomNavigationView {

  public static final HashMap<Integer, Fragment> FRAGMENT_LOOKUP_MAP = new HashMap<>();

  private static FragmentManager SUPPORT_FRAGMENT_MANAGER;

  private static int FRAGMENT_INDEX = 0;

  BottomNavigationView() {
    throw new AssertionError("No instance required");
  }

  public static void setSupportFragmentManager(final FragmentManager supportFragmentManager) {
    SUPPORT_FRAGMENT_MANAGER = supportFragmentManager;
  }

  public static void setDelegateFragment(final Fragment fragment, final int itemIndex) {
    if (fragment == null) {
      throw new NullPointerException("Delegate fragment is null");
    }

    final String fragmentTag = fragment.getClass().getSimpleName();
    final FragmentTransaction fragmentTransaction = SUPPORT_FRAGMENT_MANAGER.beginTransaction();
    final Fragment[] delegateFragment = new Fragment[] {
        SUPPORT_FRAGMENT_MANAGER.getPrimaryNavigationFragment(),
        SUPPORT_FRAGMENT_MANAGER.findFragmentByTag(fragmentTag)
    };

    FRAGMENT_LOOKUP_MAP.put(itemIndex, fragment);

    if (FRAGMENT_INDEX > itemIndex) {
      fragmentTransaction.setCustomAnimations(R.anim.enter_from_left,
          R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left
      );
    } else {
      fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,
          R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right
      );
    }

    FRAGMENT_INDEX = itemIndex;

    if (delegateFragment[0] != null) {
      fragmentTransaction.hide(delegateFragment[0]);
    }

    if (delegateFragment[1] == null) {
      delegateFragment[1] = fragment;
      fragmentTransaction.add(R.id.fragment_container, delegateFragment[1], fragmentTag);
    } else {
      fragmentTransaction.show(delegateFragment[1]);
    }

    fragmentTransaction.setPrimaryNavigationFragment(delegateFragment[1])
        .setReorderingAllowed(true)
        .commitAllowingStateLoss();
  }

  public static final class BottomBarDrawableArray {
    BottomBarDrawableArray() {
      throw new AssertionError("No instance required");
    }

    public static String[] getFragmentIds(final Context context) {
      final Resources resources = context.getResources();
      return new String[] {
          resources.getString(R.string.statistics),
          resources.getString(R.string.news),
          resources.getString(R.string.map),
          resources.getString(R.string.help),
          resources.getString(R.string.caution),
      };
    }

    public static final int[][] DRAWABLE_ICONS = new int[][] {
        { R.drawable.ic_stats, R.drawable.ic_fill_stats },
        { R.drawable.ic_news, R.drawable.ic_fill_news },
        { R.drawable.ic_map, R.drawable.ic_fill_map },
        { R.drawable.ic_heart, R.drawable.ic_fill_heart },
        { R.drawable.ic_phone, R.drawable.ic_fill_phone }
    };

    public static List<Fragment> getFragStackList() {
      return new ArrayList<>(Arrays.asList(
          StatisticsFragment.newInstance(),
          NewsFragment.newInstance(true),
          MapFragment.newInstance(),
          LiveDataFragment.newInstance(),
          HelpCenterFragment.newInstance()
      ));
    }
  }
}
