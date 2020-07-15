/*
 * Created by Force Porquillo on 7/6/20 2:37 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:37 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.databinding.HotNewsLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.views.viewholders.HotNewsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HotNewsAdapter extends PagedListAdapter<ArticlesItem, HotNewsViewHolder>{
    private NewsItemCallback callback;
    private List<ArticlesItem> itemList;

    public HotNewsAdapter(NewsItemCallback callback){
        super(DIFF_CALLBACK);
        this.callback = callback;
        itemList = new ArrayList<>();
    }

    public void setItemList(List<ArticlesItem> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public HotNewsViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HotNewsLayoutBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.hot_news_layout, parent, false);
        return new HotNewsViewHolder(binding, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull HotNewsViewHolder holder, int position){
        ArticlesItem articlesItem = getArticleItemAt(position);
        holder.bindTo(articlesItem);
    }

    final ArticlesItem getArticleItemAt(int position){
        return getItem(position);
    }

    private static DiffUtil.ItemCallback<ArticlesItem> DIFF_CALLBACK = new DiffUtil
            .ItemCallback<ArticlesItem>(){
        @Override
        public boolean areItemsTheSame(@NonNull ArticlesItem oldItem, @NonNull ArticlesItem newItem){
            return oldItem.getPublishedAt().equals(newItem.getPublishedAt());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticlesItem oldItem, @NonNull ArticlesItem newItem){
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };
}
