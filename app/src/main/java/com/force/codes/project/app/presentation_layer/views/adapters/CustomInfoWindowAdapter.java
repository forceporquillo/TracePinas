/*
 * Created by Force Porquillo on 6/24/20 6:24 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/24/20 6:24 PM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;

import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.CustomInfoWindowBinding;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.StringUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
import static com.force.codes.project.app.presentation_layer.controller.custom.utils.StringUtils.formatNumber;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private final CustomInfoWindowBinding binding;

    @SuppressLint("InflateParams")
    public CustomInfoWindowAdapter(final Context context){
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.custom_info_window, null, false);
        binding.setWindowAdapter(this);
        binding.setVariable(BR.windowAdapter, this);
        binding.executePendingBindings();
    }

    private static ForegroundColorSpan colorSpan(final View view){
        return new ForegroundColorSpan(ContextCompat.getColor(
                view.getContext(), R.color.blue));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getInfoWindow(Marker marker){
        binding.province.setText(marker.getTitle());
        binding.totalInfected.setText(spannableString(
                formatNumber(marker.getSnippet())));
        return binding.getRoot();
    }

    @Override
    public View getInfoContents(Marker marker){
        return binding.getRoot();
    }

    final SpannableString spannableString(String numCases){
        String infected = binding
                .getRoot()
                .getResources()
                .getString(R.string.TOTAL, numCases);
        SpannableString spannableString = new SpannableString(infected);
        spannableString.setSpan(colorSpan(binding.getRoot()),
                infected.length() - numCases.length(),
                infected.length(), SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
