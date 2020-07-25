/*
 * Created by Force Porquillo on 7/4/20 9:19 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:19 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.databinding.HeaderNewsLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.RuntimeMargin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HeaderNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.layout_parent)
    RelativeLayout parent;

    private final int itemCount;
    private NewsItemCallback callback;
    private HeaderNewsLayoutBinding binding;

    public HeaderNewsViewHolder(HeaderNewsLayoutBinding binding, int itemCount, NewsItemCallback callback){
        super(binding.getRoot());
        this.binding = binding;
        this.itemCount = itemCount;
        this.callback = callback;
        setBindView(binding.getRoot());
    }

    public void bindTo(TwitterData twitterData){
        binding.setTwitterData(twitterData);
        //binding.setVariable(BR.twitterData, twitterData);
    }

    public void setBindView(View view){
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    @BindingAdapter({"bindImage"})
    public static void bindHeaderView(ImageView imageView, String imageUrl){
        if(imageUrl != null){
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .centerCrop()
                    .dontAnimate()
                    .error(R.drawable.ic_warning)
                    .into(imageView);
        }
    }

    @BindingAdapter({"dateTextView"})
    public static void replaceDate(TextView timeStamp, String date){
        timeStamp.setText(date.replace(" +0000", ""));
    }

    public void setMarginAtRuntime(int index){
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) parent.getLayoutParams();
        Context context = parent.getContext();

        if(index == 0){
            params.setMarginStart(RuntimeMargin.getPixelValue(context, 15));
            params.setMarginEnd(RuntimeMargin.getPixelValue(context, 10));
            return;
        }
        params.setMarginEnd(RuntimeMargin.getPixelValue(context, 10));

        // adds 15dp margin at the end of last index item.
        if(index == (itemCount - 1))
            params.setMarginEnd(RuntimeMargin.getPixelValue(context, 15));
    }

    @Override
    public void onClick(View v){
        callback.headerNewsItemListener(
                getAdapterPosition()
        );
    }
}
