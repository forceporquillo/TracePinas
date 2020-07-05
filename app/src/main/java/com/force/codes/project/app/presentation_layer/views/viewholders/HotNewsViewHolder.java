/*
 * Created by Force Porquillo on 7/4/20 9:20 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:20 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.testmodel.Models;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotNewsViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.image_placeholder)
    ImageView imageView;

    @BindView(R.id.item_title)
    TextView title;

    @BindView(R.id.item_subtitle)
    TextView timestamp;

    public HotNewsViewHolder(@NonNull View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setHotView(Models models){
        title.setText(models.getTitle());
        timestamp.setText(models.getDate());
        Glide.with(imageView.getContext())
                .load(models.getThumbnail())
                .apply(new RequestOptions()
                        .fitCenter()
                        .override(imageView.getWidth(),
                                imageView.getHeight()))
                .centerCrop()
                .into(imageView);
    }
}
