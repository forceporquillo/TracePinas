/*
 * Created by Force Porquillo on 7/6/20 2:37 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:37 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.testmodel.Models;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.views.viewholders.HotNewsViewHolder;

import java.util.ArrayList;

public class HotNewsAdapter extends RecyclerView.Adapter<HotNewsViewHolder>{
    private ArrayList<Models> hotList;
    Context context;
    private NewsItemCallback callback;

    public HotNewsAdapter(ArrayList<Models> hotList, NewsItemCallback callback){
        this.hotList = hotList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public HotNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        final View view = LayoutInflater.from(this.context = parent.getContext())
                .inflate(R.layout.hot_news_layout, parent, false);
        return new HotNewsViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull HotNewsViewHolder holder, int position){
        Models models = hotList.get(position);

        holder.setHotView(models);
    }

    @Override
    public int getItemCount(){
        return !hotList.isEmpty() ? hotList.size() : 0;
    }
}
