/*
 * Created by Force Porquillo on 7/17/20 3:40 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 3:40 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.CardViewLayoutBinding;
import com.force.codes.project.app.presentation_layer.views.viewholders.OverAllViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OverAllAdapter extends RecyclerView.Adapter<OverAllViewHolder>{
    private static final int MAX_CARD_NUM = 5;

    public OverAllAdapter(){

    }

    @NonNull
    @Override
    public OverAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardViewLayoutBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.card_view_layout, parent, false);
        return new OverAllViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OverAllViewHolder holder, int position){

    }

    @Override
    public int getItemCount(){
        return MAX_CARD_NUM;
    }
}
