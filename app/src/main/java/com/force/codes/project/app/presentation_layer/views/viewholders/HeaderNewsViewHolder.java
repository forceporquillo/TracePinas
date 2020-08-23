/*
 * Created by Force Porquillo on 7/4/20 9:19 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:19 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.databinding.HeaderNewsLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;

public class HeaderNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  private StackEventListener.NewsItemCallback callback;
  private HeaderNewsLayoutBinding binding;

  public HeaderNewsViewHolder(
      final HeaderNewsLayoutBinding binding,
      final StackEventListener.NewsItemCallback callback
  ) {
    super(binding.getRoot());
    this.binding = binding;
    this.callback = callback;
    binding.getRoot().setOnClickListener(this);
  }

  @BindingAdapter({ "bindImage" })
  public static void bindHeaderView(ImageView imageView, final String imageUrl) {
    if (imageUrl != null) {
      Glide.with(imageView.getContext())
          .load(imageUrl)
          .centerCrop()
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .error(R.drawable.ic_warning)
          .dontAnimate()
          .into(imageView);
      return;
    }

    imageView.setBackgroundResource(R.drawable.ic_refresh);
  }

  @BindingAdapter({ "dateTextView" })
  public static void removeDateTimestamp(TextView timeStamp, final String date) {
    timeStamp.setText(date.replace(" +0000", ""));
  }

  public void bindTo(final TwitterData twitterData) {
    binding.setTwitterData(twitterData);
    binding.setVariable(BR.twitterData, twitterData);
    binding.invalidateAll();
  }

  public void setMarginAtRuntime(int index) {
    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
        binding.layoutParent.getLayoutParams();
    Context context = binding.layoutParent.getContext();
    if (index == 0) {
      params.setMarginStart(Utils.dpToPx(context, 15, true));
      params.setMarginEnd(Utils.dpToPx(context, 10, true));
      return;
    }
    params.setMarginEnd(Utils.dpToPx(context, 10, true));
    // adds 15dp margin at the end of last index item.
    if (index == (getAdapterPosition() - 1)) {
      params.setMarginEnd(Utils.dpToPx(context, 15, true));
    }
  }

  @Override public void onClick(View v) {
    callback.onRecentTweetsListener(getAdapterPosition());
  }
}
