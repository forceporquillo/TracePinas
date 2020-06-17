package com.force.codes.project.app.presentation_layer.views.viewholders;

/*
 * Created by Force Porquillo on 6/2/20 1:53 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.CountryRowsBinding;
import com.force.codes.project.app.model.CountryDetails;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.FragmentCallback;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.StringUtils;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    FragmentCallback callback;
    CountryRowsBinding rowsBinding;

    public CountryViewHolder(@NotNull CountryRowsBinding rowsBinding, FragmentCallback callback){
        super(rowsBinding.getRoot());
        rowsBinding.getRoot().setOnClickListener(this);
        this.rowsBinding = rowsBinding;
        this.callback = callback;
    }

    public void bindTo(CountryDetails details){
        rowsBinding.setDetails(details);
        rowsBinding.setVariable(BR.details, details);
        rowsBinding.executePendingBindings();
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter({"textUpdate"})
    public static void setTextUpdate(TextView textUpdate, int newCases){
        StringUtils utils = new StringUtils();

        if(newCases > 0){
            textUpdate.setText(utils.formatNumber(String.valueOf(newCases)) + " new cases...");
        } else{
            textUpdate.setText(utils.formatNumber(String.valueOf(newCases)) + " henlo fren...");
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void setFlag(@NotNull CircleImageView flagPlaceholder, final String imageUrl){
        Glide.with(flagPlaceholder.getContext())
                .asBitmap()
                .apply(new RequestOptions()
                        .fitCenter()
                        .override(125, 125))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.cupertino_loading)
                .load(imageUrl)
                .into(flagPlaceholder);
    }

    @Override
    public void onClick(View v){
        callback.CustomCardViewListener(getAdapterPosition());
    }
}
