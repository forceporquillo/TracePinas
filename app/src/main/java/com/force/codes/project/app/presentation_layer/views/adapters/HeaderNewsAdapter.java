/*
 * Created by Force Porquillo on 7/6/20 2:12 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:12 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.testmodel.Models;
import com.force.codes.project.app.databinding.HeaderNewsLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.views.viewholders.HeaderNewsViewHolder;

import java.util.ArrayList;

public class HeaderNewsAdapter extends RecyclerView.Adapter<HeaderNewsViewHolder>{
    private ArrayList<Models> list;
    private Context context;

    private NewsItemCallback callback;
    public HeaderNewsAdapter(ArrayList<Models> latestList, NewsItemCallback callback){
        this.list = latestList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public HeaderNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(this.context = parent.getContext());

        HeaderNewsLayoutBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.header_news_layout, parent, false);
        return new HeaderNewsViewHolder(binding, getItemCount(), callback);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderNewsViewHolder holder, int position){
        Models models = list.get(position);

        holder.setMarginAtRuntime(position);
        holder.bindHeaderView(models);
        System.out.println(getItemCount());
    }

    @Override
    public int getItemCount(){
        return !list.isEmpty() ? list.size() : 0;
    }
}
