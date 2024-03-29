/*
 * Created by Force Porquillo on 6/19/20 5:35 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/16/20 5:31 PM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.CountryRowsBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.views.viewholders.CountryViewHolder;
import org.jetbrains.annotations.NotNull;

public class CountryAdapter extends PagedListAdapter<CountryDetails, CountryViewHolder> {
  private static final int HEADER_TOP = 0;
  private StackEventListener.OnGetAdapterPosition callback;

  public CountryAdapter(StackEventListener.OnGetAdapterPosition callback) {
    super(DIFF_CALLBACK);
    this.callback = callback;
  }

  @Override
  @NotNull
  public CountryViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    CountryRowsBinding rowsBinding = CountryRowsBinding.inflate(inflater, parent, false);
    return new CountryViewHolder(rowsBinding, callback);
  }

  @Override
  public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
    CountryDetails countryDetails = getCountryAt(position);

    if (countryDetails != null) {
      holder.bindTo(countryDetails);
      holder.setTextUpdate(countryDetails);
    }
  }

  @Override
  public int getItemViewType(int position) {
    return position == 0 ? HEADER_TOP : 1;
  }

  private CountryDetails getCountryAt(int position) {
    return getItem(position);
  }

  private static DiffUtil.ItemCallback<CountryDetails> DIFF_CALLBACK = new DiffUtil
      .ItemCallback<CountryDetails>() {
    @Override
    public boolean areItemsTheSame(
        @NonNull CountryDetails oldItem, @NonNull CountryDetails newItem) {
      return oldItem.getCountry().equals(newItem.getCountry());
    }

    @Override
    public boolean areContentsTheSame(
        @NonNull CountryDetails oldItem, @NonNull CountryDetails newItem) {
      return oldItem.getCountry().equals(newItem.getCountry());
    }
  };
}