/*
 * Created by Force Porquillo on 6/19/20 12:07 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 12:07 AM
 */

package com.force.codes.project.app.presentation_layer.controller.support;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.interfaces.BottomItemListener;
import com.force.codes.project.app.presentation_layer.controller.model.BottomItem;
import com.force.codes.project.app.presentation_layer.views.adapters.BottomBarAdapter;
import java.util.ArrayList;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

public class BottomBar {
  private final static int ITEM_LIMIT = 5;
  private final Context context;
  private final BottomItemListener listener;
  private final RecyclerView recyclerView;
  private ArrayList<BottomItem> bottomItems;

  public BottomBar(
      RecyclerView recyclerView,
      Context context,
      BottomItemListener listener
  ) {
    this.recyclerView = recyclerView;
    this.context = context;
    this.listener = listener;
    bottomItems = new ArrayList<>();
  }

  public void addBottomItem(final BottomItem item) {
    if (bottomItems.size() != ITEM_LIMIT) {
      bottomItems.addAll(Collections.singletonList(item));
    }
  }

  final void setBottomAdapter(int selected) {
    BottomBarAdapter bottomBarAdapter = new BottomBarAdapter(
        selected, calculateWidth(), bottomItems, listener);
    recyclerView.setLayoutManager(new LinearLayoutManager(
        context, LinearLayoutManager.HORIZONTAL, false)
    );
    recyclerView.setAdapter(bottomBarAdapter);
  }

  public void setPrimary(int selected) {
    setBottomAdapter(selected);
  }

  final int calculateWidth() {
    return context.getResources()
        .getDisplayMetrics()
        .widthPixels / (bottomItems.size() + 1);
  }
}
