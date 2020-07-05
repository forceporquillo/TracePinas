/*
 * Created by Force Porquillo on 7/4/20 9:19 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:19 AM
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

public class HeaderNewsViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.item_cover_picture)
    ImageView imageView;

    @BindView(R.id.group_header_text)
    TextView headerText;

    @BindView(R.id.date_timestamp)
    TextView timestamp;

    public HeaderNewsViewHolder(@NonNull View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setHeaderView(Models models){
        headerText.setText(models.getTitle());
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
