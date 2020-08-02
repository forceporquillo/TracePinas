/*
 * Created by Force Porquillo on 7/5/20 12:26 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/2/20 11:06 PM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.databinding.NewsGroupLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.model.Group;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;

public class NewsGroupViewHolder extends RecyclerView.ViewHolder {
  private final NewsGroupLayoutBinding binding;

  public NewsGroupViewHolder(final NewsGroupLayoutBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public RecyclerView getRecyclerView() {
    return binding.newsGroupRecyclerView;
  }

  @BindingAdapter({ "decorVisibility" })
  public static void setDecorVisibility(View visibility, int pos) {
    if (pos == 1) {
      visibility.setVisibility(View.VISIBLE);
      return;
    }
    visibility.setVisibility(View.GONE);
  }

  public void bind(Group group) {
    binding.setGroup(group);
    binding.setVariable(BR.group, group);
    binding.setGroupAdapter(NewsGroupViewHolder.this);
    binding.setVariable(BR.groupAdapter, NewsGroupViewHolder.this);
    binding.executePendingBindings();
  }

  public void setMarginAtRuntime() {
    RelativeLayout.LayoutParams params = (RelativeLayout
        .LayoutParams) binding.headParent.getLayoutParams();
    Context c = binding.headParent.getContext();
    params.setMargins(0, Utils.dpToPx(c, 15), 0, 0);
    params.height = Utils.dpToPx(c, 20);
  }

  public int getAdapter() {
    return getAdapterPosition();
  }
}
