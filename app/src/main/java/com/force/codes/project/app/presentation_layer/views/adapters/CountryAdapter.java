/*
 * Created by Force Porquillo on 6/19/20 5:35 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/16/20 5:31 PM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.CountryRowsBinding;
import com.force.codes.project.app.model.CountryDetails;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.FragmentCallback;
import com.force.codes.project.app.presentation_layer.views.viewholders.CountryViewHolder;
import com.force.codes.project.app.presentation_layer.views.viewholders.DetailsViewHolder;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class CountryAdapter extends PagedListAdapter<CountryDetails, RecyclerView.ViewHolder>{
    private static final int HEADER_TOP = 0;
    private FragmentCallback fragmentCallback;

    public CountryAdapter(FragmentCallback callback){
        super(DIFF_CALLBACK);
        this.fragmentCallback = callback;
    }

    @NotNull @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // This inflates new layout view.
        // Occupies 1 size of current pageList size at index 0.
        /*if(HEADER_TOP == viewType){
            final View view = inflater
                    .inflate(R.layout.details_layout, parent, false);
            return new DetailsViewHolder(view);
        }*/

        CountryRowsBinding rowsBinding = DataBindingUtil
                .inflate(inflater, R.layout.country_rows, parent, false);
        return new CountryViewHolder(rowsBinding, fragmentCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        CountryDetails countryDetails = getCountryAt(position);

        // We cannot cast different viewHolders to
        // non object inflater so we skip index 0.
        //if(position != 0){
            CountryViewHolder viewHolder = (CountryViewHolder) holder;

            if(countryDetails != null){
                viewHolder.bindTo(countryDetails);
            } else{
                // TODO: show loading anim. when data is !yet fully loaded.
                // region ...
                Timber.d("loading...");
                // endregion
            }
        //}
    }

    @Override
    public int getItemViewType(int position){
        return position == 0 ? HEADER_TOP : 1;
    }

    private CountryDetails getCountryAt(int position){
        return getItem(position);
    }

    private static DiffUtil.ItemCallback<CountryDetails> DIFF_CALLBACK = new DiffUtil
            .ItemCallback<CountryDetails>(){
        @Override
        public boolean areItemsTheSame(
                @NonNull CountryDetails oldItem, @NonNull CountryDetails newItem){
            return oldItem.getCountry().equals(newItem.getCountry());
        }

        @Override
        public boolean areContentsTheSame(
                @NonNull CountryDetails oldItem, @NonNull CountryDetails newItem){
            return oldItem.getCountry().equals(newItem.getCountry());
        }
    };
}