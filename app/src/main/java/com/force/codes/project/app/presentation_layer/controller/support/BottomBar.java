/*
 * Created by Force Porquillo on 6/19/20 12:07 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 12:07 AM
 */

package com.force.codes.project.app.presentation_layer.controller.support;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.presentation_layer.controller.interfaces.BottomItemListener;
import com.force.codes.project.app.presentation_layer.controller.model.BottomBarItem;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import com.force.codes.project.app.presentation_layer.views.adapters.BottomBarAdapter;
import java.util.ArrayList;
import java.util.Collections;

public class BottomBar {
  private final static int ITEM_LIMIT = 5;
  private final Context context;
  private final BottomItemListener listener;
  private final RecyclerView recyclerView;
  private ArrayList<BottomBarItem> bottomBarItems;

  public BottomBar(
      RecyclerView recyclerView,
      Context context,
      BottomItemListener listener
  ) {
    this.recyclerView = recyclerView;
    this.context = context;
    this.listener = listener;
    bottomBarItems = new ArrayList<>();
  }

  public void setPrimary(int selected) {
    setBottomAdapter(selected);
  }

  final int calculateWidth() {
    return Utils.dpToPx(context,
        bottomBarItems.size() + 1,
        false);
  }

  public void addBottomItem(final BottomBarItem item) {
    if (bottomBarItems.size() != ITEM_LIMIT) {
      bottomBarItems.addAll(Collections.singletonList(item));
    }
  }

  private void setBottomAdapter(final int selected) {
    final BottomBarAdapter bottomBarAdapter = new BottomBarAdapter(
        selected, calculateWidth(), bottomBarItems, listener);
    recyclerView.setLayoutManager(new LinearLayoutManager(
        context, LinearLayoutManager.HORIZONTAL, false)
    );
    recyclerView.setAdapter(bottomBarAdapter);
  }
}
