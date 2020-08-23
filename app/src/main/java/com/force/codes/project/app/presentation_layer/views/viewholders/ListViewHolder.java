/*
 * Created by Force Porquillo on 8/8/20 9:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 9:30 PM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.ListViewItemsBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  private ListViewItemsBinding binding;
  private StackEventListener.onGetAdapterPosition callback;

  public ListViewHolder(@NonNull ListViewItemsBinding binding,
      StackEventListener.onGetAdapterPosition callback) {
    super(binding.getRoot());
    this.binding = binding;
    this.callback = callback;
    binding.getRoot().setOnClickListener(this);
  }

  public void setBinding(final CountryDetails details) {
    binding.setListItems(details);
    binding.setVariable(BR.listItems, details);
    binding.executePendingBindings();
  }

  @Override public void onClick(View v) {
    callback.onItemClicked(getAdapterPosition());
  }
}
