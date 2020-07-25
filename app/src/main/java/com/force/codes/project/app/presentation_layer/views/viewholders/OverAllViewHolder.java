/*
 * Created by Force Porquillo on 7/17/20 3:41 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 3:41 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.CardViewLayoutBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OverAllViewHolder extends RecyclerView.ViewHolder{

    private final CardViewLayoutBinding binding;

    public OverAllViewHolder(@NonNull CardViewLayoutBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setBinding(){
        
    }
}
