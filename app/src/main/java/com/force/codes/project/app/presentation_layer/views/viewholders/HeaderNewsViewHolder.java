/*
 * Created by Force Porquillo on 7/4/20 9:19 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:19 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.annotation.SuppressLint;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class HeaderNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.layout_parent)
    RelativeLayout parent;

    @BindView(R.id.group_header_text)
    TextView textView;

    @BindView(R.id.item_cover_picture)
    ImageView imageView;

    @BindView(R.id.date_timestamp)
    TextView timestamp;

    @BindView(R.id.screen_name)
    TextView screenName;

    private final int itemCount;
    //protected final HeaderNewsLayoutBinding binding;
    private NewsItemCallback callback;

    public HeaderNewsViewHolder(View view, int itemCount, NewsItemCallback callback){
        super(view);
        ButterKnife.bind(this, view);
        this.itemCount = itemCount;
        this.callback = callback;
//        View view = binding.getRoot();

        view.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void bindHeaderView(TwitterData twitterData, int position){
        // binding.setDummyModel(models);
        //binding.setVariable(BR.dummyModel, models);

        textView.setText(twitterData.getFullText());

        assert twitterData.getEntities() != null;
        if(twitterData.getEntities().getMedia() != null){
            String imageUrl = twitterData.getEntities()
                    .getMedia().get(0).getMediaUrlHttps();

            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .centerCrop()
                    .error(R.drawable.ic_warning)
                    .into(imageView);
        }

        timestamp.setText(twitterData.getCreatedAt().replace(" +0000", ""));
        screenName.setText("@" + twitterData.getUser().getScreenName());
    }

    @BindingAdapter({"headerImgUrl"})
    public static void
    setFlag(@NotNull ImageView imageView, final String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerInside()
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

    @Override
    public void onClick(View v){
        callback.headerNewsItemListener(getAdapterPosition());
    }
}
