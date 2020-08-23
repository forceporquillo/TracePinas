/*
 * Created by Force Porquillo on 7/6/20 2:12 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:12 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.databinding.HeaderNewsLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.views.viewholders.HeaderNewsViewHolder;

public class HeaderNewsAdapter extends PagedListAdapter<TwitterData, HeaderNewsViewHolder> {
  private static
  DiffUtil.ItemCallback<TwitterData> DIFF_CALLBACK = new DiffUtil.ItemCallback<TwitterData>() {
    @Override
    public boolean
    areItemsTheSame(@NonNull TwitterData oldItem, @NonNull TwitterData newItem) {
      return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean
    areContentsTheSame(@NonNull TwitterData oldItem, @NonNull TwitterData newItem) {
      return oldItem.getFullText().equals(newItem.getFullText());
    }
  };
  private StackEventListener.NewsItemCallback callback;

  public HeaderNewsAdapter(StackEventListener.NewsItemCallback callback) {
    super(DIFF_CALLBACK);
    this.callback = callback;
  }

  @NonNull
  @Override
  public HeaderNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    HeaderNewsLayoutBinding binding = DataBindingUtil
        .inflate(inflater, R.layout.header_news_layout, parent, false);
    return new HeaderNewsViewHolder(binding, callback);
  }

  @Override
  public void onBindViewHolder(@NonNull HeaderNewsViewHolder holder, int position) {
    TwitterData twitterData = getTwitterDataAt(position);
    holder.setMarginAtRuntime(position);
    holder.bindTo(twitterData);
  }

  private TwitterData getTwitterDataAt(int position) {
    return getItem(position);
  }
}
