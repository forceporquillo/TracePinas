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
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.NewsData;
import com.force.codes.project.app.data_layer.testmodel.Group;
import com.force.codes.project.app.data_layer.testmodel.Models;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.CustomDividerItemDecoration;
import com.force.codes.project.app.presentation_layer.views.viewholders.NewsGroupViewHolder;

import java.util.ArrayList;

public class NewsGroupAdapter extends RecyclerView.Adapter<NewsGroupViewHolder>{
    private ArrayList<Group> groups;
    private ArrayList<Models> latestList;
    private Context context;
    private NewsItemCallback callback;
    private HotNewsAdapter adapter;

    public NewsGroupAdapter(
            ArrayList<Group> groups,
            ArrayList<Models> latest,
            NewsItemCallback callback,
            HotNewsAdapter adapter){
        this.groups = groups;
        this.latestList = latest;
        this.callback = callback;
        this.adapter = adapter;
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

        if(position == 1)
            holder.setDecorVisibility(true);
        setListOrder(holder.recyclerView, position);
    }

    @Override
    public int getItemCount(){
        return !groups.isEmpty() ? groups.size() : 0;
    }

    private void setListOrder(RecyclerView recyclerView, int position){
        if(position == 0){
            setLatestNewsList(recyclerView);
            return;
        }
        setHotNewsList(recyclerView);
    }

    static CustomDividerItemDecoration decoration(Context context){
        return new CustomDividerItemDecoration(context,
                CustomDividerItemDecoration.VERTICAL_LIST, 0);
    }

    final void setLatestNewsList(RecyclerView recyclerView){
        HeaderNewsAdapter headerNewsAdapter = new HeaderNewsAdapter(latestList, callback);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(headerNewsAdapter);
        recyclerView.setNestedScrollingEnabled(true);
    }


    final void setHotNewsList(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(decoration(recyclerView.getContext()));
        recyclerView.setNestedScrollingEnabled(true);
    }
}
