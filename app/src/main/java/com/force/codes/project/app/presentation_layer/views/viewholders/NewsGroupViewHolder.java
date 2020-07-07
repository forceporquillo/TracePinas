/*
 * Created by Force Porquillo on 7/5/20 12:26 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/5/20 12:26 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.testmodel.Group;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsGroupViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.news_group_recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.group_title)
    TextView title;

    @BindView(R.id.bottom_divider)
    View view;

    public NewsGroupViewHolder(@NonNull View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setGroupTitle(Group group){
        title.setText(group.getGroupTitle());
    }

    public void setDecorVisibility(boolean unHide){
        if(unHide) view.setVisibility(View.VISIBLE);
    }
}
