/*
 * Created by Force Porquillo on 6/19/20 12:08 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 12:08 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.BottomItemListener;
import com.force.codes.project.app.presentation_layer.controller.custom.model.BottomItem;
import com.force.codes.project.app.presentation_layer.views.viewholders.BottomBarViewHolder;

import java.util.ArrayList;

public class BottomBarAdapter extends RecyclerView.Adapter <BottomBarViewHolder>{
    private ArrayList <BottomItem> bottomItems;
    private int itemWidth;
    private int selected;
    private BottomItemListener bottomItemListener;

    public BottomBarAdapter(
            int selected,
            ArrayList <BottomItem> bottomItems,
            int itemWidth,
            BottomItemListener bottomItemListener)
    {
        this.selected = selected;
        this.bottomItems = bottomItems;
        this.itemWidth = itemWidth;
        this.bottomItemListener = bottomItemListener;
    }

    @NonNull
    @Override
    public BottomBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottombar_item, parent, false);
        return new BottomBarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomBarViewHolder holder, int position){
        BottomItem bottomItem = bottomItems.get(position);

        if(bottomItem != null){
            holder.resizeItemWidth(itemWidth);
            holder.setIcon(bottomItem.getItemIconId());
            holder.selectedStyle(selected,
                    bottomItem.getItemId(),
                    bottomItem.getItemIconId(),
                    bottomItem.getItemFillIconId()
            );
            holder.setItemTitle(bottomItem.getItemTitle());
            setOnClickItem(holder,
                    bottomItem.getItemId(),
                    bottomItem.getItemIconId(),
                    bottomItem.getItemFillIconId()
            );
        }
    }

    @Override
    public int getItemCount(){
        return !bottomItems.isEmpty() ? bottomItems.size() : 0;
    }

    private void setOnClickItem(BottomBarViewHolder holder, final int itemId, final int itemDefIcon, final int itemFillIcon){
        holder.parentContainer.setOnClickListener(v -> {
            bottomItemListener.itemSelect(itemId);
            selected = itemId;
            holder.selectedStyle(selected, itemId, itemDefIcon, itemFillIcon);
            notifyDataSetChanged();
        });
    }
}
