/*
 * Created by Force Porquillo on 7/4/20 9:20 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:20 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.databinding.HotNewsLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import org.jetbrains.annotations.NotNull;

public class HotNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  private final StackEventListener.NewsItemCallback callback;
  private final HotNewsLayoutBinding binding;

  public HotNewsViewHolder(
      @NonNull HotNewsLayoutBinding binding,
      StackEventListener.NewsItemCallback callback
  ) {
    super(binding.getRoot());
    this.binding = binding;
    this.callback = callback;
    itemView.setOnClickListener(this);
  }

  @BindingAdapter({ "imgUrl" })
  public static void setThumbnail(ImageView imageView, @NotNull String url) {
    Glide.with(imageView.getContext())
        .load(url)
        .placeholder(R.drawable.progress_loading)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_refresh)
        .dontAnimate()
        .into(imageView);
  }

  public void bindTo(ArticlesItem articlesItem) {
    binding.setArticleItem(articlesItem);
    binding.setVariable(BR.articleItem, articlesItem);
    binding.executePendingBindings();
  }

  @Override
  public void onClick(View v) {
    callback.onLocalNewsListener(getAdapterPosition());
  }
}
