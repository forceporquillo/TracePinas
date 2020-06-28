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
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.StringUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    // region ...
    @BindView(R.id.province)
    TextView province;

    @BindView(R.id.total_infected)
    TextView totalInfected;
    // endregion
    private View infoWindowView;

    @SuppressLint("InflateParams")
    public CustomInfoWindowAdapter(Context context){
        infoWindowView = LayoutInflater.from(context).
                inflate(R.layout.custom_info_window, null);
        ButterKnife.bind(this, infoWindowView);
    }

    private static ForegroundColorSpan colorSpan(View infoWindowView){
        return new ForegroundColorSpan(ContextCompat.getColor(
                infoWindowView.getContext(), R.color.blue));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getInfoWindow(Marker marker){
        province.setText(marker.getTitle());
        totalInfected.setText(spannableString(new StringUtils()
                .formatNumber(marker.getSnippet())));
        return infoWindowView;

    }

    @Override
    public View getInfoContents(Marker marker){
        return infoWindowView;
    }

    final SpannableString spannableString(String numCases){
        String totalInfected = infoWindowView.getResources()
                .getString(R.string.TOTAL, numCases);
        SpannableString spannableString = new SpannableString(totalInfected);
        spannableString.setSpan(colorSpan(infoWindowView),
                totalInfected.length() - numCases.length(),
                totalInfected.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
