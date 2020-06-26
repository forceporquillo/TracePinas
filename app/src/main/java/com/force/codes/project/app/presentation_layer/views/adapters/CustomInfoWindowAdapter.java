/*
 * Created by Force Porquillo on 6/24/20 6:24 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/24/20 6:24 PM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.force.codes.project.app.R;
import com.force.codes.project.app.service.executors.AppExecutors;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomInfoWindowMap implements GoogleMap.InfoWindowAdapter{
    @BindView(R.id.province)
    TextView province;

    @BindView(R.id.total_infected)
    TextView totalInfected;

    private final View infoWindowView;

    private final AppExecutors executors;
    private final Context context;

    @SuppressLint("InflateParams")
    public CustomInfoWindowMap(Context context, AppExecutors executors){
        this.context = context;
        this.executors = executors;

        infoWindowView = LayoutInflater.from(context).
                inflate(R.layout.custom_info_window, null);
        ButterKnife.bind(this, infoWindowView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getInfoWindow(Marker marker){
        final String cases = marker.getSnippet();
        final String provinces = marker.getTitle();
        //province.setText(provinces);
        totalInfected.setText(cases);

        return infoWindowView;
    }

    @Override
    public View getInfoContents(Marker marker){
        return infoWindowView;
    }
}
