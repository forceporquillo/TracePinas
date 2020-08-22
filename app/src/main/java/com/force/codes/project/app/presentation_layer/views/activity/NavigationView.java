package com.force.codes.project.app.presentation_layer.views.activity;

import android.content.Context;
import android.content.res.Resources;
import androidx.fragment.app.Fragment;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.fragments.LiveDataFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.MapFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.NewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.ReadNewsFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.StatisticsFragment;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

final class NavigationView {
  private static HashMap<Integer, Fragment> fragStack = new HashMap<>();
  private static String INDEX_TAG = "android:support:next_request_index";

  NavigationView() {
    throw new AssertionError("No instance required");
  }

  public static Fragment setDelegateFrag(Fragment fragment) {
    // TODO
    return fragment;
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

    public static List<Fragment> getOrderedFragStackList() {
      return new ArrayList<>(Arrays.asList(
          StatisticsFragment.newInstance(),
          NewsFragment.newInstance(),
          MapFragment.newInstance(),
          LiveDataFragment.newInstance(),
          new ReadNewsFragment()
      ));
    }
  }
}
