/*
 * Created by Force Porquillo on 8/8/20 9:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 9:30 PM
 */

package com.force.codes.project.app.presentation_layer.views.activity.list_component;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.ListViewItemsBinding;
import com.force.codes.project.app.presentation_layer.controller.interfaces.ListViewCallback;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
  private ListViewItemsBinding binding;
  private ListViewCallback callback;

  public ListViewHolder(@NonNull ListViewItemsBinding binding,
      ListViewCallback callback) {
    super(binding.getRoot());
    this.binding = binding;
    this.callback = callback;
    binding.getRoot().setOnClickListener(this);
    setBinding();
  }

  private void setBinding() {
    binding.setListViewHolder(this);
    binding.setVariable(BR.listViewHolder, this);
    binding.executePendingBindings();
  }

  public void setViews(CountryDetails details) {
    String cases = Utils.formatNumber(String.valueOf(details.getCases()));
    binding.cases.setText(cases);
    binding.country.setText(details.getCountry());
  }

  @Override public void onClick(View v) {
    callback.getPosition(getAdapterPosition());
  }
}
