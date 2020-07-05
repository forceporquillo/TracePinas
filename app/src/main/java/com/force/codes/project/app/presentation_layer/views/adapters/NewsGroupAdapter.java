/*
 * Created by Force Porquillo on 7/4/20 9:17 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:17 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.testmodel.Group;
import com.force.codes.project.app.data_layer.testmodel.Models;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.CustomDividerItemDecoration;
import com.force.codes.project.app.presentation_layer.views.viewholders.NewsGroupViewHolder;

import java.util.ArrayList;

public class NewsGroupAdapter extends RecyclerView.Adapter<NewsGroupViewHolder>{
    private ArrayList<Group> groups;
    private ArrayList<Models> latestList;
    private ArrayList<Models> hotList;
    private Context context;

    private static final int LATEST_NEWS = 0;

    public NewsGroupAdapter(ArrayList<Group> groups, ArrayList<Models> latest, ArrayList<Models> hot){
        this.groups = groups;
        this.latestList = latest;
        this.hotList = hot;
    }

    @NonNull
    @Override
    public NewsGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        final View view = LayoutInflater.from(this.context = parent.getContext())
                .inflate(R.layout.news_group_layout, parent, false);
        return new NewsGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsGroupViewHolder holder, int position){
        Group group = groups.get(position);

        holder.setGroupTitle(group);
        setListOrder(holder.recyclerView, position);
    }

    @Override
    public int getItemCount(){
        return !groups.isEmpty() ? groups.size() : 0;
    }

    private void setListOrder(RecyclerView recyclerView, int position){
        if(position == LATEST_NEWS){
            setLatestNewsList(recyclerView);
            return;
        }

        setHotNewsList(recyclerView);
    }

    final void setLatestNewsList(RecyclerView recyclerView){
        HeaderNewsAdapter headerNewsAdapter = new HeaderNewsAdapter(latestList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(headerNewsAdapter);
        recyclerView.setNestedScrollingEnabled(true);
    }

    final void setHotNewsList(RecyclerView recyclerView){
        HotNewsAdapter hotNewsAdapter = new HotNewsAdapter(hotList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(hotNewsAdapter);
        recyclerView.addItemDecoration(decoration(recyclerView.getContext()));
        recyclerView.setNestedScrollingEnabled(true);
    }

    static CustomDividerItemDecoration decoration(Context context){
        return new CustomDividerItemDecoration(context,
                CustomDividerItemDecoration.VERTICAL_LIST, 16);
    }
}
