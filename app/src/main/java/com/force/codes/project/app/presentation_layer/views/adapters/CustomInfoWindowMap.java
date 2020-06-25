/*
 * Created by Force Porquillo on 6/24/20 6:24 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/24/20 6:24 PM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.ObservableInt;

import com.force.codes.project.app.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomInfoWindowMap implements GoogleMap.InfoWindowAdapter{
    @BindView(R.id.province)
    TextView province;

    @BindView(R.id.total_infected)
    TextView totalInfected;

    private Context context;
    private Map<LatLng, Integer> mapOfCases;


    public CustomInfoWindowMap(Context context, Map<LatLng, Integer> mapOfCases){
        this.context = context;
        this.mapOfCases = mapOfCases;
    }

    @Override
    public View getInfoWindow(Marker marker){
        @SuppressLint("InflateParams")
        View infoWindowView = ((Activity) context).
                getLayoutInflater().
                inflate(R.layout.custom_info_window, null);

        ButterKnife.bind(this, infoWindowView);

        return infoWindowView;
    }

    @Override
    public View getInfoContents(Marker marker){

        return null;
    }

    public void clearContext(){
        if(context != null){
            context = null;
        }
    }

    public Context getContext(){
        return context;
    }
}
