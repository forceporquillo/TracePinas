/*
 * Created by Force Porquillo on 6/19/20 12:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 12:08 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.BottombarItemBinding;
import com.force.codes.project.app.presentation_layer.controller.interfaces.BottomItemListener;
import com.force.codes.project.app.presentation_layer.controller.model.BottomBarItem;
import com.force.codes.project.app.presentation_layer.views.viewholders.BottomBarViewHolder;
import java.util.ArrayList;

public class BottomBarAdapter extends RecyclerView.Adapter<BottomBarViewHolder> {
  private final ArrayList<BottomBarItem> bottomBarItems;
  private final BottomItemListener bottomItemListener;
  private final int itemWidth;
  private int selected;

  public BottomBarAdapter(
      final int selected, final int itemWidth, final ArrayList<BottomBarItem> bottomBarItems,
      final BottomItemListener bottomItemListener) {
    this.selected = selected;
    this.bottomBarItems = bottomBarItems;
    this.itemWidth = itemWidth;
    this.bottomItemListener = bottomItemListener;
  }

  private void setOnClickItem(
      BottomBarViewHolder holder, final int itemId, final int itemDefIcon, final int itemFillIcon) {
    holder.getParentContainer().setOnClickListener(v -> {
      bottomItemListener.itemSelect(itemId);
      selected = itemId;
      holder.selectedStyle(selected, itemId, itemDefIcon, itemFillIcon);
      notifyDataSetChanged();
    });
  }

  @NonNull
  @Override public BottomBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final BottombarItemBinding itemBinding = DataBindingUtil.inflate(inflater,
        R.layout.bottombar_item, parent, false);
    return new BottomBarViewHolder(itemBinding);
  }

  @Override public void onBindViewHolder(@NonNull BottomBarViewHolder holder, int position) {
    BottomBarItem bottomBarItem = bottomBarItems.get(position);
    holder.resizeItemWidth(itemWidth);
    holder.setIcon(bottomBarItem.getItemIconId());
    holder.selectedStyle(selected,
        bottomBarItem.getItemId(),
        bottomBarItem.getItemIconId(),
        bottomBarItem.getItemFillIconId());
    holder.setItemTitle(bottomBarItem.getItemTitle());
    setOnClickItem(holder,
        bottomBarItem.getItemId(),
        bottomBarItem.getItemIconId(),
        bottomBarItem.getItemFillIconId());
  }

  @Override public int getItemCount() {
    return !bottomBarItems.isEmpty() ? bottomBarItems.size() : 0;
  }
}
