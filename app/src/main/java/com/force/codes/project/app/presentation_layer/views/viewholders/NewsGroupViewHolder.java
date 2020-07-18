/*
 * Created by Force Porquillo on 7/5/20 12:26 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/5/20 12:26 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.NewsGroupLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.custom.model.Group;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.RuntimeMargin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsGroupViewHolder extends RecyclerView.ViewHolder{
    // region ...
    @BindView(R.id.news_group_recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.head_parent)
    RelativeLayout relativeLayout;

    @BindView(R.id.bottom_divider)
    View view;
    //endregion

    private NewsGroupLayoutBinding binding;

    public NewsGroupViewHolder(NewsGroupLayoutBinding binding){
        super(binding.getRoot());
        this.binding = binding;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Group group){
        binding.setGroup(group);
        binding.setVariable(BR.group, group);
        binding.setGroupAdapter(NewsGroupViewHolder.this);
        binding.setVariable(BR.groupAdapter, NewsGroupViewHolder.this);
        binding.executePendingBindings();
    }

    public void setMarginAtRuntime(){
        RelativeLayout.LayoutParams params = (RelativeLayout
                .LayoutParams) relativeLayout.getLayoutParams();
        Context c = relativeLayout.getContext();
        params.setMargins(0, RuntimeMargin.getPixelValue(c, 15), 0, 0);
        params.height = RuntimeMargin.getPixelValue(c, 20);
    }

    public void setDecorVisibility(boolean unHide){
        if(unHide) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

    public int getAdapter(){
       return getAdapterPosition();
    }

    @BindingAdapter({"decorVisibility"})
    public static void setDecorVisibility(View visibility, int pos){
        if(pos == 1){
            visibility.setVisibility(View.VISIBLE);
            return;
        }
        visibility.setVisibility(View.GONE);
    }
}
