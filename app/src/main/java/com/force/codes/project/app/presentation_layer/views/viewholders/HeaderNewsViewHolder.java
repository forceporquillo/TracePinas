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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.testmodel.Models;
import com.force.codes.project.app.databinding.HeaderNewsLayoutBinding;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderNewsViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.layout_parent)
    RelativeLayout parent;

    private final int itemCount;
    protected final HeaderNewsLayoutBinding binding;

    public HeaderNewsViewHolder(HeaderNewsLayoutBinding binding, int itemCount){
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.itemCount = itemCount;
        this.binding = binding;
    }

    public void bindHeaderView(Models models){
        binding.setDummyModel(models);
        binding.setVariable(BR.dummyModel, models);
    }

    @BindingAdapter({"headerImgUrl"})
    public static void
    setFlag(@NotNull ImageView imageView, final String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(imageView);
    }

    public void setMarginAtRuntime(int index){
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) parent.getLayoutParams();
        Context context = parent.getContext();

        if(index == 0){
            params.setMarginStart(getPixelValue(context, 15));
            params.setMarginEnd(getPixelValue(context, 10));
            return;
        }

        params.setMarginEnd(getPixelValue(context, 10));

        // adds 15dp margin at the end of last index item.
        if(index == (itemCount - 1))
            params.setMarginEnd(getPixelValue(context, 15));
    }

    // converts dp to px
    static int getPixelValue(Context context, int densityPixel){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                densityPixel,
                resources.getDisplayMetrics()
        );
    }
}
