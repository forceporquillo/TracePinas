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

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.BottomItemListener;
import com.force.codes.project.app.presentation_layer.controller.custom.model.BottomItem;
import com.force.codes.project.app.presentation_layer.views.adapters.BottomBarAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomBottomBar{
    private final static int ITEM_LIMIT = 5;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList <BottomItem> bottomItems;
    private BottomItemListener bottomItemListener;

    public CustomBottomBar(View view, Context context, BottomItemListener bottomItemListener){
        setType(view);
        this.context = context;
        this.bottomItemListener = bottomItemListener;
    }

    private void setType(View view){
        recyclerView = view.findViewById(R.id.bottom_bar_recyclerview);
        bottomItems = new ArrayList <>();
    }

    public void addBottomItem(final BottomItem[] item){
        if(bottomItems.size() != ITEM_LIMIT){
            bottomItems.addAll(Arrays.asList(item));
        }
    }

    private void setBottomAdapter(int selected){
        BottomBarAdapter bottomBarAdapter = new BottomBarAdapter(
                selected, bottomItems, calculateWidth(), bottomItemListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerView.setAdapter(bottomBarAdapter);
    }

    public void setPrimary(int selected){
        setBottomAdapter(selected);
    }

    final int calculateWidth(){
        int count = bottomItems.size() + 1;
        int width = context.getResources().getDisplayMetrics().widthPixels;
        return width / count;
    }
}
