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
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.ArticlesItem;
import com.force.codes.project.app.data_layer.model.NewsData;
import com.force.codes.project.app.data_layer.testmodel.Models;
import com.force.codes.project.app.databinding.HeaderNewsLayoutBinding;
import com.force.codes.project.app.databinding.HotNewsLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class HotNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private NewsItemCallback callback;
    private HotNewsLayoutBinding binding;

    public HotNewsViewHolder(
            @NonNull HotNewsLayoutBinding binding,
            NewsItemCallback callback
    ){
        super(binding.getRoot());
        this.binding = binding;
        this.callback = callback;
        itemView.setOnClickListener(this);
    }

    public void bindTo(ArticlesItem articlesItem){
       binding.setArticleItem(articlesItem);
       binding.setVariable(BR.articleItem, articlesItem);
       binding.executePendingBindings();
    }

    @BindingAdapter({"imgUrl"})
    public static void
    setThumbnail(ImageView imageView, @NotNull String url){
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.progress_loading)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void onClick(View v){
        callback.hotNewsItemListener(getAdapterPosition());
    }
}
