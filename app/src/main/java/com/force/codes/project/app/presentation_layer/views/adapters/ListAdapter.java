/*
 * Created by Force Porquillo on 8/8/20 9:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 9:30 PM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.ListViewItemsBinding;
import com.force.codes.project.app.presentation_layer.controller.interfaces.ListViewCallback;
import com.force.codes.project.app.presentation_layer.views.viewholders.ListViewHolder;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
  private final List<CountryDetails> countryDetailsList;
  private final ListViewCallback callback;

  public ListAdapter(
      List<CountryDetails> countryDetailsList,
      ListViewCallback callback
  ) {
    this.countryDetailsList = countryDetailsList;
    this.callback = callback;
  }

  @NonNull @Override
  public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    com.force.codes.project.app.databinding.ListViewItemsBinding binding =
        ListViewItemsBinding.inflate(inflater, parent, false);
    return new ListViewHolder(binding, callback);
  }

  @Override public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
    holder.setBinding(countryDetailsList.get(position));

  }

  @Override public int getItemCount() {
    return countryDetailsList.size();
  }
}
