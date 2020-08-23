/*
 * Created by Force Porquillo on 6/19/20 12:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 12:08 AM
 */

package com.force.codes.project.app.presentation_layer.controller.navigation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.BottombarItemBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import java.util.ArrayList;

public class BottomBarAdapter extends RecyclerView.Adapter<BottomBarViewHolder> {
  private final ArrayList<BottomBarItem> bottomBarItems;
  private final StackEventListener.BottomItemListener bottomItemListener;
  private final int itemWidth;
  private int selected;

  public BottomBarAdapter(
      final int selected, final int itemWidth, final ArrayList<BottomBarItem> bottomBarItems,
      final StackEventListener.BottomItemListener bottomItemListener) {
    this.selected = selected;
    this.bottomBarItems = bottomBarItems;
    this.itemWidth = itemWidth;
    this.bottomItemListener = bottomItemListener;
  }

  @NonNull
  @Override public BottomBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final BottombarItemBinding itemBinding = DataBindingUtil.inflate(inflater,
        R.layout.bottombar_item, parent, false);
    return new BottomBarViewHolder(itemBinding);
  }

  @Override public void onBindViewHolder(@NonNull BottomBarViewHolder holder, int position) {
    final BottomBarItem bottomBarItem = bottomBarItems.get(position);
    if (bottomBarItems.size() != 0) {
      setBottomBarViews(holder, bottomBarItem);
      setOnClickItem(holder,
          bottomBarItem.getItemId(),
          bottomBarItem.getItemIconId(),
          bottomBarItem.getItemFillIconId());
    }
  }

  private void setBottomBarViews(BottomBarViewHolder holder, BottomBarItem bottomBarItem) {
    holder.setIcon(bottomBarItem.getItemIconId());
    holder.setItemTitle(bottomBarItem.getItemTitle());
    holder.resizeItemWidth(itemWidth);
    holder.selectedStyle(selected,
        bottomBarItem.getItemId(),
        bottomBarItem.getItemIconId(),
        bottomBarItem.getItemFillIconId());
  }

  private void setOnClickItem(BottomBarViewHolder holder, final int itemId,
      final int itemDefIcon, final int itemFillIcon
  ) {
    holder.getParentContainer().setOnClickListener(v -> {
      bottomItemListener.onBottomBarItemSelected(itemId);
      selected = itemId;
      holder.selectedStyle(selected, itemId, itemDefIcon, itemFillIcon);
      notifyDataSetChanged();
    });
  }

  @Override public int getItemCount() {
    return bottomBarItems.size();
  }
}
