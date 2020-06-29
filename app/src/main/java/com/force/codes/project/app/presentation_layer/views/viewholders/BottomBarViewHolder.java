/*
 * Created by Force Porquillo on 6/16/20 8:30 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/16/20 8:30 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.bottom_item_parent)
    public RelativeLayout parentContainer;

    @BindView(R.id.bottom_parent)
    public RelativeLayout bottom_parent;

    @BindView(R.id.bottom_icon)
    public ImageView bottomIcon;

    @BindView(R.id.bottom_bar_title)
    public TextView itemTitle;

    public BottomBarViewHolder(@NonNull View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItemTitle(String itemTitle){
        this.itemTitle.setText(itemTitle);
    }

    public void setIcon(int iconId){
        bottomIcon.setImageResource(iconId);
    }

    public void selectedStyle(int selected, int itemId, int itemDefIcon, int itemFillIcon){
        if(itemId == selected){
            itemTitle.setTextColor(Color.rgb(50, 121, 210));
            bottomIcon.setImageResource(itemFillIcon);
        }else{
            itemTitle.setTextColor(Color.rgb(191, 191, 191));
            bottomIcon.setImageResource(itemDefIcon);
        }
    }

    public void resizeItemWidth(int itemWidth){
        bottom_parent.getLayoutParams().width = itemWidth;
    }
}
